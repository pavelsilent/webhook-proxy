package pro.sisit.utils.webhookproxy.data;

import org.springframework.data.repository.CrudRepository;
import pro.sisit.utils.webhookproxy.domain.Source;
import pro.sisit.utils.webhookproxy.domain.entity.ProxyRule;

import java.util.List;

public interface ProxyRuleRepository extends CrudRepository<ProxyRule, Long> {

    List<ProxyRule> findAllBySource(Source source);
}
