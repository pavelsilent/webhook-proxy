package pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectDTO {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("description")
    public String description;
    @JsonProperty("web_url")
    public String webUrl;
    @JsonProperty("avatar_url")
    public String avatarUrl;
    @JsonProperty("git_ssh_url")
    public String gitSshUrl;
    @JsonProperty("git_http_url")
    public String gitHttpUrl;
    @JsonProperty("namespace")
    public String namespace;
    @JsonProperty("visibility_level")
    public Integer visibilityLevel;
    @JsonProperty("path_with_namespace")
    public String pathWithNamespace;
    @JsonProperty("ci_config_path")
    public String ciConfigPath;
    @JsonProperty("default_branch")
    public String defaultBranch;
    @JsonProperty("homepage")
    public String homepage;
    @JsonProperty("url")
    public String url;
    @JsonProperty("ssh_url")
    public String sshUrl;
    @JsonProperty("http_url")
    public String httpUrl;

}
