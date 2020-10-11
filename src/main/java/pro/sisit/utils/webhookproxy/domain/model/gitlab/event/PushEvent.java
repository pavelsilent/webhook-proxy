package pro.sisit.utils.webhookproxy.domain.model.gitlab.event;

import lombok.*;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PushEvent extends GitlabEvent {

    private UserModel user;

    private ProjectModel project;

    private List<CommitModel> commits;

    private int totalCommitsCount;

    private String branch;


}
