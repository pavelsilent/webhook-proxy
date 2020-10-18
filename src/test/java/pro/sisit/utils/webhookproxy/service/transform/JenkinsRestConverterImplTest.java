package pro.sisit.utils.webhookproxy.service.transform;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.JenkinsEvent;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.data.CommitModel;
import pro.sisit.utils.webhookproxy.rest.dto.jenkins.JenkinsEventDTO;

public class JenkinsRestConverterImplTest {

    private JenkinsEventDTO dto;

    private JenkinsRestConverterImpl restConverter;

    @BeforeEach
    public void before() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(
            classLoader.getResource("jenkins/notification-plugin-build-event.json")).getFile());

        ObjectMapper mapper = new ObjectMapper();
        dto = mapper.readValue(file, JenkinsEventDTO.class);
        Assertions.assertEquals(JenkinsEventDTO.class, dto.getClass());

        restConverter = new JenkinsRestConverterImpl();
    }

    @Test
    void testConvertToEventModel() throws IOException {
        JenkinsEvent model = restConverter.toModel(dto);
        Assertions.assertNotNull(model);
        Assertions.assertEquals(JenkinsEvent.class, model.getClass());
    }

    @Test
    void testConvertToCommitModel() {
        CommitModel model = restConverter.toModel(dto.build.scm);
        Assertions.assertNotNull(model);
        Assertions.assertEquals(CommitModel.class, model.getClass());
        Assertions.assertEquals("git@192.168.5.156.nip.io:etalon/jetalon_libs/data-types.git",
            model.getSshRepoUrl());
    }

    @Test
    void parseParams() {
        Map<String, String> parameters = restConverter.parseParams(dto.build.parameters);
        Assertions.assertEquals(4, parameters.size());
        Assertions.assertTrue(parameters.containsKey("BuildUniqueToken"));
        Assertions.assertEquals("runbuild", parameters.get("BuildUniqueToken"));
    }

    @Test
    void parseArtifacts() {
        Map<String, String> artifacts = restConverter.parseArtifacts(dto.build.artifacts);
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

    @Test
    void parseProjectUrl() {
        Assertions.assertEquals("http://192.168.5.24:8080/job/data-types/",
            restConverter.parseProjectUrl(dto));
    }

    @Test
    void parseProjectFullName() {
        Assertions.assertEquals("etalon/jetalon_libs/data-types",
            restConverter.parseProjectFullName(dto));
    }
}
