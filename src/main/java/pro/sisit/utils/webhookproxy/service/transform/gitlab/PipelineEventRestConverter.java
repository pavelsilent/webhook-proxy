package pro.sisit.utils.webhookproxy.service.transform.gitlab;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.event.PipelineEvent;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabPipelineDTO;
import pro.sisit.utils.webhookproxy.service.transform.GitlabRestConverter;
import pro.sisit.utils.webhookproxy.service.transform.RestConverter;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PipelineEventRestConverter implements RestConverter<PipelineEvent, GitLabPipelineDTO> {

    private final GitlabRestConverter restConverter;

    @Override
    public PipelineEvent toModel(GitLabPipelineDTO dto) {
        PipelineEvent model = new PipelineEvent();
        model.setPipeline(restConverter.toModel(dto.pipelineData));
        model.setBuilds(
                Optional.ofNullable(dto.builds)
                        .map(buildDTOS -> buildDTOS.stream()
                                .map(restConverter::toModel).collect(Collectors.toList()))
                        .orElseGet(ArrayList::new));
        model.setUser(restConverter.toModel(dto.user));
        model.setCommit(restConverter.toModel(dto.commit));
        model.setProject(restConverter.toModel(dto.project));
        model.setMergeRequest(restConverter.toModel(dto.mergeRequestData));
        return model;
    }

    @Override
    public boolean supports(Object dto) {
        return GitLabPipelineDTO.class.equals(dto.getClass());
    }
}
