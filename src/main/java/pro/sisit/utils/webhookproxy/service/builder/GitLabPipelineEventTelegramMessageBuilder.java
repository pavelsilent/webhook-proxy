package pro.sisit.utils.webhookproxy.service.builder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.Event;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.*;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.enumeration.PipelineSource;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.event.PipelineEvent;
import pro.sisit.utils.webhookproxy.domain.model.telegram.Message;
import pro.sisit.utils.webhookproxy.service.format.TelegramMessageFormatterHTML;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class GitLabPipelineEventTelegramMessageBuilder implements TelegramMessageBuilder<PipelineEvent> {

    private final TelegramMessageFormatterHTML messageFormatter;

    private final Map<PipelineSource, Function<PipelineEvent, Message>> map = init();

    private Map<PipelineSource, Function<PipelineEvent, Message>> init() {
        Map<PipelineSource, Function<PipelineEvent, Message>> map = new HashMap<>();
        map.put(PipelineSource.PUSH, this::toCommitPipelineMessage);
        map.put(PipelineSource.MERGE_REQUEST, this::toMergeRequestPipelineMessage);
        map.put(PipelineSource.EXTERNAL, this::toCommitExternalPipelineMessage);
        return map;
    }

    @Override
    public Message toMessage(PipelineEvent event) {
        return Optional.ofNullable(event)
                .map(PipelineEvent::getPipeline)
                .map(PipelineModel::getSource)
                .filter(map::containsKey)
                .map(map::get)
                .map(pipelineEventMessageFunction -> pipelineEventMessageFunction.apply(event))
                .orElseGet(Message::empty);
    }

    private Message toMergeRequestPipelineMessage(PipelineEvent event) {
        ProjectModel project = event.getProject();
        UserModel user = event.getUser();
        PipelineModel pipeline = event.getPipeline();
        MergeRequestShortModel merge = event.getMergeRequest();

        return toMessage(
                String.format("Project %s.%n" +
                                "Pipeline initialized by user %s%n" +
                                "by merge request %s from branch %s to branch %s%n" +
                                "has %s status.",
                        messageFormatter.link(project.getUrl(), project.getFullName()),
                        messageFormatter.bold(user.getName()),
                        messageFormatter.bold(messageFormatter.link(merge.getUrl(), merge.getShortMessage())),
                        messageFormatter.bold(messageFormatter.underline(merge.getSourceBranch())),
                        messageFormatter.bold(messageFormatter.underline(merge.getTargetBranch())),
                        messageFormatter.bold(messageFormatter.underline(pipeline.getStatus().getCode()))),
                messageFormatter.getParseMode());
    }

    private Message toCommitPipelineMessage(PipelineEvent event) {
        ProjectModel project = event.getProject();
        UserModel user = event.getUser();
        PipelineModel pipeline = event.getPipeline();
        CommitModel commit = event.getCommit();

        return toMessage(
                String.format("Project %s.%n" +
                                "Pipeline initialized by user %s%n" +
                                "by commit %s to branch %s%n" +
                                "has %s status.",
                        messageFormatter.link(project.getUrl(), project.getFullName()),
                        messageFormatter.bold(user.getName()),
                        messageFormatter.bold(messageFormatter.link(commit.getUrl(), commit.getShortMessage())),
                        messageFormatter.bold(messageFormatter.underline(pipeline.getRef())),
                        messageFormatter.bold(messageFormatter.underline(pipeline.getStatus().getCode()))),
                messageFormatter.getParseMode());
    }

    private Message toCommitExternalPipelineMessage(PipelineEvent event) {
        ProjectModel project = event.getProject();
        UserModel user = event.getUser();
        PipelineModel pipeline = event.getPipeline();
        CommitModel commit = event.getCommit();

        return toMessage(
                String.format("Project %s.%n" +
                                "External pipeline initialized by user %s%n" +
                                "by commit %s to branch %s%n" +
                                "has %s status.",
                        messageFormatter.link(project.getUrl(), project.getFullName()),
                        messageFormatter.bold(user.getName()),
                        messageFormatter.bold(messageFormatter.link(commit.getUrl(), commit.getShortMessage())),
                        messageFormatter.bold(messageFormatter.underline(pipeline.getRef())),
                        messageFormatter.bold(messageFormatter.underline(pipeline.getStatus().getCode()))),
                messageFormatter.getParseMode());
    }

    @Override
    public <E extends Event> boolean supports(E event) {
        return event instanceof PipelineEvent;
    }
}
