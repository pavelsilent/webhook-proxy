package pro.sisit.utils.webhookproxy.service.transform;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.JenkinsEvent;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.data.CommitModel;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.data.JobModel;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.data.ProjectModel;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.enumeration.JobPhase;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.enumeration.JobStatus;
import pro.sisit.utils.webhookproxy.rest.dto.jenkins.JenkinsEventDTO;
import pro.sisit.utils.webhookproxy.rest.dto.jenkins.data.JenkinsBuildDTO;
import pro.sisit.utils.webhookproxy.rest.dto.jenkins.data.JenkinsSCMDTO;
import pro.sisit.utils.webhookproxy.util.StringUtil;

@Service
public class JenkinsRestConverterImpl implements JenkinsRestConverter {

    @Override
    public JenkinsEvent toModel(JenkinsEventDTO dto) {
        if (dto == null) {
            return null;
        }

        ProjectModel projectModel = new ProjectModel();
        projectModel.setName(dto.name);
        projectModel.setFullName(parseProjectFullName(dto));
        projectModel.setUrl(parseProjectUrl(dto));

        JenkinsEvent model = new JenkinsEvent();
        model.setProject(projectModel);
        model.setJob(
            toModel(dto.build));
        model.setCommit(
            toModel(Optional.ofNullable(dto.build)
                            .map(buildDTO -> buildDTO.scm)
                            .orElse(null)));

        return model;
    }

    public String parseProjectFullName(JenkinsEventDTO dto) {
        // git@192.168.5.156.nip.io:etalon/jetalon_libs/data-types.git
        return Optional.ofNullable(dto)
                       .map(event -> event.build)
                       .map(build -> build.scm)
                       .map(scm -> scm.url)
                       .map(repoUrl -> repoUrl.split(":"))
                       .filter(strings -> strings.length == 2)
                       .map(strings -> strings[1])
                       .map(urlPart -> urlPart.split("\\.git"))
                       .filter(strings -> strings.length >= 1)
                       .map(strings -> strings[0])
                       .orElse(null);
    }

    public String parseProjectUrl(JenkinsEventDTO dto) {
        if (dto == null || dto.name == null) {
            return null;
        }

        String projectName = dto.name;
        return Optional.ofNullable(dto.build)
                       .map(buildDTO -> buildDTO.fullUrl)
                       .map(fullUrl -> fullUrl.split(projectName))
                       .filter(splitUrlArray -> splitUrlArray.length > 0)
                       .map(splitUrlArray -> splitUrlArray[0])
                       .map(urlPart -> String.format("%s%s/", urlPart, projectName))
                       .orElse(null);
    }

    @Override
    public JobModel toModel(JenkinsBuildDTO dto) {
        if (dto == null) {
            return null;
        }

        JobModel model = new JobModel();
        model.setNumber(dto.number);
        model.setUrl(dto.fullUrl);
        model.setStatus(JobStatus.resolveSoft(dto.status).orElse(null));
        model.setPhase(JobPhase.resolveSoft(dto.phase).orElse(null));
        model.setCustomMessage(dto.notes);
        model.setParameters(parseParams(dto.parameters));
        model.setArtifacts(parseArtifacts(dto.artifacts));

        return model;
    }

    @Override
    public CommitModel toModel(JenkinsSCMDTO dto) {
        if (dto == null) {
            return null;
        }

        CommitModel model = new CommitModel();
        model.setSshRepoUrl(dto.url);
        model.setHttpRepoUrl(StringUtil.buildGitURL(dto.url, false));
        model.setBranch(dto.branch);
        model.setCommit(dto.commit);
        model.setChanges(dto.changes);
        model.setCulprits(dto.culprits);
        return model;
    }

    public Map<String, String> parseParams(Object params) {
        try {
            String preParse = params.toString().substring(1, params.toString().length() - 1);

            Map<String, String> parameters = new HashMap<>();
            Arrays.stream(preParse.split(", "))
//                  .peek(s -> System.out.println(s))
                  .map(s -> s.replaceFirst("=", "~"))
                  .map(s -> s.split("~"))
                  .filter(strings -> strings.length == 2)
                  .forEach(strings -> parameters.put(
                      strings[0],
                      strings[1]));

//            parameters.forEach((s, s2) -> System.out.println(s + " => " + s2));
            return parameters;
        } catch (RuntimeException ex) {
            return new HashMap<>();
        }
    }

    public Map<String, String> parseArtifacts(Object params) {
        try {
            String preParse = params.toString().substring(1, params.toString().length() - 1);
            Map<String, String> artifacts = new HashMap<>();
            Arrays.stream(preParse.split(", "))
                  .map(s -> s.split("=\\{archive="))
                  .filter(strings -> strings.length == 2)
                  .forEach(strings -> artifacts.put(
                      strings[0],
                      strings[1].substring(0, strings[1].length() - 1)));

            // artifacts.forEach((s, s2) -> System.out.println(s + " => " + s2));
            return artifacts;
        } catch (RuntimeException ex) {
            return new HashMap<>();
        }
    }
}
