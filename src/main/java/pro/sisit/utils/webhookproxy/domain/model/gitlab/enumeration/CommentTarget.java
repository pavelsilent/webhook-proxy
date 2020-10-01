package pro.sisit.utils.webhookproxy.domain.model.gitlab.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum CommentTarget {
    MERGE_REQUEST("MergeRequest", "merge request"),
    COMMIT("Commit", "commit"),
    ISSUE("Issue", "issue"),
    SNIPPET("Snippet", "snippet");

    private final String code;

    private final String label;

    public static CommentTarget resolve(String code) {
        return Arrays.stream(values()).filter(commentTarget ->
                commentTarget.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(code));
    }
}
