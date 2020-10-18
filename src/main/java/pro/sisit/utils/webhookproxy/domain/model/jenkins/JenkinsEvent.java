package pro.sisit.utils.webhookproxy.domain.model.jenkins;

import lombok.Data;
import pro.sisit.utils.webhookproxy.domain.Event;
import pro.sisit.utils.webhookproxy.domain.Source;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.data.CommitModel;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.data.JobModel;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.data.ProjectModel;

@Data
public class JenkinsEvent implements Event {

    private ProjectModel project;

    private JobModel job;

    private CommitModel commit;

    @Override
    public Source getSource() {
        return Source.JENKINS;
    }
}
