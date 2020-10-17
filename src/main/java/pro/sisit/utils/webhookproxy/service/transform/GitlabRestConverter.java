package pro.sisit.utils.webhookproxy.service.transform;

import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.*;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.*;

public interface GitlabRestConverter {

    UserModel toModel(UserDTO dto);

    ProjectModel toModel(ProjectDTO dto);

    RepositoryModel toModel(RepositoryDTO dto);

    MergeRequestModel toModel(MergeRequestDataDTO dto);

    MergeRequestModel toModel(MergeRequestDTO dto);

    MergeRequestShortModel toModel(MergeRequestShortDTO dto);

    CommentModel toModel(CommentDataDTO dto);

    CommitModel toModel(CommitDTO dto);

    CommitModel toModel(CommitFullDTO dto);

    PipelineModel toModel(PipelineDataDTO dto);

    PipelineBuildModel toModel(BuildDTO dto);

    RunnerModel toModel(RunnerDTO dto);
}
