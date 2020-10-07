package pro.sisit.utils.webhookproxy.service.builder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.WebhookEvent;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.CommentModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.MergeRequestModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.ProjectModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.UserModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.event.MergeRequestCommentEvent;
import pro.sisit.utils.webhookproxy.domain.model.telegram.Message;
import pro.sisit.utils.webhookproxy.service.format.TelegramMessageFormatterHTML;

@Service
@RequiredArgsConstructor
public class GitLabMergeRequestCommentEventTelegramMessageBuilder implements TelegramMessageBuilder<MergeRequestCommentEvent> {

    private final TelegramMessageFormatterHTML messageFormatter;

    @Override
    public Message toMessage(MergeRequestCommentEvent event) {
        ProjectModel project = event.getProject();
        MergeRequestModel merge = event.getMergeRequest();
        UserModel user = event.getUser();
        CommentModel comment = event.getComment();

        return toMessage(
                String.format("Project %s\n" +
                                "User %s add comment to %s %s\n" +
                                "   %s",
                        messageFormatter.link(project.getUrl(), project.getName()),
                        messageFormatter.bold(user.getName()),
                        messageFormatter.link(comment.getUrl(), comment.getTarget().getLabel()),
                        merge.getTitle(),
                        messageFormatter.italic(comment.getText())),
                messageFormatter.getParseMode());
    }

    @Override
    public <E extends WebhookEvent> boolean supports(E event) {
        return event instanceof MergeRequestCommentEvent;
    }
}
