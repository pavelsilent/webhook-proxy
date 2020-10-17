package pro.sisit.utils.webhookproxy.data;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import pro.sisit.utils.webhookproxy.domain.entity.filter.FieldValueFilter;

public interface FieldValueFilterRepository extends CrudRepository<FieldValueFilter, Long> {

    Optional<FieldValueFilter> findFirstByClassNameAndPropertyPathAndPropertyValue(String classname,
        String propertyPath, String propertyValue);
}
