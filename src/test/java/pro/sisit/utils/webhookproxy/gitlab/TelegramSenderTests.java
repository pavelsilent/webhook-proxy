package pro.sisit.utils.webhookproxy.gitlab;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pengrad.telegrambot.model.request.ParseMode;
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
import pro.sisit.utils.webhookproxy.domain.TelegramGroup;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabWebHookDTO;
import pro.sisit.utils.webhookproxy.rest.dto.jenkins.JenkinsBuildEventDTO;
import pro.sisit.utils.webhookproxy.service.TelegramSender;
import pro.sisit.utils.webhookproxy.service.builder.TelegramMessageBuilderFactory;
import pro.sisit.utils.webhookproxy.service.transform.GitlabRestConverter;
import pro.sisit.utils.webhookproxy.service.transform.WebHookRestConverterFactory;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

@SpringBootTest
class TelegramSenderTests {

    @Autowired
    private GitlabRestConverter restConverter;

    @Autowired
    private WebHookRestConverterFactory restConverterFactory;

    @Autowired
    private TelegramMessageBuilderFactory messageBuilder;

    @Autowired
    private TelegramSender sender;

    @Autowired
    private Environment environment;

    private TelegramGroup channel;

    @BeforeEach
    private void before() {
        channel = TelegramGroup.builder()
                .botId(environment.getProperty("telegram.bot.id"))
                .channelId(environment.getProperty("telegram.channel.id"))
                .build();
    }

    @ParameterizedTest
    @ArgumentsSource(TelegramSenderTests.TelegramSendMessageTestArguments.class)
    void testSendMessage(String jsonFileName, Class dtoClass)
            throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(jsonFileName)).getFile());

        ObjectMapper mapper = new ObjectMapper();
        Object event = restConverterFactory.toModel(mapper.readValue(file, dtoClass));
        String message = messageBuilder.toMessage(event);
        SendResponse response = sender.send(channel, message, messageBuilder.getParseMode(event));

        System.out.println(response);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.message());
        Assertions.assertNotNull(response.message().messageId());
    }

    static class TelegramSendMessageTestArguments implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    new Object[]{"gitlab/merge-request-event.json", GitLabWebHookDTO.class},
                    new Object[]{"gitlab/merge-request-comment-event.json", GitLabWebHookDTO.class},
                    new Object[]{"gitlab/commit-comment-event.json", GitLabWebHookDTO.class},
                    new Object[]{"gitlab/pipeline-event.json", GitLabWebHookDTO.class},
                    new Object[]{"jenkins/build-event.json", JenkinsBuildEventDTO.class})
                    .map(Arguments::of);
        }
    }

}
