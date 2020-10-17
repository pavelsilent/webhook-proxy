package pro.sisit.utils.webhookproxy.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.entity.ProxyRule;
import pro.sisit.utils.webhookproxy.service.cqs.CommandHandler;
import pro.sisit.utils.webhookproxy.service.rule.ProxyRuleService;

@Service
@RequiredArgsConstructor
public class ProxyRuleCreateHandler implements CommandHandler<ProxyRuleCreateCommand, Long> {

    private final ProxyRuleService proxyRuleService;

    @Override
    public Long process(ProxyRuleCreateCommand command) {
        ProxyRule proxyRule = proxyRuleService.create(command.getModel());
        return proxyRule.getId();
    }

    @Override
    public Class getCommandClass() {
        return ProxyRuleCreateCommand.class;
    }
}
