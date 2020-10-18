package pro.sisit.utils.webhookproxy.domain.model.jenkins.data;

import java.util.List;
import lombok.Data;

@Data
public class CommitModel {

    private String sshRepoUrl;

    private String httpRepoUrl;

    private String branch;

    private String commit;

    private List<String> changes;

    private List<String> culprits;

}
