package pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BuildDTO {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("stage")
    public String stage;
    @JsonProperty("name")
    public String name;
    @JsonProperty("status")
    public String status;
    @JsonProperty("created_at")
    public String createdAt;
    @JsonProperty("started_at")
    public String startedAt;
    @JsonProperty("finished_at")
    public String finishedAt;
    @JsonProperty("when")
    public String when;
    @JsonProperty("manual")
    public Boolean manual;
    @JsonProperty("allow_failure")
    public Boolean allowFailure;
    @JsonProperty("user")
    public UserDTO user;
    @JsonProperty("runner")
    public Object runner;
    @JsonProperty("artifacts_file")
    public ArtifactFileDTO artifactsFile;
}

@JsonInclude(JsonInclude.Include.NON_NULL)
class ArtifactFileDTO {

    @JsonProperty("filename")
    public String filename;
    @JsonProperty("size")
    public Double size;

}
