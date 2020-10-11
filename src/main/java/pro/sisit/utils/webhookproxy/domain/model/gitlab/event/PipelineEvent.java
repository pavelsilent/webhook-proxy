package pro.sisit.utils.webhookproxy.domain.model.gitlab.event;

import lombok.*;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PipelineEvent extends GitlabEvent {

    private PipelineModel pipeline;

    private MergeRequestShortModel mergeRequest;

    private UserModel user;

    private ProjectModel project;

    private CommitModel commit;

    private List<PipelineBuildModel> builds;

}
