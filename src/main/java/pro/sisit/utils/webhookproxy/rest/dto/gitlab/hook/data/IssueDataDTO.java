package pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IssueDataDTO {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("title")
    public String title;
    @JsonProperty("assignee_ids")
    public List<Integer> assigneeIds;
    @JsonProperty("assignee_id")
    public Integer assigneeId;
    @JsonProperty("author_id")
    public Integer authorId;
    @JsonProperty("project_id")
    public Integer projectId;
    @JsonProperty("created_at")
    public String createdAt;
    @JsonProperty("updated_at")
    public String updatedAt;
    @JsonProperty("updated_by_id")
    public Integer updatedById;
    @JsonProperty("last_edited_at")
    public String lastEditedAt;
    @JsonProperty("last_edited_by_id")
    public Integer lastEditedById;
    @JsonProperty("relative_position")
    public Integer relativePosition;
    @JsonProperty("description")
    public String description;
    @JsonProperty("milestone_id")
    public Integer milestoneId;
    @JsonProperty("state_id")
    public Integer stateId;
    @JsonProperty("confidential")
    public Boolean confidential;
    @JsonProperty("discussion_locked")
    public Boolean discussionLocked;
    @JsonProperty("due_date")
    public String dueDate;
    @JsonProperty("moved_to_id")
    public Integer movedToId;
    @JsonProperty("duplicated_to_id")
    public Integer duplicatedToId;
    @JsonProperty("time_estimate")
    public Integer timeEstimate;
    @JsonProperty("total_time_spent")
    public Integer totalTimeSpent;
    @JsonProperty("human_total_time_spent")
    public Integer humanTotalTimeSpent;
    @JsonProperty("human_time_estimate")
    public Integer humanTimeEstimate;
    @JsonProperty("weight")
    public Integer weight;
    @JsonProperty("iid")
    public Integer iid;
    @JsonProperty("url")
    public String url;
    @JsonProperty("state")
    public String state;
    @JsonProperty("action")
    public String action;
    @JsonProperty("labels")
    public List<LabelDTO> labels = null;
}
