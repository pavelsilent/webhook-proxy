package pro.sisit.utils.webhookproxy.util;

import java.time.LocalDateTime;
import java.util.Optional;

public class DateUtil {

    public static LocalDateTime of(String value) {
        return Optional.ofNullable(value)
                .map(s -> s.replace("Z", ""))
                .map(LocalDateTime::parse)
                .orElse(null);
    }
}
