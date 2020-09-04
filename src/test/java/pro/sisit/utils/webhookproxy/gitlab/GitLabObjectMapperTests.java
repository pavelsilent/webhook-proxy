package pro.sisit.utils.webhookproxy.gitlab;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.GitLabObjectKind;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.WebHookCommentDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.WebHookDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.WebHookIssueDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.WebHookJobDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.WebHookMergeRequestDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.WebHookPipelineDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.WebHookPushDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.WebHookTagPushDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.WebHookWikiDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.CommitDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.IssueDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.MergeRequestDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.SnippetDTO;

class GitLabObjectMapperTests {

    @ParameterizedTest
    @ArgumentsSource(TestObjectMapperArguments.class)
    void testObjectMapper(String jsonFileName, Class expectedClass, GitLabObjectKind expectedObjectKind)
        throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(jsonFileName)).getFile());
        System.out.println(file.getAbsolutePath());

        ObjectMapper mapper = new ObjectMapper();
        WebHookDTO webHookDTO = mapper.readValue(file, WebHookDTO.class);
        System.out.println(webHookDTO);
        Assertions.assertEquals(expectedClass, webHookDTO.getClass());
        Assertions.assertEquals(expectedObjectKind.name(), webHookDTO.objectKind);
    }

    @ParameterizedTest
    @ArgumentsSource(TestNoteObjectMapperArguments.class)
    void testNoteObjectMapper(String jsonFileName, Class expectedClass, Class expectedTargetClass)
        throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(jsonFileName)).getFile());
        System.out.println(file.getAbsolutePath());

        ObjectMapper mapper = new ObjectMapper();
        WebHookDTO webHookDTO = mapper.readValue(file, WebHookDTO.class);
        System.out.println(webHookDTO);

        WebHookCommentDTO webHookCommentDTO = (WebHookCommentDTO) webHookDTO;

        Assertions.assertEquals(expectedClass, webHookCommentDTO.getClass());
        Assertions.assertEquals(GitLabObjectKind.note.name(), webHookCommentDTO.objectKind);
        Assertions.assertEquals(expectedTargetClass, webHookCommentDTO.getTarget().getClass());
    }

    static class TestNoteObjectMapperArguments implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                new Object[]{"commit-comment-event.json", WebHookCommentDTO.class, CommitDTO.class},
                new Object[]{"merge-request-comment-event.json", WebHookCommentDTO.class, MergeRequestDTO.class},
                new Object[]{"issue-comment-event.json", WebHookCommentDTO.class, IssueDTO.class},
                new Object[]{"code-snippet-comment-event.json", WebHookCommentDTO.class, SnippetDTO.class})
                         .map(Arguments::of);
        }
    }

    static class TestObjectMapperArguments implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                new Object[]{"push-event.json", WebHookPushDTO.class, GitLabObjectKind.push},
                new Object[]{"tag-push-event.json", WebHookTagPushDTO.class, GitLabObjectKind.tag_push},
                new Object[]{"issue-event.json", WebHookIssueDTO.class, GitLabObjectKind.issue},
                new Object[]{"commit-comment-event.json", WebHookCommentDTO.class, GitLabObjectKind.note},
                new Object[]{"merge-request-comment-event.json", WebHookCommentDTO.class, GitLabObjectKind.note},
                new Object[]{"issue-comment-event.json", WebHookCommentDTO.class, GitLabObjectKind.note},
                new Object[]{"code-snippet-comment-event.json", WebHookCommentDTO.class, GitLabObjectKind.note},
                new Object[]{"merge-request-event.json", WebHookMergeRequestDTO.class, GitLabObjectKind.merge_request},
                new Object[]{"wiki-event.json", WebHookWikiDTO.class, GitLabObjectKind.wiki_page},
                new Object[]{"pipeline-event.json", WebHookPipelineDTO.class, GitLabObjectKind.pipeline},
                new Object[]{"job-event.json", WebHookJobDTO.class, GitLabObjectKind.build})
                         .map(Arguments::of);
        }
    }

}
