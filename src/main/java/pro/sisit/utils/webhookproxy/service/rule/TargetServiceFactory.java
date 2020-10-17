package pro.sisit.utils.webhookproxy.service.rule;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.entity.Target;
import pro.sisit.utils.webhookproxy.domain.model.rule.TargetModel;

@Service
@RequiredArgsConstructor
public class TargetServiceFactory {

    private final List<TargetService> services;

    public Target ensure(TargetModel model) {
        return services
            .stream()
            .filter(targetService -> targetService.supports(model.getClass()))
            .findFirst()
            .map(targetService -> targetService.ensure(model))
            .orElseThrow(() ->
                new RuntimeException("Not support target " + model.getClass().getSimpleName()));
    }


}
