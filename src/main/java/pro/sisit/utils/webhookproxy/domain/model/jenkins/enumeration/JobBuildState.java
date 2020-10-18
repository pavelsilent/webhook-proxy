package pro.sisit.utils.webhookproxy.domain.model.jenkins.enumeration;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiPredicate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pro.sisit.utils.webhookproxy.domain.enumeration.format.IconType;

@Getter
@RequiredArgsConstructor
public enum JobBuildState {
    SUCCESS("SUCCESS", IconType.FAILURE, JobBuildState::hasSuccessState),
    FAILURE("FAILURE", IconType.FAILURE, JobBuildState::hasFailureState),
    STARTED("STARTED", IconType.FAILURE, JobBuildState::hasStartedState),
    ABORTED("ABORTED", IconType.FAILURE, JobBuildState::hasAbortedState);

    private final String code;

    private final IconType icon;

    private final BiPredicate<JobPhase, JobStatus> predicate;

    public static Optional<JobBuildState> resolveSoft(String code) {
        return Arrays.stream(values())
                     .filter(commentTarget -> commentTarget.getCode().equals(code))
                     .findFirst();
    }

    public static JobBuildState resolve(String code) {
        return resolveSoft(code)
            .orElseThrow(() -> new RuntimeException(code));
    }

    public static JobBuildState resolveSoft(JobPhase phase, JobStatus status) {
        return Arrays.stream(values())
                     .filter(commentTarget -> commentTarget.getPredicate().test(phase, status))
                     .findFirst()
                     .orElse(null);
    }

    public static boolean hasStartedState(JobPhase phase, JobStatus status) {
        return Optional.ofNullable(phase)
                       .filter(JobPhase.STARTED::equals)
                       .isPresent();
    }

    public static boolean hasSuccessState(JobPhase phase, JobStatus status) {
        return Optional.ofNullable(phase)
                       .filter(data -> JobPhase.COMPLETED.equals(data) || JobPhase.FINALIZED.equals(data))
                       .filter(data -> JobStatus.SUCCESS.equals(status))
                       .isPresent();
    }

    public static boolean hasFailureState(JobPhase phase, JobStatus status) {
        return Optional.ofNullable(phase)
                       .filter(data -> JobPhase.COMPLETED.equals(data) || JobPhase.FINALIZED.equals(data))
                       .filter(data -> JobStatus.FAILURE.equals(status))
                       .isPresent();
    }

    public static boolean hasAbortedState(JobPhase phase, JobStatus status) {
        return Optional.ofNullable(phase)
                       .filter(data -> JobPhase.COMPLETED.equals(data) || JobPhase.FINALIZED.equals(data))
                       .filter(data -> JobStatus.ABORTED.equals(status))
                       .isPresent();
    }
}
