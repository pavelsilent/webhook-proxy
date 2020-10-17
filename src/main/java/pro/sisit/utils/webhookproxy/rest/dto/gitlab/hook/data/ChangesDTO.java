package pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChangesDTO {

    @JsonProperty("updated_by_id")
    public IdChangesDTO updatedById;
    @JsonProperty("updated_at")
    public TimeStampChangesDTO updatedAt;
    @JsonProperty("labels")
    public LabelChangesDTO labels;

}

@JsonInclude(JsonInclude.Include.NON_NULL)
class IdChangesDTO {

    @JsonProperty("previous")
    public Integer previous;
    @JsonProperty("current")
    public Integer current;
}

@JsonInclude(JsonInclude.Include.NON_NULL)
class TimeStampChangesDTO {

    @JsonProperty("previous")
    public String previous;
    @JsonProperty("current")
    public String current;

}

@JsonInclude(JsonInclude.Include.NON_NULL)
class LabelChangesDTO {

    @JsonProperty("previous")
    public List<LabelDTO> previous = null;
    @JsonProperty("current")
    public List<LabelDTO> current = null;
}
