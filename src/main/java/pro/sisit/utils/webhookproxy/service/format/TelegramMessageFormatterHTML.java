package pro.sisit.utils.webhookproxy.service.format;

import com.pengrad.telegrambot.model.request.ParseMode;
import org.springframework.stereotype.Service;

@Service
public class TelegramMessageFormatterHTML implements TelegramMessageFormatter {
    @Override
    public String bold(String text) {
        return String.format("<b>%s</b>", text);
    }

    @Override
    public String underline(String text) {
        return String.format("<u>%s</u>", text);
    }

    @Override
    public String italic(String text) {
        return String.format("<i>%s</i>", text);
    }

    @Override
    public String strike(String text) {
        return String.format("<s>%s</s>", text);
    }

    @Override
    public String link(String url, String text) {
        return String.format("<a href='%s'>%s</a>", url, text);
    }

    @Override
    public ParseMode getParseMode() {
        return ParseMode.HTML;
    }
}
