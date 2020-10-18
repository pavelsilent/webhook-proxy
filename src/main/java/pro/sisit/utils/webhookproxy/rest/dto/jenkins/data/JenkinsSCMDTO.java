package pro.sisit.utils.webhookproxy.rest.dto.jenkins.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * WebHook generate by
 * https://plugins.jenkins.io/notification/
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JenkinsSCMDTO {

    /**
     * url=git@192.168.5.156.nip.io:etalon/jetalon_libs/data-types.git
     */
    @JsonProperty("url")
    public String url;

    /**
     * branch=origin/master
     */
    @JsonProperty("branch")
    public String branch;

    /**
     * commit=6bbba0594ef3a6ca0718faac675e3d4404b0c4f6
     */
    @JsonProperty("commit")
    public String commit;

    /**
     * changes=[src/main/java/pro/sisit/etalon/jetalibs/datatypes/utils/NumberUtils.java]
     */
    @JsonProperty("changes")
    public List<String> changes;

    /**
     * culprits=[p.brichev]
     * culprits - виновники :-)
     */
    @JsonProperty("culprits")
    public List<String> culprits;
}
