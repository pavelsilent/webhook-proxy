package pro.sisit.utils.webhookproxy.rest.dto.rule.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import pro.sisit.utils.webhookproxy.rest.dto.rule.FilterDTO;

public class ClassNameFilterDTO extends FilterDTO {

    @JsonProperty("class_name")
    public String className;
}
