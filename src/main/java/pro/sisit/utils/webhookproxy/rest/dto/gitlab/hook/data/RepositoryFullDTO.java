package pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RepositoryFullDTO extends RepositoryDTO {

    @JsonProperty("git_http_url")
    public String gitHttpUrl;
    @JsonProperty("git_ssh_url")
    public String gitSshUrl;
    @JsonProperty("visibility_level")
    public Integer visibilityLevel;
}
