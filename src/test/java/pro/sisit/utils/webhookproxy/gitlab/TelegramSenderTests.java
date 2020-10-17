package pro.sisit.utils.webhookproxy.gitlab;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pengrad.telegrambot.response.SendResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import pro.sisit.utils.webhookproxy.WebHookProxyTestApplication;
import pro.sisit.utils.webhookproxy.domain.Event;
import pro.sisit.utils.webhookproxy.domain.entity.TelegramBot;
import pro.sisit.utils.webhookproxy.domain.entity.target.TelegramChannel;
import pro.sisit.utils.webhookproxy.domain.model.telegram.Message;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabPushDTO;
import pro.sisit.utils.webhookproxy.rest.dto.jenkins.JenkinsBuildEventDTO;
import pro.sisit.utils.webhookproxy.service.builder.TelegramMessageBuilderFactory;
import pro.sisit.utils.webhookproxy.service.sender.TelegramSender;
import pro.sisit.utils.webhookproxy.service.transform.GitlabRestConverter;
import pro.sisit.utils.webhookproxy.service.transform.RestConverterFactory;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

@SpringBootTest(classes = WebHookProxyTestApplication.class)
class TelegramSenderTests {

    @Autowired
    private GitlabRestConverter restConverter;

    @Autowired
    private RestConverterFactory restConverterFactory;

    @Autowired
    private TelegramMessageBuilderFactory messageBuilder;

    @Autowired
    private TelegramSender sender;

    @Autowired
    private Environment environment;

    private TelegramChannel channel;

    @BeforeEach
    private void before() {
        TelegramBot bot = new TelegramBot();
        bot.setToken(environment.getProperty("telegram.bot.id"));

        channel = new TelegramChannel();
        channel.setBot(bot);
        channel.setChannelId(environment.getProperty("telegram.channel.id"));
    }

    @ParameterizedTest
    @ArgumentsSource(TelegramSenderTests.TelegramSendMessageTestArguments.class)
    void testSendMessage(String jsonFileName, Class dtoClass)
            throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(jsonFileName)).getFile());

        ObjectMapper mapper = new ObjectMapper();
        Event event = restConverterFactory.toModel(mapper.readValue(file, dtoClass));
        Message message = messageBuilder.toMessage(event);
        SendResponse response = sender.send(channel, message);

        System.out.println(response);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.message());
        Assertions.assertNotNull(response.message().messageId());
    }

    static class TelegramSendMessageTestArguments implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    new Object[]{"gitlab/merge-request-event.json", GitLabDTO.class},
                    new Object[]{"gitlab/merge-request-comment-event.json", GitLabDTO.class},
                    new Object[]{"gitlab/commit-comment-event.json", GitLabDTO.class},
                    new Object[]{"gitlab/pipeline-event.json", GitLabDTO.class},
                    new Object[]{"gitlab/push-event.json", GitLabPushDTO.class},
                    new Object[]{"jenkins/build-event.json", JenkinsBuildEventDTO.class})
                    .map(Arguments::of);
        }
    }

}
