package pro.sisit.utils.webhookproxy.domain.model.gitlab.data;

import lombok.Data;
import pro.sisit.utils.webhookproxy.util.StringUtil;

@Data
public class CommitModel {

    private String externalId;

    private UserModel author;

    private String message;

    private String url;

    public String getShortMessage() {
        return StringUtil.getCutEscapedString(message);
    }
}
