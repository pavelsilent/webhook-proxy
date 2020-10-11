package pro.sisit.utils.webhookproxy.domain.cqs;

import pro.sisit.utils.webhookproxy.service.cqs.CommandHandler;

public interface Command {

    default boolean fits(CommandHandler handler) {
        return handler.supports(this);
    }
}
