package pro.sisit.utils.webhookproxy.domain.model.jenkins.enumeration;

import java.util.Arrays;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JobStatus {
    SUCCESS("SUCCESS"),
    FAILURE("FAILURE"),
    ABORTED("ABORTED");

    private final String code;

    public static Optional<JobStatus> resolveSoft(String code) {
        return Arrays.stream(values())
                     .filter(commentTarget -> commentTarget.getCode().equals(code))
                     .findFirst();
    }

    public static JobStatus resolve(String code) {
        return resolveSoft(code)
            .orElseThrow(() -> new RuntimeException(code));
    }
}
