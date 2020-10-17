package pro.sisit.utils.webhookproxy;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@RequiredArgsConstructor
@EnableJpaRepositories("pro.sisit.utils")
public class WebHookProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebHookProxyApplication.class, args);
    }
}
