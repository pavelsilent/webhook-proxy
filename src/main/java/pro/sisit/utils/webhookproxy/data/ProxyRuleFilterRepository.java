package pro.sisit.utils.webhookproxy.data;

import org.springframework.data.repository.CrudRepository;
import pro.sisit.utils.webhookproxy.domain.Source;
import pro.sisit.utils.webhookproxy.domain.entity.ProxyRuleFilter;

import java.util.List;

public interface ProxyRuleFilterRepository extends CrudRepository<ProxyRuleFilter, Long> {

    List<ProxyRuleFilter> findAllByRuleId(Long sourceId);

    List<ProxyRuleFilter> findAllByRuleSource(Source source);
}
