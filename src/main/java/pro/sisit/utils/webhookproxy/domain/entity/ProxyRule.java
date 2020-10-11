package pro.sisit.utils.webhookproxy.domain.entity;

import lombok.Data;
import pro.sisit.utils.webhookproxy.domain.Source;

import javax.persistence.*;

@Data
@Entity(name = "proxy_rule")
public class ProxyRule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "source", nullable = false)
    @Enumerated(EnumType.STRING)
    private Source source;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_id", nullable = false)
    private Target target;
}
