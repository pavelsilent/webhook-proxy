package pro.sisit.utils.webhookproxy.domain.model.gitlab.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectModel {

    private Long externalId;

    private String name;

    private String fullName;

    private String url;

    public String getBranch(String ref) {
        return Optional.ofNullable(ref)
                .map(s -> s.split("/"))
                .filter(strings -> strings.length > 0)
                .map(strings -> strings[strings.length - 1])
                .orElse("<unknown>");
    }

    public String getBranchFullName(String ref) {
        return fullName + "/" + getBranch(ref);
    }
}
