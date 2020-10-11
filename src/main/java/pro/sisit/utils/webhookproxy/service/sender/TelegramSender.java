package pro.sisit.utils.webhookproxy.service.sender;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.entity.Target;
import pro.sisit.utils.webhookproxy.domain.entity.target.TelegramChannel;
import pro.sisit.utils.webhookproxy.domain.model.telegram.Message;

@Service
@RequiredArgsConstructor
public class TelegramSender implements TargetSender<TelegramChannel, SendResponse> {
    // https://api.telegram.org/bot1104514641:AAHgsNR4J4A9P0CZIo_dNn-nZWAevDMhWo4/
    // sendMessage?chat_id=-435910891&text=
    // Build%20%23${BUILD_NUMBER}%20of%20jetalon_master_application%20STARTED%20link%20${BUILD_URL}

    @Override
    public SendResponse send(TelegramChannel target, Message message) {
        TelegramBot bot = new TelegramBot(target.getBot().getToken());
        return bot.execute(
                new SendMessage(
                        target.getChannelId(), message.getPayload())
                        .parseMode(message.getParseMode()));
    }

    @Override
    public boolean supports(Target target) {
        return target instanceof TelegramChannel;
    }
}
