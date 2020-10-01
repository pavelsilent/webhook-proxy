package pro.sisit.utils.webhookproxy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.*;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.event.CommitCommentEvent;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.event.MergeRequestCommentEvent;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.event.MergeRequestEvent;

@Service
@RequiredArgsConstructor
public class TelegramMessageBuilderImpl implements TelegramMessageBuilder {

    private final TelegramMessageFormatter messageFormatter;

    @Override
    public String toMessage(MergeRequestEvent event) {
        ProjectModel project = event.getProject();
        MergeRequestModel merge = event.getMergeRequest();
        UserModel user = event.getUser();

        return String.format("Project %s\n" +
                        "User %s %s merge request %s\n" +
                        "from branch %s to branch %s\n",
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
    public String toMessage(MergeRequestCommentEvent event) {
        ProjectModel project = event.getProject();
        MergeRequestModel merge = event.getMergeRequest();
        UserModel user = event.getUser();
        CommentModel comment = event.getComment();

        return String.format("Project %s\n" +
                        "User %s add comment to %s %s\n" +
                        "   %s",
                messageFormatter.link(project.getUrl(), project.getName()),
                messageFormatter.bold(user.getName()),
                messageFormatter.link(comment.getUrl(), comment.getTarget().getLabel()),
                merge.getTitle(),
                messageFormatter.italic(comment.getText()));
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


}
