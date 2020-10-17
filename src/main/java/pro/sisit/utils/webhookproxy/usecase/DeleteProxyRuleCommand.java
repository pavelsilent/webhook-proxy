package pro.sisit.utils.webhookproxy.usecase;

import lombok.Data;
import pro.sisit.utils.webhookproxy.domain.cqs.Command;

@Data
public class DeleteProxyRuleCommand implements Command {

    private Long proxyRuleId;
}
