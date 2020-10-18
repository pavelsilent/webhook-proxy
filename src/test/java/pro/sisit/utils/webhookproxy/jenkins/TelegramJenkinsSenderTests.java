package pro.sisit.utils.webhookproxy.jenkins;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pengrad.telegrambot.response.SendResponse;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;
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
import pro.sisit.utils.webhookproxy.domain.entity.TelegramBot;
import pro.sisit.utils.webhookproxy.domain.entity.target.TelegramChannel;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.JenkinsEvent;
import pro.sisit.utils.webhookproxy.domain.model.telegram.Message;
import pro.sisit.utils.webhookproxy.rest.dto.jenkins.JenkinsEventDTO;
import pro.sisit.utils.webhookproxy.service.builder.TelegramMessageBuilderFactory;
import pro.sisit.utils.webhookproxy.service.sender.TelegramSender;
import pro.sisit.utils.webhookproxy.service.transform.JenkinsRestConverter;

@SpringBootTest(classes = WebHookProxyTestApplication.class)
class TelegramJenkinsSenderTests {

    @Autowired
    private JenkinsRestConverter jenkinsRestConverter;

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
    @ArgumentsSource(TelegramJenkinsSenderTests.TelegramSendMessageTestArguments.class)
    void testSendMessage(String jsonFileName)
        throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(jsonFileName)).getFile());

        ObjectMapper mapper = new ObjectMapper();
        JenkinsEventDTO dto = mapper.readValue(file, JenkinsEventDTO.class);
        Assertions.assertEquals(JenkinsEventDTO.class, dto.getClass());
        JenkinsEvent model = jenkinsRestConverter.toModel(dto);

        Message message = messageBuilder.toMessage(model);
        SendResponse response = sender.send(channel, message);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.message());
        Assertions.assertNotNull(response.message().messageId());
    }

    static class TelegramSendMessageTestArguments implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                new Object[]{"jenkins/notification-plugin-build-event.json"})
                         .map(Arguments::of);
        }
    }

}
