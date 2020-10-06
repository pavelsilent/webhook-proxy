package pro.sisit.utils.webhookproxy.rest.dto.jenkins;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * WebHook generate by
 * jenkinsci/outbound-webhook-plugin
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JenkinsBuildEventDTO {

    @JsonProperty("buildName")
    public String name;
    @JsonProperty("buildUrl")
    public String url;
    @JsonProperty("event")
    public String status;
    @JsonProperty("projectName")
    public String projectName;
    @JsonProperty("buildVars")
    public String buildVars;

}
