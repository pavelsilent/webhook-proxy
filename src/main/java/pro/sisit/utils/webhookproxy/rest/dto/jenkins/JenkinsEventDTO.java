package pro.sisit.utils.webhookproxy.rest.dto.jenkins;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import pro.sisit.utils.webhookproxy.rest.dto.jenkins.data.JenkinsBuildDTO;

/**
 * WebHook generate by
 * https://plugins.jenkins.io/notification/
 */


@JsonInclude(JsonInclude.Include.NON_NULL)
public class JenkinsEventDTO {

    /**
     * name=data-types
     */
    @JsonProperty("name")
    public String name;

    /**
     * display_name=data-types
     */
    @JsonProperty("display_name")
    public String displayName;

    /**
     * url=job/data-types/
     */
    @JsonProperty("url")
    public String url;

    @JsonProperty("build")
    public JenkinsBuildDTO build;
}
