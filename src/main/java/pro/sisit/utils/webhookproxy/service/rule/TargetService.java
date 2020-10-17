package pro.sisit.utils.webhookproxy.service.rule;

import pro.sisit.utils.webhookproxy.domain.entity.Target;
import pro.sisit.utils.webhookproxy.domain.model.rule.TargetModel;

public interface TargetService<TModel extends TargetModel> {

    Target ensure(TModel model);

    boolean supports(Class modelClass);

    void deleteAll();

}
