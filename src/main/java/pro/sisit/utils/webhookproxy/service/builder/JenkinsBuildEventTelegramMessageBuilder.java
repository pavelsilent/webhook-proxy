package pro.sisit.utils.webhookproxy.service.builder;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.Event;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.JenkinsEvent;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.data.CommitModel;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.data.JobModel;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.data.ProjectModel;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.enumeration.BuildParameters;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.enumeration.JobBuildState;
import pro.sisit.utils.webhookproxy.domain.model.telegram.Message;
import pro.sisit.utils.webhookproxy.service.format.TelegramMessageFormatterHTML;

@Service
@RequiredArgsConstructor
public class JenkinsBuildEventTelegramMessageBuilder implements TelegramMessageBuilder<JenkinsEvent> {

    private final TelegramMessageFormatterHTML messageFormatter;

    @Override
    public Message toMessage(JenkinsEvent event) {
        // finalized / completed  -> failure / success / aborted
        // started

        ProjectModel project = event.getProject();
        JobModel job = event.getJob();
        CommitModel commit = event.getCommit();

        JobBuildState state = JobBuildState.resolveSoft(job.getPhase(), job.getStatus());

        String message = "has " + messageFormatter.boldUnderline("UNKNOWN") + " state";
        if (state.equals(JobBuildState.STARTED)) {
            message = "was " + messageFormatter.boldUnderline("STARTED");
        } else if (state.equals(JobBuildState.SUCCESS)) {
            message = "was finished " + messageFormatter.boldUnderline("SUCCESSFULLY");
        } else if (state.equals(JobBuildState.FAILURE)) {
            message = "was " + messageFormatter.boldUnderline("FAILURE");
        } else if (state.equals(JobBuildState.ABORTED)) {
            message = "was " + messageFormatter.boldUnderline("ABORTED");
        }

        if (state.equals(JobBuildState.FAILURE)) {
            if (job.getParameters().containsKey(BuildParameters.SONARQUBE_REPORT.getCode())) {
                message += "\n" + messageFormatter.link(
                    job.getParameters().get(BuildParameters.SONARQUBE_REPORT.getCode()),
                    "Sonarqube analysis report");
            }

            if (job.getParameters().containsKey(BuildParameters.TEST_REPORT.getCode())) {
                String link = job.getParameters().get(BuildParameters.TEST_REPORT.getCode());
                message += "\nTest report: " + link;
            }
        }

        String jobName = String.format("job #%s (branch: %s)", job.getNumber(), commit.getBranch());

        return toMessage(
            String.format("Project %s %s%nJenkins %s %n%s",
                messageFormatter.link(commit.getHttpRepoUrl(),
                    Optional.ofNullable(project.getFullName()).orElseGet(project::getName)),
                state.getIcon().asString(),
                messageFormatter.link(job.getUrl(), jobName),
                message),
            messageFormatter.getParseMode());
    }

    @Override
    public <E extends Event> boolean supports(E event) {
        return event instanceof JenkinsEvent;
    }
}
