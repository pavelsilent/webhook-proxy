package pro.sisit.utils.webhookproxy.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pro.sisit.utils.webhookproxy.rest.dto.jenkins.JenkinsBuildEventDTO;

@RestController
@RequiredArgsConstructor
public class JenkinsController {

    @PostMapping(value = "jenkins/receive", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void receive(@RequestBody JenkinsBuildEventDTO data) {

        System.out.println("JenkinsController: receive");
        System.out.println(data.getClass());
    }

}
