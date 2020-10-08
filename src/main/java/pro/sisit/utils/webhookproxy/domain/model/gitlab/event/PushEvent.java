package pro.sisit.utils.webhookproxy.domain.model.gitlab.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PushEvent extends GitlabEvent {

    private UserModel user;

    private ProjectModel project;

    private List<CommitModel> commits;

    private int totalCommitsCount;

    private String branch;


}
