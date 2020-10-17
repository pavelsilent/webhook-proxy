package pro.sisit.utils.webhookproxy.data;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import pro.sisit.utils.webhookproxy.domain.entity.TelegramBot;

public interface TelegramBotRepository extends CrudRepository<TelegramBot, Long> {

    List<TelegramBot> findAll();

    Optional<TelegramBot> findFirstByToken(String token);
}
