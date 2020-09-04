package pro.sisit.utils.webhookproxy.service;

import pro.sisit.utils.webhookproxy.domain.TelegramGroup;

public interface TelegramSender {

    void send(TelegramGroup telegramGroup, String text);
}
