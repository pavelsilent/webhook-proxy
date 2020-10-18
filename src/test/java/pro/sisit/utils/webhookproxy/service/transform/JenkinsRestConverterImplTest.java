package pro.sisit.utils.webhookproxy.service.transform;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.JenkinsEvent;
import pro.sisit.utils.webhookproxy.rest.dto.jenkins.JenkinsEventDTO;

public class JenkinsRestConverterImplTest {

    @Test
    public void testConvertToEvent() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(
            classLoader.getResource("jenkins/notification-plugin-build-event.json")).getFile());

        ObjectMapper mapper = new ObjectMapper();
        JenkinsEventDTO dto = mapper.readValue(file, JenkinsEventDTO.class);
        Assertions.assertEquals(JenkinsEventDTO.class, dto.getClass());

        JenkinsRestConverterImpl restConverter = new JenkinsRestConverterImpl();
        JenkinsEvent model = restConverter.toModel(dto);

        Assertions.assertEquals(JenkinsEvent.class, model.getClass());
        Assertions.assertEquals("http://192.168.5.24:8080/job/data-types/",
            model.getProject().getUrl());

        Map<String, String> parameters = model.getJob().getParameters();
        Assertions.assertEquals(1, parameters.size());
        Assertions.assertTrue(parameters.containsKey("BuildUniqueToken"));
        Assertions.assertEquals("runbuild", parameters.get("BuildUniqueToken"));

        Map<String, String> artifacts = model.getJob().getArtifacts();
        Assertions.assertEquals(3, artifacts.size());

        String jar1 = "build/libs/data-types-1.3.61.RELEASE.jar";
        Assertions.assertTrue(artifacts.containsKey(jar1));
        Assertions.assertEquals(
            "http://192.168.5.24:8080/job/data-types/61/artifact/build/libs/data-types-1.3.61.RELEASE.jar",
            artifacts.get(jar1));

        String jar2 = "build/libs/data-types-test-1.3.61.RELEASE.jar";
        Assertions.assertTrue(artifacts.containsKey(jar2));
        Assertions.assertEquals(
            "http://192.168.5.24:8080/job/data-types/61/artifact/build/libs/data-types-test-1.3.61.RELEASE.jar",
            artifacts.get(jar2));

        String jar3 = "build/libs/data-types-1.3.61.RELEASE-sources.jar";
        Assertions.assertTrue(artifacts.containsKey(jar3));
        Assertions.assertEquals(
            "http://192.168.5.24:8080/job/data-types/61/artifact/build/libs/data-types-1.3.61.RELEASE-sources.jar",
            artifacts.get(jar3));
    }
}
