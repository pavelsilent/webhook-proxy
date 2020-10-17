package pro.sisit.utils.webhookproxy;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(excludeFilters = {
    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = WebHookProxyApplication.class)})
@EnableJpaRepositories
@EntityScan
public class WebHookProxyTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebHookProxyTestApplication.class, args);
    }
}
