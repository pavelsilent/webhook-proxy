package pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabCommentTargetDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SnippetDTO implements GitLabCommentTargetDTO {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("title")
    public String title;
    @JsonProperty("content")
    public String content;
    @JsonProperty("author_id")
    public Integer authorId;
    @JsonProperty("project_id")
    public Integer projectId;
    @JsonProperty("created_at")
    public String createdAt;
    @JsonProperty("updated_at")
    public String updatedAt;
    @JsonProperty("file_name")
    public String fileName;
    @JsonProperty("expires_at")
    public String expiresAt;
    @JsonProperty("type")
    public String type;
    @JsonProperty("visibility_level")
    public Integer visibilityLevel;
}
