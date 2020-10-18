package pro.sisit.utils.webhookproxy.domain.model.jenkins.enumeration;

import java.util.Arrays;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JobPhase {
    QUEUED("QUEUED"),
    STARTED("STARTED"),
    FINALIZED("FINALIZED"),
    COMPLETED("COMPLETED");

    private final String code;

    public static Optional<JobPhase> resolveSoft(String code) {
        return Arrays.stream(values())
                     .filter(commentTarget -> commentTarget.getCode().equals(code))
                     .findFirst();
    }

    public static JobPhase resolve(String code) {
        return resolveSoft(code)
            .orElseThrow(() -> new RuntimeException(code));
    }
}
