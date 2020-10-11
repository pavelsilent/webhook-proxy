package pro.sisit.utils.webhookproxy.domain.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum SystemFilterEnum {
    ALL("ALL"),
    NOTHING("NOTHING");

    private final String code;
}
