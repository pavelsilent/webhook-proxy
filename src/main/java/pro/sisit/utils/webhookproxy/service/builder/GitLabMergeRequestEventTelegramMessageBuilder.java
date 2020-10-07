package pro.sisit.utils.webhookproxy.service.builder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.WebhookEvent;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.MergeRequestModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.ProjectModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.UserModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.event.MergeRequestEvent;
import pro.sisit.utils.webhookproxy.domain.model.telegram.Message;
import pro.sisit.utils.webhookproxy.service.format.TelegramMessageFormatterHTML;

@Service
@RequiredArgsConstructor
public class GitLabMergeRequestEventTelegramMessageBuilder implements TelegramMessageBuilder<MergeRequestEvent> {

    private final TelegramMessageFormatterHTML messageFormatter;

    @Override
    public Message toMessage(MergeRequestEvent event) {
        ProjectModel project = event.getProject();
        MergeRequestModel merge = event.getMergeRequest();
        UserModel user = event.getUser();

        return toMessage(
                String.format("Project %s\n" +
                                "User %s %s merge request %s\n" +
                                "from branch %s to branch %s.",
                        messageFormatter.link(project.getUrl(), project.getName()),
                        messageFormatter.bold(user.getName()),
                        merge.getState(),
                        messageFormatter.link(merge.getUrl(), merge.getTitle()),
                        messageFormatter.bold(
                                messageFormatter.underline(merge.getSourceBranch())),
                        messageFormatter.bold(
                                messageFormatter.underline(merge.getTargetBranch()))),
                messageFormatter.getParseMode());
    }

    @Override
    public <E extends WebhookEvent> boolean supports(E event) {
        return event instanceof MergeRequestEvent;
    }
}
