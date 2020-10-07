package pro.sisit.utils.webhookproxy.domain.model.jenkins;

import lombok.Data;
import pro.sisit.utils.webhookproxy.domain.WebhookEvent;
import pro.sisit.utils.webhookproxy.domain.Source;

@Data
public class BuildEvent implements WebhookEvent {

    private String name;

    private String url;

    private BuildEventStatus status;

    private String projectName;

    private Source source;

}
