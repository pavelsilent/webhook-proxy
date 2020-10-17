package pro.sisit.utils.webhookproxy.data;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import pro.sisit.utils.webhookproxy.domain.entity.TelegramBot;
import pro.sisit.utils.webhookproxy.domain.entity.target.TelegramChannel;

public interface TelegramChannelRepository extends CrudRepository<TelegramChannel, Long> {

    Optional<TelegramChannel> findFirstByBotAndChannelId(TelegramBot bot, String channelId);
}
