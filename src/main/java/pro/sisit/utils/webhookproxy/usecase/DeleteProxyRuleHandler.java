package pro.sisit.utils.webhookproxy.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.service.cqs.CommandHandler;
import pro.sisit.utils.webhookproxy.service.rule.ProxyRuleService;

@Service
@RequiredArgsConstructor
public class DeleteProxyRuleHandler implements CommandHandler<DeleteProxyRuleCommand, Long> {

    private final ProxyRuleService proxyRuleService;

    @Override
    public Long process(DeleteProxyRuleCommand command) {
        proxyRuleService.delete(command.getProxyRuleId());
        return command.getProxyRuleId();
    }

    @Override
    public Class getCommandClass() {
        return DeleteProxyRuleCommand.class;
    }
}
