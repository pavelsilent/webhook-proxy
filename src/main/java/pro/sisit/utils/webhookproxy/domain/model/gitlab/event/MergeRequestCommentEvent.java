package pro.sisit.utils.webhookproxy.domain.model.gitlab.event;

import lombok.*;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MergeRequestCommentEvent extends GitlabEvent {

    private UserModel user;

    private ProjectModel project;

    private RepositoryModel repository;

    private CommentModel comment;

    private MergeRequestModel mergeRequest;
}
