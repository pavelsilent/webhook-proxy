package pro.sisit.utils.webhookproxy.domain.model.gitlab.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum PipelineSource {
    PUSH("push"),
    MERGE_REQUEST("merge_request_event"),
    EXTERNAL("external");

    private final String code;

    public static Optional<PipelineSource> resolveSoft(String code) {
        return Arrays.stream(values())
                .filter(commentTarget -> commentTarget.getCode().equals(code))
                .findFirst();
    }

    public static PipelineSource resolve(String code) {
        return resolveSoft(code)
                .orElseThrow(() -> new RuntimeException(code));
    }
}
