package pro.sisit.utils.webhookproxy.service.transform.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.event.CommitCommentEvent;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.WebHookCommentDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.CommitDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.CommitFullDTO;
import pro.sisit.utils.webhookproxy.service.transform.GitlabRestConverter;

@Service
@RequiredArgsConstructor
public class CommitCommentEventRestConverter implements GitlabEventRestConverter<CommitCommentEvent, WebHookCommentDTO> {

    private final GitlabRestConverter restConverter;

    @Override
    public CommitCommentEvent toModel(WebHookCommentDTO dto) {
        CommitCommentEvent model = new CommitCommentEvent();
        model.setUser(restConverter.toModel(dto.user));
        model.setRepository(restConverter.toModel(dto.repository));
        model.setProject(restConverter.toModel(dto.project));
        if (dto.getTarget() instanceof CommitDTO) {
            model.setCommit(restConverter.toModel((CommitDTO) dto.getTarget()));
        } else if (dto.getTarget() instanceof CommitFullDTO) {
            model.setCommit(restConverter.toModel((CommitFullDTO) dto.getTarget()));
        }

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
                (CommitDTO.class.equals(comment.getTarget().getClass()) ||
                        CommitFullDTO.class.equals(comment.getTarget().getClass()));

    }
}
