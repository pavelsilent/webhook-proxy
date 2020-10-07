package pro.sisit.utils.webhookproxy.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Source {
    GITLAB("gitLab"),
    JENKINS("jenkins");

    private final String code;
}
