package pro.sisit.utils.webhookproxy.service.transform;

import pro.sisit.utils.webhookproxy.domain.model.jenkins.JenkinsEvent;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.data.CommitModel;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.data.JobModel;
import pro.sisit.utils.webhookproxy.rest.dto.jenkins.JenkinsEventDTO;
import pro.sisit.utils.webhookproxy.rest.dto.jenkins.data.JenkinsBuildDTO;
import pro.sisit.utils.webhookproxy.rest.dto.jenkins.data.JenkinsSCMDTO;

public interface JenkinsRestConverter {

    JenkinsEvent toModel(JenkinsEventDTO dto);

    JobModel toModel(JenkinsBuildDTO dto);

    CommitModel toModel(JenkinsSCMDTO dto);

}
