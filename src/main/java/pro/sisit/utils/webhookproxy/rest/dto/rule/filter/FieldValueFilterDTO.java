package pro.sisit.utils.webhookproxy.rest.dto.rule.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import pro.sisit.utils.webhookproxy.rest.dto.rule.FilterDTO;


public class FieldValueFilterDTO extends FilterDTO {

    @JsonProperty("class_name")
    public String className;

    @JsonProperty("property_path")
    public String propertyPath;

    @JsonProperty("property_value")
    public String propertyValue;

}
