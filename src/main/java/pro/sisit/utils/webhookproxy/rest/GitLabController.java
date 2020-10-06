package pro.sisit.utils.webhookproxy.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.GitLabWebHookDTO;

@RestController
@RequiredArgsConstructor
public class GitLabController {

    @PostMapping(value = "gitlab/receive", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void receive(@RequestBody GitLabWebHookDTO data) {

        System.out.println("GitLabController: receive");
        System.out.println(data.getClass());
    }

}
