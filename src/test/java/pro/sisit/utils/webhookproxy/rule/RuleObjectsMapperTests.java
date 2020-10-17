package pro.sisit.utils.webhookproxy.rule;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabDTO;
import pro.sisit.utils.webhookproxy.rest.dto.rule.ProxyRuleInitialDTO;

class RuleObjectsMapperTests {

    @ParameterizedTest
    @ArgumentsSource(TestRuleObjectsMapperTests.class)
    void testObjectMapper(String jsonFileName, Class expectedClass)
        throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(jsonFileName)).getFile());

        ObjectMapper mapper = new ObjectMapper();
        Object dto = mapper.readValue(file, expectedClass);
        Assertions.assertEquals(expectedClass, dto.getClass());
    }

    static class TestRuleObjectsMapperTests implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                new Object[]{"rule/proxy-rule-initial-dto.json", ProxyRuleInitialDTO.class},
                new Object[]{"rule/proxy-rule-initial-dto.json", ProxyRuleInitialDTO.class})
                         .map(Arguments::of);
        }
    }
}
