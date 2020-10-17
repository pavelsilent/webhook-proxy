package pro.sisit.utils.webhookproxy.domain.model.rule;

import java.util.List;
import lombok.Data;
import pro.sisit.utils.webhookproxy.domain.Source;

@Data
public class ProxyRuleInitialModel {

    private List<FilterModel> filters;

    private Source source;

    private TargetModel target;
}
