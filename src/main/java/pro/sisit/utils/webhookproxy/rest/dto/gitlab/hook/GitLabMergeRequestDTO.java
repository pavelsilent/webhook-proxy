package pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.ChangesDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.LabelDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.MergeRequestDataDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.ProjectDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.RepositoryDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.UserDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class GitLabMergeRequestDTO extends GitLabDTO {

    @JsonProperty("user")
    public UserDTO user;
    @JsonProperty("project")
    public ProjectDTO project;
    @JsonProperty("repository")
    public RepositoryDTO repository;
    @JsonProperty("object_attributes")
    public MergeRequestDataDTO mergeRequestData;
    @JsonProperty("labels")
    public List<LabelDTO> labels;
    @JsonProperty("changes")
    public ChangesDTO changes;
}
