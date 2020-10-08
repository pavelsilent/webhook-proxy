package pro.sisit.utils.webhookproxy.domain.model.gitlab.data;

import lombok.Data;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.enumeration.PipelineSource;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.enumeration.PipelineStatus;

import java.util.List;

@Data
public class PipelineModel {

    private Long externalId;

    private PipelineStatus status;

    private List<String> stages;

    private PipelineSource source;

    private String ref;

    private Long duration;
}
