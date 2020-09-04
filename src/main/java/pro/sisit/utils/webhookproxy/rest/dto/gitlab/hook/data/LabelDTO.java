package pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LabelDTO {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("title")
    public String title;
    @JsonProperty("color")
    public String color;
    @JsonProperty("project_id")
    public Integer projectId;
    @JsonProperty("created_at")
    public String createdAt;
    @JsonProperty("updated_at")
    public String updatedAt;
    @JsonProperty("template")
    public Boolean template;
    @JsonProperty("description")
    public String description;
    @JsonProperty("type")
    public String type;
    @JsonProperty("group_id")
    public Integer groupId;
}
