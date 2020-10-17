package pro.sisit.utils.webhookproxy.rest.dto.rule;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import org.springframework.validation.annotation.Validated;
import pro.sisit.utils.webhookproxy.domain.enumeration.TargetTypeEnum;
import pro.sisit.utils.webhookproxy.rest.dto.rule.target.TelegramChannelTargetDTO;

@Validated
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
    include = As.PROPERTY,
    property = "type",
    visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = TelegramChannelTargetDTO.class, name = "TELEGRAM_CHANNEL"),
})
public class TargetDTO {

    @JsonProperty("type")
    public TargetTypeEnum type;
}
