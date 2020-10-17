package pro.sisit.utils.webhookproxy.rest.dto.rule;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import pro.sisit.utils.webhookproxy.domain.Source;

public class ProxyRuleInitialDTO {

    @JsonProperty("source")
    public Source source;

    @JsonProperty("target")
    public TargetDTO target;

    @JsonProperty("filters")
    public List<FilterDTO> filters;
}
