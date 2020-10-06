package pro.sisit.utils.webhookproxy.service;

import com.pengrad.telegrambot.model.request.ParseMode;

public interface TelegramMessageFormatter {

    String bold(String text);

    String underline(String text);

    String italic(String text);

    String strike(String text);

    default String link(String url) {
        return link(url, url);
    }

    String link(String url, String text);

    ParseMode getParseMode();
}
