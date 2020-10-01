package pro.sisit.utils.webhookproxy.service.transform.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GitlabEventRestConverterFactory {

    private final List<GitlabEventRestConverter> converters;

    public <T, K> T toModel(K dto) {
        return converters.stream()
                .filter(service -> service.supports(dto))
                .findFirst()
                .map(service -> service.toModel(dto))
                .map(model -> (T) model)
                .orElseThrow(() -> new RuntimeException(dto.getClass().toString()));
    }

}
