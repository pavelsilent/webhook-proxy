package pro.sisit.utils.webhookproxy.service.rule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.data.SystemFilterRepository;
import pro.sisit.utils.webhookproxy.domain.entity.Filter;
import pro.sisit.utils.webhookproxy.domain.entity.filter.SystemFilter;
import pro.sisit.utils.webhookproxy.domain.model.rule.filter.SystemFilterModel;

@Service
@RequiredArgsConstructor
public class SystemFilterService implements FilterService<SystemFilterModel> {

    private final SystemFilterRepository repository;

    @Override
    public Filter ensure(SystemFilterModel model) {
        return repository.findFirstByType(model.getValue())
                         .orElseGet(() -> {
                             SystemFilter filter = new SystemFilter();
                             filter.setType(model.getValue());
                             return repository.save(filter);
                         });
    }

    @Override
    public boolean supports(Class modelClass) {
        return SystemFilterModel.class.equals(modelClass);
    }
}
