package pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.BuildDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.CommitDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.MergeRequestShortDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.PipelineDataDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.ProjectDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.UserDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GitLabWebHookPipelineDTO extends GitLabWebHookDTO {

    @JsonProperty("object_attributes")
    public PipelineDataDTO pipelineData;
    @JsonProperty("merge_request")
    public MergeRequestShortDTO mergeRequestData;
    @JsonProperty("user")
    public UserDTO user;
    @JsonProperty("project")
    public ProjectDTO project;
    @JsonProperty("commit")
    public CommitDTO commit;
    @JsonProperty("builds")
    public List<BuildDTO> builds;
}
