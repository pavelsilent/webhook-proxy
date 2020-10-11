package pro.sisit.utils.webhookproxy.domain.model.gitlab.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PipelineBuildModel {

    private Long externalId;

    private String stage;

    private String name;

    private String when;

    private String status;

    private UserModel user;

    private RunnerModel runner;
}
