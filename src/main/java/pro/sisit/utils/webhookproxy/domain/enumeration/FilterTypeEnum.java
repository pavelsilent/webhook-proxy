package pro.sisit.utils.webhookproxy.domain.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum FilterTypeEnum {
    SYSTEM("SYSTEM"),
    CLASS_NAME("CLASS_NAME"),
    FIELD_VALUE("FIELD_VALUE");

    private final String code;
}
