package pro.sisit.utils.webhookproxy.service.cqs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.cqs.Command;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandHandlerFactory {

    private final List<CommandHandler> handlers;

    @SuppressWarnings("unchecked")
    public <T extends Command, R> R process(T command) {
        return (R) handlers.stream().filter(command::fits).findFirst()
                .map(handler -> handler.process(command))
                .orElseThrow(() -> new RuntimeException(
                        String.format("Not found handler for command '%s'.",
                                command.getClass().getSimpleName())));
    }


}
