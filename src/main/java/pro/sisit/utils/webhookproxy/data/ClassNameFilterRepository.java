package pro.sisit.utils.webhookproxy.data;

import org.springframework.data.repository.CrudRepository;
import pro.sisit.utils.webhookproxy.domain.entity.filter.ClassNameFilter;

public interface ClassNameFilterRepository extends CrudRepository<ClassNameFilter, Long> {
}
