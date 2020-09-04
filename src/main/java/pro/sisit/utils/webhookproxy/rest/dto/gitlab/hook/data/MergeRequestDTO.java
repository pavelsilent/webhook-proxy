package pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.WebHookCommentTargetDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MergeRequestDTO implements WebHookCommentTargetDTO {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("target_branch")
    public String targetBranch;
    @JsonProperty("source_branch")
    public String sourceBranch;
    @JsonProperty("source_project_id")
    public Integer sourceProjectId;
    @JsonProperty("author_id")
    public Integer authorId;
    @JsonProperty("assignee_id")
    public Integer assigneeId;
    @JsonProperty("title")
    public String title;
    @JsonProperty("created_at")
    public String createdAt;
    @JsonProperty("updated_at")
    public String updatedAt;
    @JsonProperty("milestone_id")
    public Integer milestoneId;
    @JsonProperty("state")
    public String state;
    @JsonProperty("merge_status")
    public String mergeStatus;
    @JsonProperty("target_project_id")
    public Integer targetProjectId;
    @JsonProperty("iid")
    public Integer iid;
    @JsonProperty("description")
    public String description;
    @JsonProperty("position")
    public Integer position;
    @JsonProperty("source")
    public ProjectDTO source;
    @JsonProperty("target")
    public ProjectDTO target;
    @JsonProperty("last_commit")
    public CommitDTO lastCommit;
    @JsonProperty("work_in_progress")
    public Boolean workInProgress;
    @JsonProperty("assignee")
    public UserDTO assignee;

}
