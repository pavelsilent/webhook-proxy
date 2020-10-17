package pro.sisit.utils.webhookproxy.domain.model.rule;

import lombok.Data;
import pro.sisit.utils.webhookproxy.domain.enumeration.TargetTypeEnum;

@Data
public class TargetModel {

    private TargetTypeEnum target;
}
