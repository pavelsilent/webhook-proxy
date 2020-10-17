package pro.sisit.utils.webhookproxy.service.rule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.data.FieldValueFilterRepository;
import pro.sisit.utils.webhookproxy.domain.entity.Filter;
import pro.sisit.utils.webhookproxy.domain.entity.filter.FieldValueFilter;
import pro.sisit.utils.webhookproxy.domain.model.rule.filter.FieldValueFilterModel;

@Service
@RequiredArgsConstructor
public class FieldValueFilterService implements FilterService<FieldValueFilterModel> {

    private final FieldValueFilterRepository repository;

    @Override
    public Filter ensure(FieldValueFilterModel model) {
        return repository.findFirstByClassNameAndPropertyPathAndPropertyValue(
            model.getClassName(), model.getPropertyPath(), model.getPropertyValue())
                         .orElseGet(() -> {
                             FieldValueFilter filter = new FieldValueFilter();
                             filter.setClassName(model.getClassName());
                             filter.setPropertyPath(model.getPropertyPath());
                             filter.setPropertyValue(model.getPropertyValue());
                             return repository.save(filter);
                         });
    }

    @Override
    public boolean supports(Class modelClass) {
        return FieldValueFilterModel.class.equals(modelClass);
    }
}
