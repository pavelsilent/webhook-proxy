package pro.sisit.utils.webhookproxy.domain.model.gitlab.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.enumeration.MergeRequestState;
import pro.sisit.utils.webhookproxy.util.StringUtil;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MergeRequestModel {

    private Long externalId;

    private String title;

    private String description;

    private String url;

    private ProjectModel source;

    private String sourceBranch;

    private ProjectModel target;

    private String targetBranch;

    private Long externalAuthorId;

    private Long externalAssigneeId;

    private MergeRequestState state;

    private String mergeStatus;

    private boolean workInProgress;

    public String getShortMessage() {
        return StringUtil.getCutEscapedString(title);
    }

}
