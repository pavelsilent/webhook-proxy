package pro.sisit.utils.webhookproxy.domain.model.gitlab.data;

import lombok.Data;

@Data
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

    private String state;

    private String mergeStatus;

    private boolean workInProgress;

}
