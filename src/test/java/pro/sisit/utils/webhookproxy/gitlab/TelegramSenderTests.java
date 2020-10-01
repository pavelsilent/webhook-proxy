package pro.sisit.utils.webhookproxy.gitlab;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import pro.sisit.utils.webhookproxy.domain.TelegramGroup;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.event.CommitCommentEvent;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.event.MergeRequestCommentEvent;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.event.MergeRequestEvent;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.WebHookDTO;
import pro.sisit.utils.webhookproxy.service.TelegramMessageBuilder;
import pro.sisit.utils.webhookproxy.service.TelegramSender;
import pro.sisit.utils.webhookproxy.service.transform.event.GitlabEventRestConverterFactory;
import pro.sisit.utils.webhookproxy.service.transform.GitlabRestConverter;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@SpringBootTest
class TelegramSenderTests {

    @Autowired
    private GitlabRestConverter restConverter;

    @Autowired
    private GitlabEventRestConverterFactory restConverterFactory;

    @Autowired
    private TelegramMessageBuilder messageBuilder;

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

    @Test
    void testMergeRequestSending() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("merge-request-event.json")).getFile());
        System.out.println(file.getAbsolutePath());

        ObjectMapper mapper = new ObjectMapper();
        WebHookDTO webHookDTO = mapper.readValue(file, WebHookDTO.class);
        MergeRequestEvent event = restConverterFactory.toModel(webHookDTO);

        sender.send(channel, messageBuilder.toMessage(event));
    }

    @Test
    void testMergeRequestCommentSending() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("merge-request-comment-event.json")).getFile());
        System.out.println(file.getAbsolutePath());

        ObjectMapper mapper = new ObjectMapper();
        WebHookDTO webHookDTO = mapper.readValue(file, WebHookDTO.class);
        MergeRequestCommentEvent event = restConverterFactory.toModel(webHookDTO);

        sender.send(channel, messageBuilder.toMessage(event));
    }

    @Test
    void testCommitCommentSending() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("commit-comment-event.json")).getFile());
        System.out.println(file.getAbsolutePath());

        ObjectMapper mapper = new ObjectMapper();
        WebHookDTO webHookDTO = mapper.readValue(file, WebHookDTO.class);
        CommitCommentEvent event = restConverterFactory.toModel(webHookDTO);

        sender.send(channel, messageBuilder.toMessage(event));
    }
}
