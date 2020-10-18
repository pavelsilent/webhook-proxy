package pro.sisit.utils.webhookproxy.domain.model.jenkins.data;

import java.util.List;
import lombok.Data;

@Data
public class CommitModel {

    private String url;

    private String branch;

    private String commit;

    private List<String> changes;

    private List<String> culprits;

}
