package pro.sisit.utils.webhookproxy;

import com.pengrad.telegrambot.model.request.ParseMode;
import lombok.RequiredArgsConstructor;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import pro.sisit.utils.webhookproxy.domain.TelegramGroup;
import pro.sisit.utils.webhookproxy.service.TelegramSender;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@SpringBootApplication
@RequiredArgsConstructor
public class WebHookProxyApplication {

    private final Environment environment;

    private final TelegramSender telegramSender;

    public static void main(String[] args) {
        SpringApplication.run(WebHookProxyApplication.class, args);
    }

    // @PostConstruct
    public void test() {
        telegramSender.send(TelegramGroup.builder()
                .botId(environment.getProperty("telegram.bot.id"))
                .channelId(environment.getProperty("telegram.channel.id"))
                .build(), "Проверка отправки multi-language message - сообщения :-)", ParseMode.HTML);
    }
}
