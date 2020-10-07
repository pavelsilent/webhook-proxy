package pro.sisit.utils.webhookproxy.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.model.telegram.Message;
import pro.sisit.utils.webhookproxy.service.builder.TelegramMessageBuilderFactory;
import pro.sisit.utils.webhookproxy.service.cqs.CommandHandler;
import pro.sisit.utils.webhookproxy.service.query.ProxyRuleQueryService;
import pro.sisit.utils.webhookproxy.service.sender.TargetSender;

@Service
@RequiredArgsConstructor
public class ReceiveWebhookHandler implements CommandHandler<ReceiveWebhookCommand, Void> {

    private final TelegramMessageBuilderFactory messageBuilder;
    private final TargetSender sender;
    private final Environment environment;

    private final ProxyRuleQueryService queryService;

    @Override
    public Void process(ReceiveWebhookCommand command) {
        Message message = messageBuilder.toMessage(command.getWebhookEvent());
        queryService.findTargets(command.getWebhookEvent())
                .forEach(target -> sender.send(target, message));

        return null;
    }

    @Override
    public boolean supports(ReceiveWebhookCommand command) {
        return ReceiveWebhookCommand.class.equals(command.getClass());
    }
}
