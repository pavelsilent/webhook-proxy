package pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.ProjectDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.UserDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.WikiDTO;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data.WikiDataDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GitLabWikiDTO extends GitLabDTO {

    @JsonProperty("user")
    public UserDTO user;
    @JsonProperty("project")
    public ProjectDTO project;
    @JsonProperty("wiki")
    public WikiDTO wiki;
    @JsonProperty("object_attributes")
    public WikiDataDTO wikiData;
}
