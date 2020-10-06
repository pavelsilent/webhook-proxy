package pro.sisit.utils.webhookproxy.domain.model.gitlab.event;

import lombok.Data;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.*;

import java.util.List;

@Data
public class PipelineEvent {

    private PipelineModel pipeline;

    private MergeRequestShortModel mergeRequest;

    private UserModel user;

    private ProjectModel project;

    private CommitModel commit;

    private List<PipelineBuildModel> builds;

}
