package pro.sisit.utils.webhookproxy.usecase;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.service.cqs.CommandHandler;
import pro.sisit.utils.webhookproxy.service.rule.ProxyRuleService;

@Service
@RequiredArgsConstructor
public class DeleteAllDataHandler implements CommandHandler<DeleteAllDataCommand, Optional<Long>> {

    private final ProxyRuleService proxyRuleService;

    @Override
    public Optional<Long> process(DeleteAllDataCommand command) {
        proxyRuleService.deleteAllData(command.isOnlyRules());
        return Optional.empty();
    }

    @Override
    public Class getCommandClass() {
        return DeleteAllDataCommand.class;
    }
}
