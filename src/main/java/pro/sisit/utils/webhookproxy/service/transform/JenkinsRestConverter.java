package pro.sisit.utils.webhookproxy.service.transform;

import pro.sisit.utils.webhookproxy.domain.model.jenkins.BuildEvent;
import pro.sisit.utils.webhookproxy.rest.dto.jenkins.JenkinsBuildEventDTO;

public interface JenkinsRestConverter extends WebHookRestConverter<BuildEvent, JenkinsBuildEventDTO> {

    @Override
    default boolean supports(Object dto) {
        return JenkinsBuildEventDTO.class.equals(dto.getClass());
    }
}
