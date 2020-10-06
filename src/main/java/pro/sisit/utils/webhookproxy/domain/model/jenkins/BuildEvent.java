package pro.sisit.utils.webhookproxy.domain.model.jenkins;

import lombok.Data;

@Data
public class BuildEvent {

    private String name;

    private String url;

    private BuildEventStatus status;

    private String projectName;

}
