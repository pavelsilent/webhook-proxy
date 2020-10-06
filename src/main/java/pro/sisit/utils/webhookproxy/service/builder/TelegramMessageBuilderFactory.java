package pro.sisit.utils.webhookproxy.service.builder;

import com.pengrad.telegrambot.model.request.ParseMode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TelegramMessageBuilderFactory {

    private final List<TelegramMessageBuilder> services;

    public String toMessage(Object event) {
        return Optional.ofNullable(getService(event))
                .map(telegramMessageBuilder -> telegramMessageBuilder.toMessage(event))
                .orElse(null);
    }

    public ParseMode getParseMode(Object event) {
        return Optional.ofNullable(getService(event))
                .map(TelegramMessageBuilder::getParseMode)
                .orElse(null);
    }

    private TelegramMessageBuilder getService(Object event) {
        return services.stream().filter(telegramMessageBuilder -> telegramMessageBuilder.supports(event))
                .findFirst().orElseThrow(() -> new RuntimeException("TelegramMessageBuilderFactory. Not supported event: " + event.getClass()));
    }
}
