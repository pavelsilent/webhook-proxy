package pro.sisit.utils.webhookproxy.domain.model.gitlab.event;

import lombok.*;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.MergeRequestModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.ProjectModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.RepositoryModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.UserModel;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MergeRequestEvent extends GitlabEvent {

    private UserModel user;

    private ProjectModel project;

    private RepositoryModel repository;

    private MergeRequestModel mergeRequest;
}
