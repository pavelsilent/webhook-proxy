package pro.sisit.utils.webhookproxy.domain.model.gitlab.data;

import lombok.Data;

@Data
public class MergeRequestShortModel {

    private Long externalId;

    private String title;

    private String url;

    private String sourceBranch;

    private String targetBranch;

    private String state;

    private String mergeStatus;
}
