package pro.sisit.utils.webhookproxy.domain.model.gitlab.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.enumeration.CommentTarget;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentModel {

    private Long externalId;

    private CommentTarget target;

    private String text;

    private String url;

    private Long authorId;

}
