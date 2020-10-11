package pro.sisit.utils.webhookproxy.usecase;

import lombok.Data;
import pro.sisit.utils.webhookproxy.domain.Event;
import pro.sisit.utils.webhookproxy.domain.cqs.Command;

@Data
public class ReceiveEventCommand implements Command {

    private Event webhookEvent;
}
