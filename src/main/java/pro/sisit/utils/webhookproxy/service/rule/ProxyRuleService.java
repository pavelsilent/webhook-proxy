package pro.sisit.utils.webhookproxy.service.rule;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.data.ProxyRuleFilterRepository;
import pro.sisit.utils.webhookproxy.data.ProxyRuleRepository;
import pro.sisit.utils.webhookproxy.domain.entity.ProxyRule;
import pro.sisit.utils.webhookproxy.domain.entity.ProxyRuleFilter;
import pro.sisit.utils.webhookproxy.domain.entity.Target;
import pro.sisit.utils.webhookproxy.domain.model.rule.ProxyRuleInitialModel;

@Service
@RequiredArgsConstructor
public class ProxyRuleService {

    private final TargetServiceFactory targetService;

    private final FilterServiceFactory filterService;

    private final ProxyRuleRepository proxyRuleRepository;

    private final ProxyRuleFilterRepository proxyRuleFilterRepository;

    public ProxyRule create(ProxyRuleInitialModel model) {
        Target target = targetService.ensure(model.getTarget());

        ProxyRule proxyRule = new ProxyRule();
        proxyRule.setSource(model.getSource());
        proxyRule.setTarget(target);
        proxyRuleRepository.save(proxyRule);

        Optional.ofNullable(model.getFilters())
                .ifPresent(filterModels -> filterModels
                    .stream()
                    .map(filterService::ensure)
                    .forEach(filter -> {
                        ProxyRuleFilter proxyRuleFilter = new ProxyRuleFilter();
                        proxyRuleFilter.setRule(proxyRule);
                        proxyRuleFilter.setFilter(filter);
                        proxyRuleFilterRepository.save(proxyRuleFilter);
                    }));

        return proxyRule;
    }
}
