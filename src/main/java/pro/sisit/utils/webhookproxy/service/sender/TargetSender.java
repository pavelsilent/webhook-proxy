package pro.sisit.utils.webhookproxy.service.sender;

import com.pengrad.telegrambot.response.SendResponse;
import pro.sisit.utils.webhookproxy.domain.entity.Target;
import pro.sisit.utils.webhookproxy.domain.model.telegram.Message;

public interface TargetSender<T extends Target, R> {

    R send(T target, Message message);

    <K extends Target> boolean supports(K target);
}
