package pro.sisit.utils.webhookproxy.service.rule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.data.ClassNameFilterRepository;
import pro.sisit.utils.webhookproxy.domain.entity.Filter;
import pro.sisit.utils.webhookproxy.domain.entity.filter.ClassNameFilter;
import pro.sisit.utils.webhookproxy.domain.model.rule.filter.ClassNameFilterModel;

@Service
@RequiredArgsConstructor
public class ClassNameFilterService implements FilterService<ClassNameFilterModel> {

    private final ClassNameFilterRepository repository;

    @Override
    public Filter ensure(ClassNameFilterModel model) {
        return repository.findFirstByClassName(model.getClassName())
                         .orElseGet(() -> {
                             ClassNameFilter filter = new ClassNameFilter();
                             filter.setClassName(model.getClassName());
                             return repository.save(filter);
                         });
    }

    @Override
    public boolean supports(Class modelClass) {
        return ClassNameFilterModel.class.equals(modelClass);
    }
}
