package pro.sisit.utils.webhookproxy.domain.model.gitlab.enumeration;

import java.util.Arrays;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MergeRequestState {
    OPENED("opened"),
    CLOSED("closed"),
    LOCKED("locked"),
    MERGED("merged");

    private final String code;

    public static Optional<MergeRequestState> resolveSoft(String code) {
        return Arrays.stream(values())
                .filter(commentTarget -> commentTarget.getCode().equals(code))
                .findFirst();
    }

    public static MergeRequestState resolve(String code) {
        return resolveSoft(code).orElseThrow(() -> new RuntimeException(code));
    }
}
