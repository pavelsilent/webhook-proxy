package pro.sisit.utils.webhookproxy.domain.model.rule;

import lombok.Data;
import pro.sisit.utils.webhookproxy.domain.enumeration.FilterTypeEnum;

@Data
public class FilterModel {

    private FilterTypeEnum type;
}
