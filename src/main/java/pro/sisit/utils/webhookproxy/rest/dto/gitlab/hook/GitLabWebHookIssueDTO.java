package pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.ChangesDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.IssueDataDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.LabelDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.ProjectDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.RepositoryDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.UserDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GitLabWebHookIssueDTO extends GitLabWebHookDTO {

    @JsonProperty("event_type")
    public String eventType;
    @JsonProperty("user")
    public UserDTO user;
    @JsonProperty("project")
    public ProjectDTO project;
    @JsonProperty("object_attributes")
    public IssueDataDTO issueData;
    @JsonProperty("repository")
    public RepositoryDTO repository;
    @JsonProperty("assignees")
    public List<UserDTO> assignees;
    @JsonProperty("assignee")
    public UserDTO assignee;
    @JsonProperty("labels")
    public List<LabelDTO> labels;
    @JsonProperty("changes")
    public ChangesDTO changes;
}
