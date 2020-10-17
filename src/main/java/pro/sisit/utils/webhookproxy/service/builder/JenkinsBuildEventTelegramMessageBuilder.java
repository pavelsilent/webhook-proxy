package pro.sisit.utils.webhookproxy.service.builder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.Event;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.BuildEvent;
import pro.sisit.utils.webhookproxy.domain.model.telegram.Message;
import pro.sisit.utils.webhookproxy.service.format.TelegramMessageFormatterHTML;

@Service
@RequiredArgsConstructor
public class JenkinsBuildEventTelegramMessageBuilder implements TelegramMessageBuilder<BuildEvent> {

    private final TelegramMessageFormatterHTML messageFormatter;

    @Override
    public Message toMessage(BuildEvent event) {
        return toMessage(
                String.format("Project %s.%n%s has %s status.",
                        event.getProjectName(),
                        messageFormatter.link(event.getUrl(), String.format("Build %s", event.getName())),
                        messageFormatter.underline(messageFormatter.bold(event.getStatus().name()))),
                messageFormatter.getParseMode());
    }

    @Override
    public <E extends Event> boolean supports(E event) {
        return event instanceof BuildEvent;
    }

}
