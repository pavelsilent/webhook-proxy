package pro.sisit.utils.webhookproxy.service.format;

import com.pengrad.telegrambot.model.request.ParseMode;
import org.springframework.stereotype.Service;

@Service
public class TelegramMessageFormatterMarkDown implements TelegramMessageFormatter {
    @Override
    public String bold(String text) {
        return String.format("*%s*", text);
    }

    @Override
    public String underline(String text) {
        return String.format("__%s__", text);
    }

    @Override
    public String italic(String text) {
        return String.format("_%s_", text);
    }

    @Override
    public String strike(String text) {
        return String.format("~%s~", text);
    }

    @Override
    public String link(String url, String text) {
        return String.format("[%s](%s)", text, url);
    }

    @Override
    public ParseMode getParseMode() {
        return ParseMode.MarkdownV2;
    }
}
