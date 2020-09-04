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
    @JsonSubTypes.Type(value = WebHookCommentDTO.class, name = "note"),
    @JsonSubTypes.Type(value = WebHookPushDTO.class, name = "push"),
    @JsonSubTypes.Type(value = WebHookTagPushDTO.class, name = "tag_push"),
    @JsonSubTypes.Type(value = WebHookIssueDTO.class, name = "issue"),
    @JsonSubTypes.Type(value = WebHookMergeRequestDTO.class, name = "merge_request"),
    @JsonSubTypes.Type(value = WebHookWikiDTO.class, name = "wiki_page"),
    @JsonSubTypes.Type(value = WebHookPipelineDTO.class, name = "pipeline"),
    @JsonSubTypes.Type(value = WebHookJobDTO.class, name = "build"),
})
public class WebHookDTO {

    @JsonProperty("object_kind")
    public String objectKind;

}
