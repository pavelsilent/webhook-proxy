package pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WikiDTO {

    @JsonProperty("web_url")
    public String webUrl;
    @JsonProperty("git_ssh_url")
    public String gitSshUrl;
    @JsonProperty("git_http_url")
    public String gitHttpUrl;
    @JsonProperty("path_with_namespace")
    public String pathWithNamespace;
    @JsonProperty("default_branch")
    public String defaultBranch;

}
