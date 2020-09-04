package pro.sisit.utils.webhookproxy.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TelegramGroup implements Target {

    private String botId;

    private String channelId;
}
