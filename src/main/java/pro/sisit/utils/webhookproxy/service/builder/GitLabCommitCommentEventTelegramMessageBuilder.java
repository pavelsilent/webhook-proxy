package pro.sisit.utils.webhookproxy.service.builder;

import com.pengrad.telegrambot.model.request.ParseMode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.CommentModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.CommitModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.ProjectModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.UserModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.event.CommitCommentEvent;
import pro.sisit.utils.webhookproxy.service.TelegramMessageFormatterHTML;

@Service
@RequiredArgsConstructor
public class GitLabCommitCommentEventTelegramMessageBuilder implements TelegramMessageBuilder<CommitCommentEvent> {

    private final TelegramMessageFormatterHTML messageFormatter;

    @Override
    public ParseMode getParseMode() {
        return messageFormatter.getParseMode();
    }

    @Override
    public String toMessage(CommitCommentEvent event) {
        ProjectModel project = event.getProject();
        CommitModel commit = event.getCommit();
        UserModel user = event.getUser();
        CommentModel comment = event.getComment();

        return String.format("Project %s\n" +
                        "User %s add comment to %s %s\n" +
                        "   %s",
                messageFormatter.link(project.getUrl(), project.getName()),
                messageFormatter.bold(user.getName()),
                messageFormatter.link(comment.getUrl(), comment.getTarget().getLabel()),
                commit.getExternalId(),
                messageFormatter.italic(comment.getText()));
    }

    @Override
    public boolean supports(Object event) {
        return event instanceof CommitCommentEvent;
    }
}
