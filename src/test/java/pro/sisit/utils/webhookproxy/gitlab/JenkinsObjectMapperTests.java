package pro.sisit.utils.webhookproxy.gitlab;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pro.sisit.utils.webhookproxy.rest.dto.jenkins.JenkinsEventDTO;

class JenkinsObjectMapperTests {

    @Test
    void testObjectMapper() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(
            classLoader.getResource("jenkins/notification-plugin-build-event.json")).getFile());

        ObjectMapper mapper = new ObjectMapper();
        JenkinsEventDTO dto = mapper.readValue(file, JenkinsEventDTO.class);
        Assertions.assertEquals(JenkinsEventDTO.class, dto.getClass());
    }
}
