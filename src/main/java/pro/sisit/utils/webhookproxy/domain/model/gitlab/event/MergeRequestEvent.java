package pro.sisit.utils.webhookproxy.domain.model.gitlab.event;

import lombok.Data;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.MergeRequestModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.ProjectModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.RepositoryModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.UserModel;

@Data
public class MergeRequestEvent {

    private UserModel user;

    private ProjectModel project;

    private RepositoryModel repository;

    private MergeRequestModel mergeRequest;
}
