package pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommitInfoDTO {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("sha")
    public String sha;
    @JsonProperty("message")
    public String message;
    @JsonProperty("author_name")
    public String authorName;
    @JsonProperty("author_email")
    public String authorEmail;
    @JsonProperty("status")
    public String status;
    @JsonProperty("started_at")
    public String startedAt;
    @JsonProperty("finished_at")
    public String finishedAt;
    @JsonProperty("duration")
    public Integer duration;
}

