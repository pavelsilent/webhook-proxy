package pro.sisit.utils.webhookproxy.domain.enumeration.format;

import java.nio.charset.StandardCharsets;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum IconType {
    STARTED(new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x9A, (byte) 0x80}),
    SUCCESS(new byte[]{(byte) 0xE2, (byte) 0x9C, (byte) 0x85}),
    FAILURE(new byte[]{(byte) 0xE2, (byte) 0x9D, (byte) 0x8C});

    private final byte[] byteArray;

    public String asString() {
        return new String(byteArray, StandardCharsets.UTF_8);
    }
}
