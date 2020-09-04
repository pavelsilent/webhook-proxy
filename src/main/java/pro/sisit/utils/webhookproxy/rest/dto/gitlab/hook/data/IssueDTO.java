package pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.WebHookCommentTargetDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IssueDTO implements WebHookCommentTargetDTO {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("title")
    public String title;
    @JsonProperty("assignee_ids")
    public List<Integer> assigneeIds = null;
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
    @JsonProperty("position")
    public Integer position;
    @JsonProperty("branch_name")
    public String branchName;
    @JsonProperty("description")
    public String description;
    @JsonProperty("milestone_id")
    public Integer milestoneId;
    @JsonProperty("state")
    public String state;
    @JsonProperty("iid")
    public Integer iid;
    @JsonProperty("labels")
    public List<LabelDTO> labels;
}
