package pro.sisit.utils.webhookproxy.service.builder;

import com.pengrad.telegrambot.model.request.ParseMode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.BuildEvent;
import pro.sisit.utils.webhookproxy.service.TelegramMessageFormatterHTML;

@Service
@RequiredArgsConstructor
public class JenkinsBuildEventTelegramMessageBuilder implements TelegramMessageBuilder<BuildEvent> {

    private final TelegramMessageFormatterHTML messageFormatter;

    @Override
    public ParseMode getParseMode() {
        return messageFormatter.getParseMode();
    }

    @Override
    public String toMessage(BuildEvent event) {
        return String.format("Project %s%n%s has %s status.",
                event.getProjectName(),
                messageFormatter.link(event.getUrl(), String.format("Build %s", event.getName())),
                messageFormatter.underline(messageFormatter.bold(event.getStatus().name())));
    }

    @Override
    public boolean supports(Object event) {
        return event instanceof BuildEvent;
    }
}
