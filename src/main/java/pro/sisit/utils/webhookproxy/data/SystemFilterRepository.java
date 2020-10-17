package pro.sisit.utils.webhookproxy.data;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import pro.sisit.utils.webhookproxy.domain.entity.filter.SystemFilter;
import pro.sisit.utils.webhookproxy.domain.enumeration.SystemFilterEnum;

public interface SystemFilterRepository extends CrudRepository<SystemFilter, Long> {

    Optional<SystemFilter> findFirstByType(SystemFilterEnum type);
}
