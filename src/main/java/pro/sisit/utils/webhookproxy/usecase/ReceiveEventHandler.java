package pro.sisit.utils.webhookproxy.usecase;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.model.telegram.Message;
import pro.sisit.utils.webhookproxy.service.builder.TelegramMessageBuilderFactory;
import pro.sisit.utils.webhookproxy.service.cqs.CommandHandler;
import pro.sisit.utils.webhookproxy.service.query.ProxyRuleQueryService;
import pro.sisit.utils.webhookproxy.service.sender.SenderService;

@Service
@RequiredArgsConstructor
public class ReceiveEventHandler implements CommandHandler<ReceiveEventCommand, Optional<Object>> {

    private final TelegramMessageBuilderFactory messageBuilder;
    private final SenderService senderService;
    private final Environment environment;

    private final ProxyRuleQueryService queryService;

    @Override
    public Optional<Object> process(ReceiveEventCommand command) {
        if (command.getWebhookEvent() == null) {
            return Optional.of(new ArrayList<>());
        }

        Message message = messageBuilder.toMessage(command.getWebhookEvent());
        System.out.println(
            String.format("Detect targets by event '%s'. Source: '%s'.",
                command.getWebhookEvent().getClass(), command.getWebhookEvent().getSource()));
        return Optional.ofNullable(
            queryService.findTargets(command.getWebhookEvent())
                        .stream()
                        .peek(target -> System.out.println("target: " + target))
                        .map(target -> senderService.send(target, message))
                        .collect(Collectors.toList()));
    }

    @Override
    public Class getCommandClass() {
        return ReceiveEventCommand.class;
    }
}
