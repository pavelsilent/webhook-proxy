package pro.sisit.utils.webhookproxy.domain.model.jenkins.enumeration;

import java.util.Arrays;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BuildParameters {
    TEST_REPORT("TestReport"),
    TEST_COVERAGE_REPORT("TestCoverageReport"),
    SONARQUBE_REPORT("SonarQubeProject");

    private final String code;

    public static Optional<BuildParameters> resolveSoft(String code) {
        return Arrays.stream(values())
                     .filter(commentTarget -> commentTarget.getCode().equals(code))
                     .findFirst();
    }

    public static BuildParameters resolve(String code) {
        return resolveSoft(code)
            .orElseThrow(() -> new RuntimeException(code));
    }
}
