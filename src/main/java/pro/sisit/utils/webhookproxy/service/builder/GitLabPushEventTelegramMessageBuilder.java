package pro.sisit.utils.webhookproxy.service.builder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.Event;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.ProjectModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.UserModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.event.PushEvent;
import pro.sisit.utils.webhookproxy.domain.model.telegram.Message;
import pro.sisit.utils.webhookproxy.service.format.TelegramMessageFormatterHTML;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GitLabPushEventTelegramMessageBuilder implements TelegramMessageBuilder<PushEvent> {

    private final TelegramMessageFormatterHTML messageFormatter;

    @Override
    public Message toMessage(PushEvent event) {
        ProjectModel project = event.getProject();
        UserModel user = event.getUser();

        return toMessage(
                String.format("Project %s.%n" +
                                "User %s pushed %d commits%nto branch %s.%n" +
                                "Commits:%n" +
                                "   %s",
                        messageFormatter.link(project.getUrl(), project.getFullName()),
                        messageFormatter.bold(user.getName()),
                        event.getTotalCommitsCount(),
                        messageFormatter.underline(messageFormatter.bold(event.getBranch())),
                        messageFormatter.italic(event.getCommits().stream()
                                .map(commitModel ->
                                        String.format("   @%s: %s",
                                                commitModel.getAuthor().getName(),
                                                messageFormatter.link(commitModel.getUrl(), commitModel.getShortMessage())))
                                .collect(Collectors.joining("\n   ")))),
                messageFormatter.getParseMode());
    }

    @Override
    public <E extends Event> boolean supports(E event) {
        return event instanceof PushEvent;
    }
}
