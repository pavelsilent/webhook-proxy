package pro.sisit.utils.webhookproxy.domain.model.gitlab.data;

import lombok.Data;

@Data
public class PipelineBuildModel {

    private Long externalId;

    private String stage;

    private String name;

    private String when;

    private String status;

    private UserModel user;

    private RunnerModel runner;
}
