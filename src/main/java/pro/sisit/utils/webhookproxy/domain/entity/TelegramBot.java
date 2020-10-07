package pro.sisit.utils.webhookproxy.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "telegram_bot")
public class TelegramBot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token", nullable = false)
    private String token;
}
