package pro.sisit.utils.webhookproxy.service.transform.gitlab;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.event.MergeRequestEvent;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabWebHookMergeRequestDTO;
import pro.sisit.utils.webhookproxy.service.transform.GitlabRestConverter;
import pro.sisit.utils.webhookproxy.service.transform.WebHookRestConverter;

@Service
@RequiredArgsConstructor
public class MergeRequestEventRestConverter implements WebHookRestConverter<MergeRequestEvent, GitLabWebHookMergeRequestDTO> {

    private final GitlabRestConverter restConverter;

    @Override
    public MergeRequestEvent toModel(GitLabWebHookMergeRequestDTO dto) {
        MergeRequestEvent model = new MergeRequestEvent();
        model.setUser(restConverter.toModel(dto.user));
        model.setRepository(restConverter.toModel(dto.repository));
        model.setProject(restConverter.toModel(dto.project));
        model.setMergeRequest(restConverter.toModel(dto.mergeRequestData));
        return model;
    }

    @Override
    public boolean supports(Object dto) {
        return GitLabWebHookMergeRequestDTO.class.equals(dto.getClass());
    }
}
