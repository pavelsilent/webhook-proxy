package pro.sisit.utils.webhookproxy.rest.dto.rule;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import org.springframework.validation.annotation.Validated;
import pro.sisit.utils.webhookproxy.domain.enumeration.FilterTypeEnum;
import pro.sisit.utils.webhookproxy.rest.dto.rule.filter.ClassNameFilterDTO;
import pro.sisit.utils.webhookproxy.rest.dto.rule.filter.FieldValueFilterDTO;
import pro.sisit.utils.webhookproxy.rest.dto.rule.filter.SystemFilterDTO;

@Validated
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
    include = As.PROPERTY,
    property = "type",
    visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = SystemFilterDTO.class, name = "SYSTEM"),
    @JsonSubTypes.Type(value = ClassNameFilterDTO.class, name = "CLASS_NAME"),
    @JsonSubTypes.Type(value = FieldValueFilterDTO.class, name = "FIELD_VALUE"),
})
public class FilterDTO {

    @JsonProperty("type")
    public FilterTypeEnum type;
}
