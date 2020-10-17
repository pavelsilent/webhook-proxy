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
    @JsonSubTypes.Type(value = GitLabCommentDTO.class, name = "note"),
    @JsonSubTypes.Type(value = GitLabPushDTO.class, name = "push"),
    @JsonSubTypes.Type(value = GitLabTagPushDTO.class, name = "tag_push"),
    @JsonSubTypes.Type(value = GitLabIssueDTO.class, name = "issue"),
    @JsonSubTypes.Type(value = GitLabMergeRequestDTO.class, name = "merge_request"),
    @JsonSubTypes.Type(value = GitLabWikiDTO.class, name = "wiki_page"),
    @JsonSubTypes.Type(value = GitLabPipelineDTO.class, name = "pipeline"),
    @JsonSubTypes.Type(value = GitLabJobDTO.class, name = "build"),
})
public class GitLabDTO {

    @JsonProperty("object_kind")
    public String objectKind;

}
