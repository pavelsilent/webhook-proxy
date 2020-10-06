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
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabWebHookCommentDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabWebHookDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabWebHookIssueDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabWebHookJobDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabWebHookMergeRequestDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabWebHookPipelineDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabWebHookPushDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabWebHookTagPushDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabWebHookWikiDTO;
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

        ObjectMapper mapper = new ObjectMapper();
        GitLabWebHookDTO webHookDTO = mapper.readValue(file, GitLabWebHookDTO.class);
        Assertions.assertEquals(expectedClass, webHookDTO.getClass());
        Assertions.assertEquals(expectedObjectKind.name(), webHookDTO.objectKind);
    }

    @ParameterizedTest
    @ArgumentsSource(TestNoteObjectMapperArguments.class)
    void testNoteObjectMapper(String jsonFileName, Class expectedClass, Class expectedTargetClass)
        throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(jsonFileName)).getFile());

        ObjectMapper mapper = new ObjectMapper();
        GitLabWebHookDTO webHookDTO = mapper.readValue(file, GitLabWebHookDTO.class);

        GitLabWebHookCommentDTO webHookCommentDTO = (GitLabWebHookCommentDTO) webHookDTO;

        Assertions.assertEquals(expectedClass, webHookCommentDTO.getClass());
        Assertions.assertEquals(GitLabObjectKind.note.name(), webHookCommentDTO.objectKind);
        Assertions.assertEquals(expectedTargetClass, webHookCommentDTO.getTarget().getClass());
    }

    static class TestNoteObjectMapperArguments implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                new Object[]{"gitlab/commit-comment-event.json", GitLabWebHookCommentDTO.class, CommitDTO.class},
                new Object[]{"gitlab/merge-request-comment-event.json", GitLabWebHookCommentDTO.class, MergeRequestDTO.class},
                new Object[]{"gitlab/issue-comment-event.json", GitLabWebHookCommentDTO.class, IssueDTO.class},
                new Object[]{"gitlab/code-snippet-comment-event.json", GitLabWebHookCommentDTO.class, SnippetDTO.class})
                         .map(Arguments::of);
        }
    }

    static class TestObjectMapperArguments implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                new Object[]{"gitlab/push-event.json", GitLabWebHookPushDTO.class, GitLabObjectKind.push},
                new Object[]{"gitlab/tag-push-event.json", GitLabWebHookTagPushDTO.class, GitLabObjectKind.tag_push},
                new Object[]{"gitlab/issue-event.json", GitLabWebHookIssueDTO.class, GitLabObjectKind.issue},
                new Object[]{"gitlab/commit-comment-event.json", GitLabWebHookCommentDTO.class, GitLabObjectKind.note},
                new Object[]{"gitlab/merge-request-comment-event.json", GitLabWebHookCommentDTO.class, GitLabObjectKind.note},
                new Object[]{"gitlab/issue-comment-event.json", GitLabWebHookCommentDTO.class, GitLabObjectKind.note},
                new Object[]{"gitlab/code-snippet-comment-event.json", GitLabWebHookCommentDTO.class, GitLabObjectKind.note},
                new Object[]{"gitlab/merge-request-event.json", GitLabWebHookMergeRequestDTO.class, GitLabObjectKind.merge_request},
                new Object[]{"gitlab/wiki-event.json", GitLabWebHookWikiDTO.class, GitLabObjectKind.wiki_page},
                new Object[]{"gitlab/pipeline-event.json", GitLabWebHookPipelineDTO.class, GitLabObjectKind.pipeline},
                new Object[]{"gitlab/job-event.json", GitLabWebHookJobDTO.class, GitLabObjectKind.build})
                         .map(Arguments::of);
        }
    }

}
