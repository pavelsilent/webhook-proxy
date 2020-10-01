package pro.sisit.utils.webhookproxy.domain.model.gitlab.data;

import lombok.Data;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.enumeration.CommentTarget;

@Data
public class CommentModel {

    private Long externalId;

    private CommentTarget target;

    private String text;

    private String url;

    private Long authorId;

}
