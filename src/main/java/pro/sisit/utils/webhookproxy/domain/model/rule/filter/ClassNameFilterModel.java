package pro.sisit.utils.webhookproxy.domain.model.rule.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pro.sisit.utils.webhookproxy.domain.model.rule.FilterModel;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClassNameFilterModel extends FilterModel {

    public String className;
}
