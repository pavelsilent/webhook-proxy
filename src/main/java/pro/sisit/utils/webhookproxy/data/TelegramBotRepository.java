package pro.sisit.utils.webhookproxy.data;

import org.springframework.data.repository.CrudRepository;
import pro.sisit.utils.webhookproxy.domain.entity.TelegramBot;

import java.util.List;

public interface TelegramBotRepository extends CrudRepository<TelegramBot, Long> {
    List<TelegramBot> findAll();
}
