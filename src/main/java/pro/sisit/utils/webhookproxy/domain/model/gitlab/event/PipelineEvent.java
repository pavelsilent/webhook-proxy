package pro.sisit.utils.webhookproxy.domain.model.gitlab.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PipelineEvent extends GitlabWebhookEvent {

    private PipelineModel pipeline;

    private MergeRequestShortModel mergeRequest;

    private UserModel user;

    private ProjectModel project;

    private CommitModel commit;

    private List<PipelineBuildModel> builds;

}
