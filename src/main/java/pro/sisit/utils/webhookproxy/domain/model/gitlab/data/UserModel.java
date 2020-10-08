package pro.sisit.utils.webhookproxy.domain.model.gitlab.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private Long externalId;

    private String name;

    private String login;

    private String email;

    private String avatarURL;
}
