package pro.sisit.utils.webhookproxy;

import com.pengrad.telegrambot.model.request.ParseMode;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pro.sisit.utils.webhookproxy.domain.model.telegram.TelegramGroup;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
@EnableJpaRepositories("pro.sisit.utils")
public class WebHookProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebHookProxyApplication.class, args);
    }

//    @PostConstruct
//    public void test() {
//        telegramSender.send(TelegramGroup.builder()
//                .botId(environment.getProperty("telegram.bot.id"))
//                .channelId(environment.getProperty("telegram.channel.id"))
//                .build(), "Проверка отправки multi-language message - сообщения :-)", ParseMode.HTML);
//    }
}
