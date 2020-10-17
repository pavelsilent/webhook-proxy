package pro.sisit.utils.webhookproxy.service.transform;

import pro.sisit.utils.webhookproxy.domain.model.rule.ProxyRuleInitialModel;
import pro.sisit.utils.webhookproxy.rest.dto.rule.ProxyRuleInitialDTO;

public interface RuleRestConverter {

    ProxyRuleInitialModel toModel(ProxyRuleInitialDTO dto);
}
