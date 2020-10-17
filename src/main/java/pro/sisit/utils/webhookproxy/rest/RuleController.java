package pro.sisit.utils.webhookproxy.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pro.sisit.utils.webhookproxy.rest.dto.rule.ProxyRuleInitialDTO;
import pro.sisit.utils.webhookproxy.service.cqs.CommandHandlerFactory;
import pro.sisit.utils.webhookproxy.service.transform.RuleRestConverter;
import pro.sisit.utils.webhookproxy.usecase.ProxyRuleCreateCommand;

@RestController
@RequiredArgsConstructor
public class RuleController {

    private final CommandHandlerFactory commandDispatcher;
    private final RuleRestConverter restConverter;

    @PutMapping(value = "proxy-rule", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long addProxyRule(@RequestBody ProxyRuleInitialDTO dto) {
        System.out.println("RuleController: addProxyRule");
        System.out.println(dto);

        ProxyRuleCreateCommand command = new ProxyRuleCreateCommand();
        command.setModel(restConverter.toModel(dto));

        return commandDispatcher.process(command);
    }

}
