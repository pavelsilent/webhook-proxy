package pro.sisit.utils.webhookproxy.domain.model.gitlab.event;

import lombok.Data;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.*;

@Data
public class MergeRequestCommentEvent {

    private UserModel user;

    private ProjectModel project;

    private RepositoryModel repository;

    private CommentModel comment;

    private MergeRequestModel mergeRequest;
}
