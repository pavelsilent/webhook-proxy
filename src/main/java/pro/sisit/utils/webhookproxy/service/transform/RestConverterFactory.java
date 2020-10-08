package pro.sisit.utils.webhookproxy.service.transform;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestConverterFactory {

    private final List<RestConverter> converters;

    public <T, K> T toModel(K dto) {
        return (T) toModelSoft(dto).orElseThrow(() -> new RuntimeException(dto.getClass().toString()));
    }

    public <T, K> T toModelSoft(K dto, T defaultValue) {
        return (T) toModelSoft(dto).orElse(defaultValue);
    }

    public <T, K> Optional<T> toModelSoft(K dto) {
        return converters.stream()
                .filter(service -> service.supports(dto))
                .findFirst()
                .map(service -> service.toModel(dto))
                .map(model -> (T) model);

    }

}
