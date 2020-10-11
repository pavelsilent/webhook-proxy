package pro.sisit.utils.webhookproxy.usecase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pengrad.telegrambot.response.SendResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import pro.sisit.utils.webhookproxy.domain.entity.TelegramBot;
import pro.sisit.utils.webhookproxy.domain.entity.target.TelegramChannel;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.GitLabObjectKind;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.*;
import pro.sisit.utils.webhookproxy.service.cqs.CommandHandlerFactory;
import pro.sisit.utils.webhookproxy.service.query.ProxyRuleQueryService;
import pro.sisit.utils.webhookproxy.service.transform.RestConverterFactory;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class ReceiveEventHandlerTest {

    @Autowired
    private CommandHandlerFactory commandDispatcher;

    @Autowired
    private RestConverterFactory restConverter;

    @Autowired
    private Environment environment;

    @MockBean
    private ProxyRuleQueryService proxyRuleQueryService;

    @BeforeEach
    public void setUp() {
        TelegramBot bot = new TelegramBot();
        bot.setToken(environment.getProperty("telegram.bot.id"));

        TelegramChannel channel = new TelegramChannel();
        channel.setBot(bot);
        channel.setChannelId(environment.getProperty("telegram.channel.id"));

        Mockito.doReturn(Collections.singletonList(channel)).when(proxyRuleQueryService).findTargets(any());
    }

    @ParameterizedTest
    @ArgumentsSource(ReceiveTestArguments.class)
    void testSendMessage(String jsonFileName, Class expectedClass, GitLabObjectKind expectedObjectKind, boolean wasSend)
            throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(jsonFileName)).getFile());

        ObjectMapper mapper = new ObjectMapper();
        GitLabDTO dto = mapper.readValue(file, GitLabDTO.class);
        Assertions.assertEquals(expectedClass, dto.getClass());
        Assertions.assertEquals(expectedObjectKind.name(), dto.objectKind);

        ReceiveEventCommand command = new ReceiveEventCommand();
        command.setWebhookEvent(restConverter.toModelSoft(dto, null));

        Optional<List<SendResponse>> result = commandDispatcher.process(command);
        Assertions.assertTrue(result.isPresent());

        List<SendResponse> sendResponses = result.get();
        Assertions.assertNotNull(sendResponses);


        Assertions.assertEquals(wasSend ? 1 : 0, sendResponses.size());

        if (wasSend) {
            SendResponse sendResponse = sendResponses.get(0);

            Assertions.assertNotNull(sendResponse);
            Assertions.assertNotNull(sendResponse.message());
            Assertions.assertNotNull(sendResponse.message().messageId());
        }
    }

    static class ReceiveTestArguments implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    new Object[]{"gitlab/push-event.json", GitLabPushDTO.class, GitLabObjectKind.push, true},
                    new Object[]{"gitlab/tag-push-event.json", GitLabTagPushDTO.class, GitLabObjectKind.tag_push, false},
                    new Object[]{"gitlab/issue-event.json", GitLabIssueDTO.class, GitLabObjectKind.issue, false},
                    new Object[]{"gitlab/commit-comment-event.json", GitLabCommentDTO.class, GitLabObjectKind.note, true},
                    new Object[]{"gitlab/merge-request-comment-event.json", GitLabCommentDTO.class, GitLabObjectKind.note, true},
                    new Object[]{"gitlab/issue-comment-event.json", GitLabCommentDTO.class, GitLabObjectKind.note, false},
                    new Object[]{"gitlab/code-snippet-comment-event.json", GitLabCommentDTO.class, GitLabObjectKind.note, false},
                    new Object[]{"gitlab/merge-request-event.json", GitLabMergeRequestDTO.class, GitLabObjectKind.merge_request, true},
                    new Object[]{"gitlab/wiki-event.json", GitLabWikiDTO.class, GitLabObjectKind.wiki_page, false},
                    new Object[]{"gitlab/pipeline-event.json", GitLabPipelineDTO.class, GitLabObjectKind.pipeline, true},
                    new Object[]{"gitlab/job-event.json", GitLabJobDTO.class, GitLabObjectKind.build, false})
                    .map(Arguments::of);
        }
    }

}