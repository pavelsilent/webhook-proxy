package pro.sisit.utils.webhookproxy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pro.sisit.utils.webhookproxy.domain.TelegramGroup;

@Service
@RequiredArgsConstructor
public class TelegramSenderImpl implements TelegramSender {
    // https://api.telegram.org/bot1104514641:AAHgsNR4J4A9P0CZIo_dNn-nZWAevDMhWo4/
    // sendMessage?chat_id=-435910891&text=
    // Build%20%23${BUILD_NUMBER}%20of%20jetalon_master_application%20STARTED%20link%20${BUILD_URL}

    private final Environment environment;

    @Override
    public void send(TelegramGroup telegramGroup, String text) {
        String url = String.format("%s/bot%s/sendMessage?chat_id=%s&parse_mode=%s&text=%s",
                environment.getProperty("telegram.api.url"),
                telegramGroup.getBotId(),
                telegramGroup.getChannelId(),
//                "MarkdownV2",
                "html",
                text);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class);

        System.out.println(response);
    }

}
