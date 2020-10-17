package pro.sisit.utils.webhookproxy.service.transform.gitlab;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.event.MergeRequestCommentEvent;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabCommentDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.MergeRequestDTO;
import pro.sisit.utils.webhookproxy.service.transform.GitlabRestConverter;
import pro.sisit.utils.webhookproxy.service.transform.RestConverter;

@Service
@RequiredArgsConstructor
public class MergeRequestCommentEventRestConverter implements RestConverter<MergeRequestCommentEvent, GitLabCommentDTO> {

    private final GitlabRestConverter restConverter;

    @Override
    public MergeRequestCommentEvent toModel(GitLabCommentDTO dto) {
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
        if (!(dto instanceof GitLabCommentDTO)) {
            return false;
        }

        GitLabCommentDTO comment = (GitLabCommentDTO) dto;
        return GitLabCommentDTO.class.equals(dto.getClass()) &&
                MergeRequestDTO.class.equals(comment.getTarget().getClass());

    }
}
