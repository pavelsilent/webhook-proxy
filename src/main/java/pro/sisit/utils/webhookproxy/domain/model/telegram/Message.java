package pro.sisit.utils.webhookproxy.domain.model.telegram;

import com.pengrad.telegrambot.model.request.ParseMode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {

    private String payload;

    private ParseMode parseMode;
}
