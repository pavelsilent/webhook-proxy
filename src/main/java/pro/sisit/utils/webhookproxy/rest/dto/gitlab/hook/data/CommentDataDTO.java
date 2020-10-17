package pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDataDTO {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("note")
    public String note;
    @JsonProperty("noteable_type")
    public String noteableType;
    @JsonProperty("author_id")
    public Integer authorId;
    @JsonProperty("created_at")
    public String createdAt;
    @JsonProperty("updated_at")
    public String updatedAt;
    @JsonProperty("project_id")
    public Integer projectId;
    @JsonProperty("attachment")
    public String attachment;
    @JsonProperty("line_code")
    public String lineCode;
    @JsonProperty("commit_id")
    public String commitId;
    @JsonProperty("noteable_id")
    public Integer noteableId;
    @JsonProperty("system")
    public Boolean system;
    @JsonProperty("st_diff")
    public CodeDiffDTO codeDiff;
    @JsonProperty("url")
    public String url;

}
