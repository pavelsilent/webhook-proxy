package pro.sisit.utils.webhookproxy.domain.model.jenkins.data;

import java.util.Map;
import lombok.Data;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.enumeration.JobPhase;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.enumeration.JobStatus;

@Data
public class JobModel {

    private Integer number;

    private String url;

    private JobStatus status;

    private JobPhase phase;

    private String customMessage;

    private Map<String, String> parameters;

    private Map<String, String> artifacts;

}
