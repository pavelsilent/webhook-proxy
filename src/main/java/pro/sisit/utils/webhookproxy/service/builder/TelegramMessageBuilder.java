package pro.sisit.utils.webhookproxy.service.builder;

import com.pengrad.telegrambot.model.request.ParseMode;
import pro.sisit.utils.webhookproxy.domain.Event;
import pro.sisit.utils.webhookproxy.domain.model.telegram.Message;

public interface TelegramMessageBuilder<T extends Event> {

    Message toMessage(T event);

    default Message toMessage(String payload, ParseMode parseMode) {
        return Message.builder()
                .payload(payload)
                .parseMode(parseMode)
                .build();
    }

    <E extends Event> boolean supports(E event);
}
