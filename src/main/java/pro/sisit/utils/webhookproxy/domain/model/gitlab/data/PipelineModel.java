package pro.sisit.utils.webhookproxy.domain.model.gitlab.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.enumeration.PipelineSource;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.enumeration.PipelineStatus;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PipelineModel {

    private Long externalId;

    private PipelineStatus status;

    private List<String> stages;

    private PipelineSource source;

    private String ref;

    private Long duration;
}
