package pro.sisit.utils.webhookproxy.service;

import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.response.SendResponse;
import pro.sisit.utils.webhookproxy.domain.TelegramGroup;

public interface TelegramSender {

    SendResponse send(TelegramGroup telegramGroup, String text, ParseMode parseMode);
}
