package pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import java.util.stream.Stream;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.CommentDataDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.CommitDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.IssueDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.MergeRequestDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.ProjectDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.RepositoryDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.SnippetDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.UserDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WebHookCommentDTO extends WebHookDTO {

    @JsonProperty("user")
    public UserDTO user;

    @JsonProperty("project_id")
    public Integer projectId;

    @JsonProperty("project")
    public ProjectDTO project;

    @JsonProperty("repository")
    public RepositoryDTO repository;

    @JsonProperty("object_attributes")
    public CommentDataDTO commentData;

    @JsonProperty("merge_request")
    public MergeRequestDTO mergeRequest;

    @JsonProperty("commit")
    public CommitDTO commit;

    @JsonProperty("issue")
    public IssueDTO issue;

    @JsonProperty("snippet")
    public SnippetDTO snippet;

    public WebHookCommentTargetDTO getTarget() {
        return Stream.of(mergeRequest, commit, issue, snippet)
                     .filter(Objects::nonNull)
                     .findFirst()
                     .orElse(null);
    }
}
