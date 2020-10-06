package pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import org.springframework.validation.annotation.Validated;

@Validated
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
    include = As.PROPERTY,
    property = "object_kind",
    visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = GitLabWebHookCommentDTO.class, name = "note"),
    @JsonSubTypes.Type(value = GitLabWebHookPushDTO.class, name = "push"),
    @JsonSubTypes.Type(value = GitLabWebHookTagPushDTO.class, name = "tag_push"),
    @JsonSubTypes.Type(value = GitLabWebHookIssueDTO.class, name = "issue"),
    @JsonSubTypes.Type(value = GitLabWebHookMergeRequestDTO.class, name = "merge_request"),
    @JsonSubTypes.Type(value = GitLabWebHookWikiDTO.class, name = "wiki_page"),
    @JsonSubTypes.Type(value = GitLabWebHookPipelineDTO.class, name = "pipeline"),
    @JsonSubTypes.Type(value = GitLabWebHookJobDTO.class, name = "build"),
})
public class GitLabWebHookDTO {

    @JsonProperty("object_kind")
    public String objectKind;

}
