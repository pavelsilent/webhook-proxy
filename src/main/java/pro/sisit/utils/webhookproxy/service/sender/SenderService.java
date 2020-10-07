package pro.sisit.utils.webhookproxy.service.sender;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.entity.Target;
import pro.sisit.utils.webhookproxy.domain.model.telegram.Message;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SenderService {

    private final List<TargetSender> senders;

    @SuppressWarnings("unchecked")
    public <T extends Target, R> R send(T target, Message message) {
        return (R) senders.stream()
                .filter(sender -> sender.supports(target))
                .findFirst()
                .map(sender -> sender.send(target, message))
                .orElseThrow(() -> new RuntimeException(
                        String.format("Not found sender for target '%s'.",
                                target.getClass().getSimpleName())));
    }


}
