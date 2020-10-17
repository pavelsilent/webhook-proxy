package pro.sisit.utils.webhookproxy.domain.model.rule.target;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pro.sisit.utils.webhookproxy.domain.model.rule.TargetModel;

@EqualsAndHashCode(callSuper = true)
@Data
public class TelegramChannelTargetModel extends TargetModel {

    private String botToken;

    private String channelId;
}
