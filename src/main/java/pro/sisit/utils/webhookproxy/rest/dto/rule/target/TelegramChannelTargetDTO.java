package pro.sisit.utils.webhookproxy.rest.dto.rule.target;

import com.fasterxml.jackson.annotation.JsonProperty;
import pro.sisit.utils.webhookproxy.rest.dto.rule.TargetDTO;


public class TelegramChannelTargetDTO extends TargetDTO {

    @JsonProperty("bot_token")
    public String botToken;

    @JsonProperty("channel_id")
    public String channelId;
}
