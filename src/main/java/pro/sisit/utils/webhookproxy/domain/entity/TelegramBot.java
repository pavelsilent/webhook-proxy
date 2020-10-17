package pro.sisit.utils.webhookproxy.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "telegram_bot")
public class TelegramBot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "token", nullable = false, length = 1000)
    private String token;
}
