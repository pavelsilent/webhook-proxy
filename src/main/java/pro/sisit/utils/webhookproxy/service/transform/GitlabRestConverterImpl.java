package pro.sisit.utils.webhookproxy.service.transform;

import java.util.Optional;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.CommentModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.CommitModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.MergeRequestModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.MergeRequestShortModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.PipelineBuildModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.PipelineModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.ProjectModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.RepositoryModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.RunnerModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.UserModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.enumeration.CommentTarget;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.enumeration.PipelineSource;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.enumeration.PipelineStatus;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.AuthorDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.BuildDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.CommentDataDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.CommitDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.CommitFullDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.MergeRequestDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.MergeRequestDataDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.MergeRequestShortDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.PipelineDataDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.ProjectDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.RepositoryDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.RunnerDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.UserDTO;
import pro.sisit.utils.webhookproxy.util.NumberUtil;

@Service
public class GitlabRestConverterImpl implements GitlabRestConverter {

    @Override
    public UserModel toModel(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        
        UserModel model = new UserModel();
        model.setExternalId(NumberUtil.of(dto.id));
        model.setName(dto.name);
        model.setLogin(dto.username);
        model.setEmail(dto.email);
        model.setAvatarURL(dto.avatarUrl);

        return model;
    }

    public UserModel toModel(AuthorDTO dto) {
        if (dto == null) {
            return null;
        }

        UserModel model = new UserModel();
        model.setName(dto.name);
        model.setEmail(dto.email);
        return model;
    }

    @Override
    public ProjectModel toModel(ProjectDTO dto) {
        if (dto == null) {
            return null;
        }

        ProjectModel model = new ProjectModel();
        model.setExternalId(NumberUtil.of(dto.id));
        model.setName(dto.name);
        model.setFullName(dto.pathWithNamespace);
        model.setUrl(dto.webUrl);
        return model;
    }

    @Override
    public RepositoryModel toModel(RepositoryDTO dto) {
        if (dto == null) {
            return null;
        }

        RepositoryModel model = new RepositoryModel();
        model.setName(dto.name);
        model.setUrl(dto.homepage);
        return model;
    }

    @Override
    public MergeRequestModel toModel(MergeRequestDataDTO dto) {
        if (dto == null) {
            return null;
        }

        MergeRequestModel model = new MergeRequestModel();
        model.setExternalId(NumberUtil.of(dto.id));
        model.setTitle(dto.title);
        model.setDescription(dto.description);
        model.setUrl(dto.url);
        model.setSource(toModel(dto.source));
        model.setSourceBranch(dto.sourceBranch);
        model.setTarget(toModel(dto.target));
        model.setTargetBranch(dto.targetBranch);
        model.setExternalAuthorId(NumberUtil.of(dto.authorId));
        model.setExternalAssigneeId(NumberUtil.of(dto.assigneeId));
        model.setState(dto.state);
        model.setMergeStatus(dto.mergeStatus);
        model.setWorkInProgress(dto.workInProgress);

        return model;
    }

    @Override
    public MergeRequestModel toModel(MergeRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        MergeRequestModel model = new MergeRequestModel();
        model.setExternalId(NumberUtil.of(dto.id));
        model.setTitle(dto.title);
        model.setDescription(dto.description);
//        model.setUrl(dto.url);
        model.setSource(toModel(dto.source));
        model.setSourceBranch(dto.sourceBranch);
        model.setTarget(toModel(dto.target));
        model.setTargetBranch(dto.targetBranch);
        model.setExternalAuthorId(NumberUtil.of(dto.authorId));
        model.setExternalAssigneeId(NumberUtil.of(dto.assigneeId));
        model.setState(dto.state);
        model.setMergeStatus(dto.mergeStatus);
        model.setWorkInProgress(dto.workInProgress);

        return model;
    }

    @Override
    public MergeRequestShortModel toModel(MergeRequestShortDTO dto) {
        if (dto == null) {
            return null;
        }

        MergeRequestShortModel model = new MergeRequestShortModel();
        model.setExternalId(NumberUtil.of(dto.id));
        model.setTitle(dto.title);
        model.setUrl(dto.url);
        model.setSourceBranch(dto.sourceBranch);
        model.setTargetBranch(dto.targetBranch);
        model.setState(dto.state);
        model.setMergeStatus(dto.mergeStatus);
        return model;
    }

    @Override
    public CommentModel toModel(CommentDataDTO dto) {
        if (dto == null) {
            return null;
        }

        CommentModel model = new CommentModel();
        model.setExternalId(NumberUtil.of(dto.id));
        model.setTarget(CommentTarget.resolve(dto.noteableType));
        model.setAuthorId(NumberUtil.of(dto.authorId));
        model.setText(dto.note);
        model.setUrl(dto.url);

        return model;
    }

    @Override
    public CommitModel toModel(CommitDTO dto) {
        if (dto == null) {
            return null;
        }

        CommitModel model = new CommitModel();
        model.setExternalId(dto.id);
        model.setMessage(dto.message);
        model.setUrl(dto.url);
        model.setAuthor(toModel(dto.author));
        return model;
    }

    @Override
    public CommitModel toModel(CommitFullDTO dto) {
        if (dto == null) {
            return null;
        }

        CommitModel model = new CommitModel();
        model.setExternalId(dto.id);
        model.setMessage(dto.message);
        model.setUrl(dto.url);
        model.setAuthor(toModel(dto.author));
        return model;
    }

    @Override
    public PipelineModel toModel(PipelineDataDTO dto) {
        if (dto == null) {
            return null;
        }

        PipelineModel model = new PipelineModel();
        model.setExternalId(NumberUtil.of(dto.id));
        model.setStatus(PipelineStatus.resolveSoft(dto.status).orElse(null));
        model.setStages(dto.stages);
        model.setDuration(Optional.ofNullable(dto.duration).map(Long::valueOf).orElse(0L));
        model.setSource(PipelineSource.resolveSoft(dto.source).orElse(null));
        model.setRef(dto.ref);
        return model;
    }

    @Override
    public PipelineBuildModel toModel(BuildDTO dto) {
        if (dto == null) {
            return null;
        }

        PipelineBuildModel model = new PipelineBuildModel();
        model.setExternalId(NumberUtil.of(dto.id));
        model.setName(dto.name);
        model.setStage(dto.stage);
        model.setStatus(dto.status);
        model.setWhen(dto.when);
        model.setRunner(toModel(dto.runner));
        model.setUser(toModel(dto.user));
        return model;
    }

    @Override
    public RunnerModel toModel(RunnerDTO dto) {
        if (dto == null) {
            return null;
        }

        RunnerModel model = new RunnerModel();
        model.setExternalId(NumberUtil.of(dto.id));
        model.setDescription(dto.description);
        model.setActive(dto.active);
        model.setShared(dto.shared);
        return model;
    }

}
