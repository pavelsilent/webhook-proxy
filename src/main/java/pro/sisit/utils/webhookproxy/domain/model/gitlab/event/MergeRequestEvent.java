package pro.sisit.utils.webhookproxy.domain.model.gitlab.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pro.sisit.utils.webhookproxy.domain.WebhookEvent;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.MergeRequestModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.ProjectModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.RepositoryModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.UserModel;

@EqualsAndHashCode(callSuper = true)
@Data
public class MergeRequestEvent extends GitlabWebhookEvent {

    private UserModel user;

    private ProjectModel project;

    private RepositoryModel repository;

    private MergeRequestModel mergeRequest;
}
