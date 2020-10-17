package pro.sisit.utils.webhookproxy.domain.model.rule.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pro.sisit.utils.webhookproxy.domain.enumeration.SystemFilterEnum;
import pro.sisit.utils.webhookproxy.domain.model.rule.FilterModel;

@EqualsAndHashCode(callSuper = true)
@Data
public class SystemFilterModel extends FilterModel {

    private SystemFilterEnum value;
}
