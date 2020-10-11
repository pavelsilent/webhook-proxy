package pro.sisit.utils.webhookproxy.data;

import org.springframework.data.repository.CrudRepository;
import pro.sisit.utils.webhookproxy.domain.entity.filter.SystemFilter;

public interface SystemFilterRepository extends CrudRepository<SystemFilter, Long> {

}
