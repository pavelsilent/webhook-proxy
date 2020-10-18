package pro.sisit.utils.webhookproxy.rest.dto.jenkins.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * WebHook generate by
 * https://plugins.jenkins.io/notification/
 */


@JsonInclude(JsonInclude.Include.NON_NULL)
public class JenkinsBuildDTO {

    /**
     * full_url=http://192.168.5.24:8080/job/data-types/63/
     */
    @JsonProperty("full_url")
    public String fullUrl;

    /**
     * number=63
     */
    @JsonProperty("number")
    public Integer number;

    /**
     * queue_id=7528
     */
    @JsonProperty("queue_id")
    public Integer queueId;

    /**
     * timestamp=1602953668814
     */
    @JsonProperty("timestamp")
    public Long timestamp;

    /**
     * phase=COMPLETED
     * QUEUED, STARTED, FINALIZED, COMPLETED
     */
    @JsonProperty("phase")
    public String phase;

    /**
     * status=FAILURE
     * SUCCESS, FAILURE, ABORTED
     */
    @JsonProperty("status")
    public String status;

    /**
     * url=job/data-types/63/
     */
    @JsonProperty("url")
    public String url;

    @JsonProperty("scm")
    public JenkinsSCMDTO scm;

    /**
     * parameters={BuildUniqueToken=runbuild}
     * Объект с полями:
     * "p1=v1"
     * "p2=v2"
     * ...
     */
    @JsonProperty("parameters")
    public Object parameters;

    /**
     * artifacts={
     * build/libs/data-types-1.3.63.RELEASE-sources.jar={
     *      archive=http://192.168.5.24:8080/job/data-types/63/artifact/build/libs/data-types-1.3.63.RELEASE-sources.jar},
     * build/libs/data-types-test-1.3.63.RELEASE.jar={
     *      archive=http://192.168.5.24:8080/job/data-types/63/artifact/build/libs/data-types-test-1.3.63.RELEASE.jar},
     * build/libs/data-types-1.3.63.RELEASE.jar={
     *      archive=http://192.168.5.24:8080/job/data-types/63/artifact/build/libs/data-types-1.3.63.RELEASE.jar}
     * }
     *
     * Объект с полями:
     * "artifactName={archive = archiveURL}"
     * "p2=v2"
     * ...
     */
    @JsonProperty("artifacts")
    public Object artifacts;


    @JsonProperty("log")
    public Object log;

    /**
     * extra info customize from plugin settings
     */
    @JsonProperty("notes")
    public String notes;
}
