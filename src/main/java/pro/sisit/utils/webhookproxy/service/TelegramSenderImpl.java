package pro.sisit.utils.webhookproxy.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.TelegramGroup;

@Service
@RequiredArgsConstructor
public class TelegramSenderImpl implements TelegramSender {
    // https://api.telegram.org/bot1104514641:AAHgsNR4J4A9P0CZIo_dNn-nZWAevDMhWo4/
    // sendMessage?chat_id=-435910891&text=
    // Build%20%23${BUILD_NUMBER}%20of%20jetalon_master_application%20STARTED%20link%20${BUILD_URL}

    @Override
    public SendResponse send(TelegramGroup telegramGroup, String text, ParseMode parseMode) {
        TelegramBot bot = new TelegramBot(telegramGroup.getBotId());
        return bot.execute(
                new SendMessage(
                        telegramGroup.getChannelId(), text)
                        .parseMode(parseMode));
    }

}
