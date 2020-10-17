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
public class MergeRequestShortModel {

    private Long externalId;

    private String title;

    private String url;

    private String sourceBranch;

    private String targetBranch;

    private MergeRequestState state;

    private String mergeStatus;

    public String getShortMessage() {
        return StringUtil.getCutEscapedString(title);
    }
}
