package pro.sisit.utils.webhookproxy.rest.dto.rule.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import pro.sisit.utils.webhookproxy.domain.enumeration.SystemFilterEnum;
import pro.sisit.utils.webhookproxy.rest.dto.rule.FilterDTO;

public class SystemFilterDTO extends FilterDTO {

    @JsonProperty("system_filter_value")
    public SystemFilterEnum value;
}
