package pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.CommitInfoDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.RepositoryFullDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.UserDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GitLabWebHookJobDTO extends GitLabWebHookDTO {

    @JsonProperty("ref")
    public String ref;
    @JsonProperty("tag")
    public Boolean tag;
    @JsonProperty("before_sha")
    public String beforeSha;
    @JsonProperty("sha")
    public String sha;
    @JsonProperty("build_id")
    public Integer buildId;
    @JsonProperty("build_name")
    public String buildName;
    @JsonProperty("build_stage")
    public String buildStage;
    @JsonProperty("build_status")
    public String buildStatus;
    @JsonProperty("build_started_at")
    public String buildStartedAt;
    @JsonProperty("build_finished_at")
    public String buildFinishedAt;
    @JsonProperty("build_duration")
    public Integer buildDuration;
    @JsonProperty("build_allow_failure")
    public Boolean buildAllowFailure;
    @JsonProperty("build_failure_reason")
    public String buildFailureReason;
    @JsonProperty("pipeline_id")
    public Integer pipelineId;
    @JsonProperty("project_id")
    public Integer projectId;
    @JsonProperty("project_name")
    public String projectName;
    @JsonProperty("user")
    public UserDTO user;
    @JsonProperty("commit")
    public CommitInfoDTO commit;
    @JsonProperty("repository")
    public RepositoryFullDTO repository;
    @JsonProperty("runner")
    public RunnerDTO runner;
}

@JsonInclude(JsonInclude.Include.NON_NULL)
class RunnerDTO {

    @JsonProperty("active")
    public Boolean active;
    @JsonProperty("is_shared")
    public Boolean isShared;
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("description")
    public String description;

}

