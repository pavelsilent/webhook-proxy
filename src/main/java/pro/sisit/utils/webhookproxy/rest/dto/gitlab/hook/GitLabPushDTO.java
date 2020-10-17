package pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.CommitFullDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.ProjectDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.RepositoryFullDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GitLabPushDTO extends GitLabDTO {

    @JsonProperty("before")
    public String before;
    @JsonProperty("after")
    public String after;
    @JsonProperty("ref")
    public String ref;
    @JsonProperty("checkout_sha")
    public String checkoutSha;
    @JsonProperty("user_id")
    public Integer userId;
    @JsonProperty("user_name")
    public String userName;
    @JsonProperty("user_username")
    public String userUsername;
    @JsonProperty("user_email")
    public String userEmail;
    @JsonProperty("user_avatar")
    public String userAvatar;
    @JsonProperty("project_id")
    public Integer projectId;
    @JsonProperty("project")
    public ProjectDTO project;
    @JsonProperty("repository")
    public RepositoryFullDTO repository;
    @JsonProperty("commits")
    public List<CommitFullDTO> commits;
    @JsonProperty("total_commits_count")
    public Integer totalCommitsCount;
}
