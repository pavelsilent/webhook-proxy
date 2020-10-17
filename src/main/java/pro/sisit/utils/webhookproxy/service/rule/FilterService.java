package pro.sisit.utils.webhookproxy.service.rule;

import pro.sisit.utils.webhookproxy.domain.Target;
import pro.sisit.utils.webhookproxy.domain.entity.Filter;
import pro.sisit.utils.webhookproxy.domain.model.rule.FilterModel;

public interface FilterService<TModel extends FilterModel> {

    Filter ensure(TModel model);

    boolean supports(Class modelClass);

     void deleteAll();
}
