package pro.sisit.utils.webhookproxy.domain.model.gitlab.data;

import lombok.Data;

@Data
public class UserModel {

    private Long externalId;

    private String name;

    private String login;

    private String email;

    private String avatarURL;
}
