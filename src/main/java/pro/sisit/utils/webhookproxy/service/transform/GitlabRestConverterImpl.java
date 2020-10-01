package pro.sisit.utils.webhookproxy.service.transform;

import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.*;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.enumeration.CommentTarget;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.*;
import pro.sisit.utils.webhookproxy.util.NumberUtil;

@Service
public class GitlabRestConverterImpl implements GitlabRestConverter {
    @Override
    public UserModel toModel(UserDTO dto) {
        UserModel model = new UserModel();
        model.setExternalId(NumberUtil.of(dto.id));
        model.setName(dto.name);
        model.setLogin(dto.username);
        model.setEmail(dto.email);
        model.setAvatarURL(dto.avatarUrl);

        return model;
    }


    public UserModel toModel(AuthorDTO dto) {
        UserModel model = new UserModel();
        model.setName(dto.name);
        model.setEmail(dto.email);
        return model;
    }

    @Override
    public ProjectModel toModel(ProjectDTO dto) {
        ProjectModel model = new ProjectModel();
        model.setExternalId(NumberUtil.of(dto.id));
        model.setName(dto.name);
        model.setUrl(dto.webUrl);
        return model;
    }

    @Override
    public RepositoryModel toModel(RepositoryDTO dto) {
        RepositoryModel model = new RepositoryModel();
        model.setName(dto.name);
        model.setUrl(dto.homepage);
        return model;
    }

    @Override
    public MergeRequestModel toModel(MergeRequestDataDTO dto) {
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
    public CommentModel toModel(CommentDataDTO dto) {
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
        CommitModel model = new CommitModel();
        model.setExternalId(dto.id);
        model.setMessage(dto.message);
        model.setUrl(dto.url);
        model.setAuthor(toModel(dto.author));
        return model;
    }

    @Override
    public CommitModel toModel(CommitFullDTO dto) {
        CommitModel model = new CommitModel();
        model.setExternalId(dto.id);
        model.setMessage(dto.message);
        model.setUrl(dto.url);
        model.setAuthor(toModel(dto.author));
        return model;
    }

}
