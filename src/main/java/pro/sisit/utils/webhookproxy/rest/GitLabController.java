package pro.sisit.utils.webhookproxy.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabDTO;
import pro.sisit.utils.webhookproxy.service.cqs.CommandHandlerFactory;
import pro.sisit.utils.webhookproxy.service.transform.RestConverterFactory;
import pro.sisit.utils.webhookproxy.usecase.ReceiveEventCommand;

@RestController
@RequiredArgsConstructor
public class GitLabController {

    private final CommandHandlerFactory commandDispatcher;
    private final RestConverterFactory restConverter;

    @PostMapping(value = "gitlab/receive", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void receive(@RequestBody GitLabDTO dto) {
        System.out.println("GitLabController: receive");
        System.out.println(dto.getClass());

        ReceiveEventCommand command = new ReceiveEventCommand();
        command.setWebhookEvent(restConverter.toModelSoft(dto, null));

        commandDispatcher.process(command);
    }

}
