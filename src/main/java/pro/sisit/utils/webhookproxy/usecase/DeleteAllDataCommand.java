package pro.sisit.utils.webhookproxy.usecase;

import lombok.AllArgsConstructor;
import lombok.Data;
import pro.sisit.utils.webhookproxy.domain.cqs.Command;

@Data
@AllArgsConstructor
public class DeleteAllDataCommand implements Command {

    private boolean onlyRules;
}
