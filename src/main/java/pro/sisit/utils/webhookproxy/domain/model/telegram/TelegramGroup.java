package pro.sisit.utils.webhookproxy.domain.model.telegram;

import lombok.Builder;
import lombok.Data;
import pro.sisit.utils.webhookproxy.domain.Target;

@Data
@Builder
public class TelegramGroup implements Target {

    private String botId;

    private String channelId;
}
