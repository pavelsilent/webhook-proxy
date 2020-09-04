package pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PipelineDataDTO {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("ref")
    public String ref;
    @JsonProperty("tag")
    public Boolean tag;
    @JsonProperty("sha")
    public String sha;
    @JsonProperty("before_sha")
    public String beforeSha;
    @JsonProperty("source")
    public String source;
    @JsonProperty("status")
    public String status;
    @JsonProperty("stages")
    public List<String> stages;
    @JsonProperty("created_at")
    public String createdAt;
    @JsonProperty("finished_at")
    public String finishedAt;
    @JsonProperty("duration")
    public Integer duration;
    @JsonProperty("variables")
    public List<Variable> variables = null;

}

@JsonInclude(JsonInclude.Include.NON_NULL)
class Variable {

    @JsonProperty("key")
    public String key;
    @JsonProperty("value")
    public String value;

}
