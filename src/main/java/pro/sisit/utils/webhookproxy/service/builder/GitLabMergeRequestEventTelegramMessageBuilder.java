package pro.sisit.utils.webhookproxy.service.builder;

import com.pengrad.telegrambot.model.request.ParseMode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.MergeRequestModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.ProjectModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.UserModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.event.MergeRequestEvent;
import pro.sisit.utils.webhookproxy.service.TelegramMessageFormatterHTML;

@Service
@RequiredArgsConstructor
public class GitLabMergeRequestEventTelegramMessageBuilder implements TelegramMessageBuilder<MergeRequestEvent> {

    private final TelegramMessageFormatterHTML messageFormatter;

    @Override
    public ParseMode getParseMode() {
        return messageFormatter.getParseMode();
    }

    @Override
    public String toMessage(MergeRequestEvent event) {
        ProjectModel project = event.getProject();
        MergeRequestModel merge = event.getMergeRequest();
        UserModel user = event.getUser();

        return String.format("Project %s\n" +
                        "User %s %s merge request %s\n" +
                        "from branch %s to branch %s.",
                messageFormatter.link(project.getUrl(), project.getName()),
                messageFormatter.bold(user.getName()),
                merge.getState(),
                messageFormatter.link(merge.getUrl(), merge.getTitle()),
                messageFormatter.bold(
                        messageFormatter.underline(merge.getSourceBranch())),
                messageFormatter.bold(
                        messageFormatter.underline(merge.getTargetBranch())));
    }

    @Override
    public boolean supports(Object event) {
        return event instanceof MergeRequestEvent;
    }
}
