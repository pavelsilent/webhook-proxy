package pro.sisit.utils.webhookproxy.service.transform.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.event.MergeRequestCommentEvent;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.WebHookCommentDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.MergeRequestDTO;
import pro.sisit.utils.webhookproxy.service.transform.GitlabRestConverter;

@Service
@RequiredArgsConstructor
public class MergeRequestCommentEventRestConverter implements GitlabEventRestConverter<MergeRequestCommentEvent, WebHookCommentDTO> {

    private final GitlabRestConverter restConverter;

    @Override
    public MergeRequestCommentEvent toModel(WebHookCommentDTO dto) {
        MergeRequestCommentEvent model = new MergeRequestCommentEvent();
        model.setUser(restConverter.toModel(dto.user));
        model.setRepository(restConverter.toModel(dto.repository));
        model.setProject(restConverter.toModel(dto.project));
        model.setMergeRequest(restConverter.toModel((MergeRequestDTO) dto.getTarget()));
        model.setComment(restConverter.toModel(dto.commentData));
        return model;
    }

    @Override
    public boolean supports(Object dto) {
        if (!(dto instanceof WebHookCommentDTO)) {
            return false;
        }

        WebHookCommentDTO comment = (WebHookCommentDTO) dto;
        return WebHookCommentDTO.class.equals(dto.getClass()) &&
                MergeRequestDTO.class.equals(comment.getTarget().getClass());

    }
}
