package pro.sisit.utils.webhookproxy.service.builder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.Event;
import pro.sisit.utils.webhookproxy.domain.model.telegram.Message;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TelegramMessageBuilderFactory {

    private final List<TelegramMessageBuilder> services;

    public <E extends Event> Message toMessage(E event) {
        return Optional.ofNullable(getService(event))
                .map(telegramMessageBuilder -> telegramMessageBuilder.toMessage(event))
                .orElse(null);
    }

    private <E extends Event> TelegramMessageBuilder getService(E event) {
        return services.stream().filter(telegramMessageBuilder -> telegramMessageBuilder.supports(event))
                .findFirst().orElseThrow(() -> new RuntimeException("TelegramMessageBuilderFactory. Not supported event: " + event.getClass()));
    }
}
