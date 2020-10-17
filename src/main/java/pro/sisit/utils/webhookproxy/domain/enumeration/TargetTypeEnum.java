package pro.sisit.utils.webhookproxy.domain.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum TargetTypeEnum {
    TELEGRAM_CHANNEL("TELEGRAM_CHANNEL");

    private final String code;
}
