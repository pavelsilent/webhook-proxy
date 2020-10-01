package pro.sisit.utils.webhookproxy.domain.model.gitlab.data;

import lombok.Data;

@Data
public class ProjectModel {

    private Long externalId;

    private String name;

    private String url;
}
