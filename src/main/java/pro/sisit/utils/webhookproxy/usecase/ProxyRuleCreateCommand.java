package pro.sisit.utils.webhookproxy.usecase;

import lombok.Data;
import pro.sisit.utils.webhookproxy.domain.cqs.Command;
import pro.sisit.utils.webhookproxy.domain.model.rule.ProxyRuleInitialModel;

@Data
public class ProxyRuleCreateCommand implements Command {

    private ProxyRuleInitialModel model;
}
