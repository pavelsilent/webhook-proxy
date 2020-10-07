package pro.sisit.utils.webhookproxy.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabWebHookDTO;
import pro.sisit.utils.webhookproxy.service.cqs.CommandHandlerFactory;
import pro.sisit.utils.webhookproxy.service.transform.WebHookRestConverterFactory;
import pro.sisit.utils.webhookproxy.usecase.ReceiveWebhookCommand;

@RestController
@RequiredArgsConstructor
public class GitLabController {

    private final CommandHandlerFactory commandDispatcher;
    private final WebHookRestConverterFactory restConverter;

    @PostMapping(value = "gitlab/receive", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void receive(@RequestBody GitLabWebHookDTO dto) {
        System.out.println("GitLabController: receive");
        System.out.println(dto.getClass());

        ReceiveWebhookCommand command = new ReceiveWebhookCommand();
        command.setWebhookEvent(restConverter.toModel(dto));

        commandDispatcher.process(command);
    }

}
