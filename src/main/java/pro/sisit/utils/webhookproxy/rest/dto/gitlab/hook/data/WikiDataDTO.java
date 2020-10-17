package pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WikiDataDTO {

    @JsonProperty("title")
    public String title;
    @JsonProperty("content")
    public String content;
    @JsonProperty("format")
    public String format;
    @JsonProperty("message")
    public String message;
    @JsonProperty("slug")
    public String slug;
    @JsonProperty("url")
    public String url;
    @JsonProperty("action")
    public String action;

}
