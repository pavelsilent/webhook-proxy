package pro.sisit.utils.webhookproxy.rest.dto.gitlab;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum GitLabObjectKind {
    note("note"),
    push("push"),
    tag_push("tag_push"),
    issue("issue"),
    merge_request("merge_request"),
    wiki_page("wiki_page"),
    pipeline("pipeline"),
    build("build");

    private final String name;
}
