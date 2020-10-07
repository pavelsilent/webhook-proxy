package pro.sisit.utils.webhookproxy.service.builder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.WebhookEvent;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.MergeRequestShortModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.PipelineModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.ProjectModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.UserModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.event.PipelineEvent;
import pro.sisit.utils.webhookproxy.domain.model.telegram.Message;
import pro.sisit.utils.webhookproxy.service.format.TelegramMessageFormatterHTML;

@Service
@RequiredArgsConstructor
public class GitLabPipelineEventTelegramMessageBuilder implements TelegramMessageBuilder<PipelineEvent> {

    private final TelegramMessageFormatterHTML messageFormatter;

    @Override
    public Message toMessage(PipelineEvent event) {
        ProjectModel project = event.getProject();
        UserModel user = event.getUser();
        PipelineModel pipeline = event.getPipeline();
        MergeRequestShortModel merge = event.getMergeRequest();

        return toMessage(
                String.format("Project %s%n" +
                                "Pipeline started by user %s%n" +
                                "by %s merge request %s from branch %s to branch %s%n" +
                                "has %s status after %ssec.",
                        messageFormatter.link(project.getUrl(), project.getName()),
                        messageFormatter.bold(user.getName()),
                        merge.getState(), messageFormatter.link(merge.getUrl(), merge.getTitle()),
                        messageFormatter.bold(messageFormatter.underline(merge.getSourceBranch())),
                        messageFormatter.bold(messageFormatter.underline(merge.getTargetBranch())),
                        messageFormatter.bold(messageFormatter.underline(pipeline.getStatus().getCode())),
                        pipeline.getDuration()),
                messageFormatter.getParseMode());
    }

    @Override
    public <E extends WebhookEvent> boolean supports(E event) {
        return event instanceof PipelineEvent;
    }
}
