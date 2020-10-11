package pro.sisit.utils.webhookproxy.domain.model.gitlab.event;

import lombok.Data;
import pro.sisit.utils.webhookproxy.domain.Event;
import pro.sisit.utils.webhookproxy.domain.Source;

@Data
public class GitlabEvent implements Event {

    @Override
    public Source getSource() {
        return Source.GITLAB;
    }
}
