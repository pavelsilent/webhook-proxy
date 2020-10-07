package pro.sisit.utils.webhookproxy.usecase;

import lombok.Data;
import pro.sisit.utils.webhookproxy.domain.WebhookEvent;
import pro.sisit.utils.webhookproxy.domain.cqs.Command;

@Data
public class ReceiveWebhookCommand implements Command {

    private WebhookEvent webhookEvent;
}
