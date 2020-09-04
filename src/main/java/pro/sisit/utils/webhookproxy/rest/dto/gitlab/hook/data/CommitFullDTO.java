package pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CommitFullDTO extends CommitDTO {

    @JsonProperty("title")
    public String title;
    @JsonProperty("added")
    public List<String> added;
    @JsonProperty("modified")
    public List<String> modified;
    @JsonProperty("removed")
    public List<String> removed;
}
