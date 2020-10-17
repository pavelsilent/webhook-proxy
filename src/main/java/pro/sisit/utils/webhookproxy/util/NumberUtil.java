package pro.sisit.utils.webhookproxy.util;

import java.util.Optional;

public class NumberUtil {

    public static Long of(Integer value) {
        return Optional.ofNullable(value)
                       .map(Long::valueOf)
                       .orElse(null);
    }

    public static Long of(String value) {
        return Optional.ofNullable(value)
                       .map(Long::valueOf)
                       .orElse(null);
    }
}
