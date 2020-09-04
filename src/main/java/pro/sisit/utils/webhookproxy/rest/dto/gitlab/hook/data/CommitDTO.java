package pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.WebHookCommentTargetDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommitDTO implements WebHookCommentTargetDTO {

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

@JsonInclude(JsonInclude.Include.NON_NULL)
class AuthorDTO {

    @JsonProperty("name")
    public String name;
    @JsonProperty("email")
    public String email;
}

