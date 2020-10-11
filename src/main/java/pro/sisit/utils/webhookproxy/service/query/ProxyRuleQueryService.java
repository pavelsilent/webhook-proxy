package pro.sisit.utils.webhookproxy.service.query;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.data.ProxyRuleFilterRepository;
import pro.sisit.utils.webhookproxy.data.ProxyRuleRepository;
import pro.sisit.utils.webhookproxy.domain.Event;
import pro.sisit.utils.webhookproxy.domain.entity.Filter;
import pro.sisit.utils.webhookproxy.domain.entity.ProxyRule;
import pro.sisit.utils.webhookproxy.domain.entity.Target;
import pro.sisit.utils.webhookproxy.domain.entity.filter.ClassNameFilter;
import pro.sisit.utils.webhookproxy.domain.entity.filter.FieldValueFilter;
import pro.sisit.utils.webhookproxy.domain.entity.filter.SystemFilter;
import pro.sisit.utils.webhookproxy.domain.enumeration.SystemFilterEnum;
import pro.sisit.utils.webhookproxy.util.HibernateUtil;
import pro.sisit.utils.webhookproxy.util.ObjectUtil;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
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
        Filter unproxyFilter = HibernateUtil.unProxyObject(filter);
        if (unproxyFilter == null || event == null) {
            return false;
        }

        if (unproxyFilter instanceof SystemFilter) {
            SystemFilterEnum type = ((SystemFilter) unproxyFilter).getType();
            switch (type) {
                case ALL:
                    return true;
                case NOTHING:
                    return false;
                default:
                    log.error("Unexpected system filter type: " + type);
                    return false;
            }
        } else if (unproxyFilter instanceof ClassNameFilter) {
            ClassNameFilter classNameFilter = (ClassNameFilter) unproxyFilter;
            String className = classNameFilter.getClassName();
            return Optional.of(event)
                    .map(Event::getClass)
                    .map(Class::getCanonicalName)
                    .map(className::equals)
                    .orElse(false);
        } else if (unproxyFilter instanceof FieldValueFilter) {
            FieldValueFilter fieldValueFilter = (FieldValueFilter) unproxyFilter;
            String className = fieldValueFilter.getClassName();
            if (!event.getClass().getCanonicalName().equals(className)) {
                return false;
            }

            Object fieldValue = ObjectUtil.getFieldValue(event, fieldValueFilter.getPropertyPath());
            String fieldValueAsString = fieldValue.toString();

            return fieldValueFilter.getPropertyValue().equals(fieldValueAsString);
        }
        log.error("Unexpected filter: " + unproxyFilter.getClass());
        return false;
    }
}
