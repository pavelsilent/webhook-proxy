package pro.sisit.utils.webhookproxy.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.WebHookDTO;

@RestController
@RequiredArgsConstructor
public class WebHookController {

    @PostMapping(value = "/receive", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void receive(@RequestBody WebHookDTO data) {

        System.out.println("WebHookController: receive");
        System.out.println(data.getClass());


    }

}
