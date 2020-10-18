package pro.sisit.utils.webhookproxy.service.builder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.Event;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.JenkinsEvent;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.data.CommitModel;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.data.JobModel;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.data.ProjectModel;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.enumeration.JobPhase;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.enumeration.JobStatus;
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

        JobPhase phase = job.getPhase();
        JobStatus status = job.getStatus();

        String message = "has " + messageFormatter.boldUnderline("UNKNOWN") + " state";
        if (phase.equals(JobPhase.STARTED)) {
            message = "was " + messageFormatter.boldUnderline("STARTED");
        } else if (phase.equals(JobPhase.COMPLETED) || phase.equals(JobPhase.FINALIZED)) {
            if (status.equals(JobStatus.SUCCESS)) {
                message = "was finished " + messageFormatter.boldUnderline("SUCCESSFULLY");
            } else if (status.equals(JobStatus.FAILURE)) {
                message = "was " + messageFormatter.boldUnderline("FAILURE");
            } else if (status.equals(JobStatus.ABORTED)) {
                message = "was " + messageFormatter.boldUnderline("ABORTED");
            }
        }

        String jobName = String.format("job #%s (branch: %s)", job.getNumber(), commit.getBranch());

        return toMessage(
            String.format("Project %s.%nJenkins %s %n%s.",
                messageFormatter.link(commit.getUrl(), project.getName()),
                messageFormatter.link(job.getUrl(), jobName),
                message),
            messageFormatter.getParseMode());
    }

    @Override
    public <E extends Event> boolean supports(E event) {
        return event instanceof JenkinsEvent;
    }
}
