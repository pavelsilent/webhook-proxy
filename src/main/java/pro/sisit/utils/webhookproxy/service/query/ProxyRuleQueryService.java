package pro.sisit.utils.webhookproxy.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.data.ProxyRuleFilterRepository;
import pro.sisit.utils.webhookproxy.data.ProxyRuleRepository;
import pro.sisit.utils.webhookproxy.domain.Event;
import pro.sisit.utils.webhookproxy.domain.entity.Filter;
import pro.sisit.utils.webhookproxy.domain.entity.ProxyRule;
import pro.sisit.utils.webhookproxy.domain.entity.Target;
import pro.sisit.utils.webhookproxy.domain.entity.filter.ClassNameFilter;
import pro.sisit.utils.webhookproxy.domain.entity.filter.SystemFilter;
import pro.sisit.utils.webhookproxy.domain.enumeration.SystemFilterEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProxyRuleQueryService {

    private final ProxyRuleRepository proxyRuleRepository;

    private final ProxyRuleFilterRepository proxyRuleFilterRepository;

    public List<Target> findTargets(Event event) {
        return Optional.ofNullable(event)
                .map(data -> proxyRuleRepository.findAllBySource(event.getSource())
                        .stream()
                        .filter(rule -> canProxy(rule, event))
                        .map(ProxyRule::getTarget)
                        .collect(Collectors.toList()))
                .orElseGet(ArrayList::new);

    }

    public boolean canProxy(ProxyRule rule, Event event) {
        return proxyRuleFilterRepository.findAllByRuleId(rule.getId()).stream()
                .anyMatch(proxyRuleFilter ->
                        isEventSatisfy(proxyRuleFilter.getFilter(), event));
    }

    public boolean isEventSatisfy(Filter filter, Event event) {
        if (filter == null || event == null) {
            return false;
        }

        if (filter instanceof SystemFilter) {
            SystemFilterEnum type = ((SystemFilter) filter).getType();
            switch (type) {
                case ALL:
                    return true;
                case NOTHING:
                    return false;
                default:
                    throw new IllegalStateException("Unexpected system filter type: " + type);
            }
        } else if (filter instanceof ClassNameFilter) {
            ClassNameFilter classNameFilter = (ClassNameFilter) filter;
            String className = classNameFilter.getClassName();
            return Optional.of(event)
                    .map(Event::getClass)
                    .map(Class::getCanonicalName)
                    .map(className::equals)
                    .orElse(false);
        }

        throw new IllegalStateException("Unexpected filter: " + filter.getClass());
    }
}
