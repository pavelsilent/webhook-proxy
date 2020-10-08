package pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabCommentTargetDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommitDTO implements GitLabCommentTargetDTO {

    @JsonProperty("id")
    public String id;
    @JsonProperty("message")
    public String message;
    @JsonProperty("timestamp")
    public String timestamp;
    @JsonProperty("url")
    public String url;
    @JsonProperty("author")
    public AuthorDTO author;
}

