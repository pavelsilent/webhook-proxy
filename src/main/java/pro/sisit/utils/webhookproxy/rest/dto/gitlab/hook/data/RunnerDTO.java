package pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RunnerDTO {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("description")
    public String description;
    @JsonProperty("active")
    public boolean active;
    @JsonProperty("is_shared")
    public boolean shared;
}
