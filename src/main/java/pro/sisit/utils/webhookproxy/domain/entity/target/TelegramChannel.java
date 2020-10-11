package pro.sisit.utils.webhookproxy.domain.entity.target;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pro.sisit.utils.webhookproxy.domain.entity.Target;
import pro.sisit.utils.webhookproxy.domain.entity.TelegramBot;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "target_telegram_channel")
public class TelegramChannel extends Target {

    @Column(name = "channel_id", nullable = false)
    private String channelId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bot_id", nullable = false)
    private TelegramBot bot;

}
