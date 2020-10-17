package pro.sisit.utils.webhookproxy.domain.model.gitlab.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro.sisit.utils.webhookproxy.util.StringUtil;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommitModel {

    private String externalId;

    private UserModel author;

    private String message;

    private String url;

    public String getShortMessage() {
        return StringUtil.getCutEscapedString(message);
    }
}
