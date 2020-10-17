package pro.sisit.utils.webhookproxy.service.cqs;

import pro.sisit.utils.webhookproxy.domain.cqs.Command;

public interface CommandHandler<T extends Command, R> {

    R process(T command);

    default boolean supports(Class commandClass) {
        return getCommandClass().equals(commandClass);
    }

    Class getCommandClass();
}
