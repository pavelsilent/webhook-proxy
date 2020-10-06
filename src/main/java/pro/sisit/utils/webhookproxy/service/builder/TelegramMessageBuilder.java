package pro.sisit.utils.webhookproxy.service.builder;

import com.pengrad.telegrambot.model.request.ParseMode;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.event.CommitCommentEvent;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.event.MergeRequestCommentEvent;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.event.MergeRequestEvent;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.event.PipelineEvent;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.BuildEvent;

public interface TelegramMessageBuilder<T> {

    ParseMode getParseMode();

    String toMessage(T event);

    boolean supports(Object event);
}
