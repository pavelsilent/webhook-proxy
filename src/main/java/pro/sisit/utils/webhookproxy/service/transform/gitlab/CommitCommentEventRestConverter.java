package pro.sisit.utils.webhookproxy.service.transform.gitlab;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.event.CommitCommentEvent;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabCommentDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.CommitDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.CommitFullDTO;
import pro.sisit.utils.webhookproxy.service.transform.GitlabRestConverter;
import pro.sisit.utils.webhookproxy.service.transform.RestConverter;

@Service
@RequiredArgsConstructor
public class CommitCommentEventRestConverter implements RestConverter<CommitCommentEvent, GitLabCommentDTO> {

    private final GitlabRestConverter restConverter;

    @Override
    public CommitCommentEvent toModel(GitLabCommentDTO dto) {
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
        if (!(dto instanceof GitLabCommentDTO)) {
            return false;
        }

        GitLabCommentDTO comment = (GitLabCommentDTO) dto;
        return GitLabCommentDTO.class.equals(dto.getClass()) &&
                (CommitDTO.class.equals(comment.getTarget().getClass()) ||
                        CommitFullDTO.class.equals(comment.getTarget().getClass()));

    }
}
