package pro.sisit.utils.webhookproxy.domain.model.gitlab.data;

import lombok.Data;

@Data
public class CommitModel {

    private String externalId;

    private UserModel author;

    private String message;

    private String url;
}
