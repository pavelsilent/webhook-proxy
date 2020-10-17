package pro.sisit.utils.webhookproxy.service.rule;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.entity.Filter;
import pro.sisit.utils.webhookproxy.domain.model.rule.FilterModel;

@Service
@RequiredArgsConstructor
public class FilterServiceFactory {

    private final List<FilterService> services;

    public Filter ensure(FilterModel model) {
        return services
            .stream()
            .filter(filterService -> filterService.supports(model.getClass()))
            .findFirst()
            .map(filterService -> filterService.ensure(model))
            .orElseThrow(() ->
                new RuntimeException("Not support filter " + model.getClass().getSimpleName()));
    }

    public void deleteAll() {
        services.forEach(FilterService::deleteAll);
    }


}
