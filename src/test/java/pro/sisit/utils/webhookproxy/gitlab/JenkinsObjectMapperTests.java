package pro.sisit.utils.webhookproxy.gitlab;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pro.sisit.utils.webhookproxy.rest.dto.jenkins.JenkinsBuildEventDTO;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

class JenkinsObjectMapperTests {

    @Test
    void testObjectMapper() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(
                classLoader.getResource("jenkins/build-event.json")).getFile());

        ObjectMapper mapper = new ObjectMapper();
        JenkinsBuildEventDTO dto = mapper.readValue(file, JenkinsBuildEventDTO.class);
        Assertions.assertEquals(JenkinsBuildEventDTO.class, dto.getClass());
    }
}
