package pro.sisit.utils.webhookproxy.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sisit.utils.webhookproxy.rest.dto.rule.ProxyRuleInitialDTO;
import pro.sisit.utils.webhookproxy.service.cqs.CommandHandlerFactory;
import pro.sisit.utils.webhookproxy.service.transform.RuleRestConverter;
import pro.sisit.utils.webhookproxy.usecase.DeleteAllDataCommand;
import pro.sisit.utils.webhookproxy.usecase.DeleteProxyRuleCommand;
import pro.sisit.utils.webhookproxy.usecase.ProxyRuleCreateCommand;
import pro.sisit.utils.webhookproxy.util.NumberUtil;

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

    @DeleteMapping(value = "proxy-rule/{rule-id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long deleteRule(@PathVariable("rule-id") String ruleId) {
        System.out.println("RuleController: deleteRule");
        System.out.println(ruleId);

        DeleteProxyRuleCommand command = new DeleteProxyRuleCommand();
        command.setProxyRuleId(NumberUtil.of(ruleId));

        return commandDispatcher.process(command);
    }

    @DeleteMapping(value = "proxy-rules", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteRules(@RequestParam("onlyRules") boolean onlyRules) {
        System.out.println("RuleController: deleteRules");
        System.out.println("   onlyRules: " + onlyRules);

        DeleteAllDataCommand command = new DeleteAllDataCommand(onlyRules);
        commandDispatcher.process(command);
    }
}
