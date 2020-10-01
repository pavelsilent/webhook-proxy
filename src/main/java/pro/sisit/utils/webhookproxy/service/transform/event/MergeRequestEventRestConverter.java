package pro.sisit.utils.webhookproxy.service.transform.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.event.MergeRequestEvent;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.WebHookMergeRequestDTO;
import pro.sisit.utils.webhookproxy.service.transform.GitlabRestConverter;

@Service
@RequiredArgsConstructor
public class MergeRequestEventRestConverter implements GitlabEventRestConverter<MergeRequestEvent, WebHookMergeRequestDTO> {

    private final GitlabRestConverter restConverter;

    @Override
    public MergeRequestEvent toModel(WebHookMergeRequestDTO dto) {
        MergeRequestEvent model = new MergeRequestEvent();
        model.setUser(restConverter.toModel(dto.user));
        model.setRepository(restConverter.toModel(dto.repository));
        model.setProject(restConverter.toModel(dto.project));
        model.setMergeRequest(restConverter.toModel(dto.mergeRequestData));
        return model;
    }

    @Override
    public boolean supports(Object dto) {
        return WebHookMergeRequestDTO.class.equals(dto.getClass());
    }
}
