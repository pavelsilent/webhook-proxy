package pro.sisit.utils.webhookproxy.service;

import pro.sisit.utils.webhookproxy.domain.model.gitlab.event.CommitCommentEvent;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.event.MergeRequestCommentEvent;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.event.MergeRequestEvent;

public interface TelegramMessageBuilder {

    String toMessage(MergeRequestEvent event);

    String toMessage(MergeRequestCommentEvent event);

    String toMessage(CommitCommentEvent event);
}
