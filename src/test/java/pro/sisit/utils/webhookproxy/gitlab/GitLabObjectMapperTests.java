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
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabCommentDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabIssueDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabJobDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabMergeRequestDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabPipelineDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabPushDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabTagPushDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabWikiDTO;
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
        GitLabDTO webHookDTO = mapper.readValue(file, GitLabDTO.class);
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
        GitLabDTO webHookDTO = mapper.readValue(file, GitLabDTO.class);

        GitLabCommentDTO webHookCommentDTO = (GitLabCommentDTO) webHookDTO;

        Assertions.assertEquals(expectedClass, webHookCommentDTO.getClass());
        Assertions.assertEquals(GitLabObjectKind.note.name(), webHookCommentDTO.objectKind);
        Assertions.assertEquals(expectedTargetClass, webHookCommentDTO.getTarget().getClass());
    }

    static class TestNoteObjectMapperArguments implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                new Object[]{"gitlab/commit-comment-event.json", GitLabCommentDTO.class, CommitDTO.class},
                new Object[]{"gitlab/merge-request-comment-event.json", GitLabCommentDTO.class, MergeRequestDTO.class},
                new Object[]{"gitlab/issue-comment-event.json", GitLabCommentDTO.class, IssueDTO.class},
                new Object[]{"gitlab/code-snippet-comment-event.json", GitLabCommentDTO.class, SnippetDTO.class})
                         .map(Arguments::of);
        }
    }

    static class TestObjectMapperArguments implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                new Object[]{"gitlab/push-event.json", GitLabPushDTO.class, GitLabObjectKind.push},
                new Object[]{"gitlab/tag-push-event.json", GitLabTagPushDTO.class, GitLabObjectKind.tag_push},
                new Object[]{"gitlab/issue-event.json", GitLabIssueDTO.class, GitLabObjectKind.issue},
                new Object[]{"gitlab/commit-comment-event.json", GitLabCommentDTO.class, GitLabObjectKind.note},
                new Object[]{"gitlab/merge-request-comment-event.json", GitLabCommentDTO.class, GitLabObjectKind.note},
                new Object[]{"gitlab/issue-comment-event.json", GitLabCommentDTO.class, GitLabObjectKind.note},
                new Object[]{"gitlab/code-snippet-comment-event.json", GitLabCommentDTO.class, GitLabObjectKind.note},
                new Object[]{"gitlab/merge-request-event.json", GitLabMergeRequestDTO.class, GitLabObjectKind.merge_request},
                new Object[]{"gitlab/wiki-event.json", GitLabWikiDTO.class, GitLabObjectKind.wiki_page},
                new Object[]{"gitlab/pipeline-event.json", GitLabPipelineDTO.class, GitLabObjectKind.pipeline},
                new Object[]{"gitlab/job-event.json", GitLabJobDTO.class, GitLabObjectKind.build})
                         .map(Arguments::of);
        }
    }

}
