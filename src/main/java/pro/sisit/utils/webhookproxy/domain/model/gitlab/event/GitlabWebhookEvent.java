package pro.sisit.utils.webhookproxy.domain.model.gitlab.event;

import lombok.Data;
import pro.sisit.utils.webhookproxy.domain.WebhookEvent;
import pro.sisit.utils.webhookproxy.domain.Source;

@Data
public class GitlabWebhookEvent implements WebhookEvent {

    @Override
    public Source getSource() {
        return Source.GITLAB;
    }
}
