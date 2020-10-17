package pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MergeRequestShortDTO {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("iid")
    public Integer iid;
    @JsonProperty("title")
    public String title;
    @JsonProperty("source_branch")
    public String sourceBranch;
    @JsonProperty("source_project_id")
    public Integer sourceProjectId;
    @JsonProperty("target_branch")
    public String targetBranch;
    @JsonProperty("target_project_id")
    public Integer targetProjectId;
    @JsonProperty("state")
    public String state;
    @JsonProperty("merge_status")
    public String mergeStatus;
    @JsonProperty("url")
    public String url;

}
