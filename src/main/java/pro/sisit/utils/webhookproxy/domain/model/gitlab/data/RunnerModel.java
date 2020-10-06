package pro.sisit.utils.webhookproxy.domain.model.gitlab.data;

import lombok.Data;

@Data
public class RunnerModel {

    private Long externalId;

    private String description;

    private boolean active;

    private boolean shared;
}
