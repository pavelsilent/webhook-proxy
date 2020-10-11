package pro.sisit.utils.webhookproxy.data;

import org.springframework.data.repository.CrudRepository;
import pro.sisit.utils.webhookproxy.domain.entity.target.TelegramChannel;

public interface TelegramChannelRepository extends CrudRepository<TelegramChannel, Long> {
}
