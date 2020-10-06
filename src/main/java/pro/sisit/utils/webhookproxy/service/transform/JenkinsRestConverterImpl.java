package pro.sisit.utils.webhookproxy.service.transform;

import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.BuildEvent;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.BuildEventStatus;
import pro.sisit.utils.webhookproxy.rest.dto.jenkins.JenkinsBuildEventDTO;

@Service
public class JenkinsRestConverterImpl implements JenkinsRestConverter {

    @Override
    public BuildEvent toModel(JenkinsBuildEventDTO dto) {
        BuildEvent model = new BuildEvent();
        model.setName(dto.name);
        model.setUrl(dto.url);
        model.setProjectName(dto.projectName);
        model.setStatus(BuildEventStatus.valueOf(dto.status));
        return model;
    }
}
