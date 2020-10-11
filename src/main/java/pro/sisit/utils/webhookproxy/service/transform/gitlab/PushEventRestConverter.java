package pro.sisit.utils.webhookproxy.service.transform.gitlab;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.UserModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.event.PushEvent;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabPushDTO;
import pro.sisit.utils.webhookproxy.service.transform.GitlabRestConverter;
import pro.sisit.utils.webhookproxy.service.transform.RestConverter;
import pro.sisit.utils.webhookproxy.util.NumberUtil;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PushEventRestConverter implements RestConverter<PushEvent, GitLabPushDTO> {

    private final GitlabRestConverter restConverter;

    @Override
    public PushEvent toModel(GitLabPushDTO dto) {

        PushEvent model = new PushEvent();
        model.setUser(
                new UserModel(NumberUtil.of(dto.userId),
                        dto.userName, dto.userUsername, dto.userEmail, dto.userAvatar));
        model.setProject(restConverter.toModel(dto.project));
        model.setCommits(
                Optional.ofNullable(dto.commits)
                        .map(list -> list.stream().map(restConverter::toModel).collect(Collectors.toList()))
                        .orElseGet(ArrayList::new));
        model.setTotalCommitsCount(dto.totalCommitsCount);
        model.setBranch(
                model.getProject().getBranchFullName(dto.ref));

        return model;
    }

    @Override
    public boolean supports(Object dto) {
        return GitLabPushDTO.class.equals(dto.getClass());
    }
}
