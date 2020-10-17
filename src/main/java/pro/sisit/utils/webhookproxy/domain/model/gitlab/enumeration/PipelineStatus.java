package pro.sisit.utils.webhookproxy.domain.model.gitlab.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum PipelineStatus {
    CREATED("created"),
    RESOURCE_WAITING("waiting_for_resource"),
    PREPARING("preparing"),
    PENDING("pending"),
    RUNNING("running"),
    SUCCESS("success"),
    FAILED("failed"),
    CANCELED("canceled"),
    SKIPPED("skipped"),
    MANUAL("manual"),
    SCHEDULED("scheduled");

    private final String code;

    public static Optional<PipelineStatus> resolveSoft(String code) {
        return Arrays.stream(values())
                .filter(commentTarget -> commentTarget.getCode().equals(code))
                .findFirst();
    }

    public static PipelineStatus resolve(String code) {
        return resolveSoft(code).orElseThrow(() -> new RuntimeException(code));
    }
}
