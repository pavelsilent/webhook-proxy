package pro.sisit.utils.webhookproxy.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "proxy_rule_filter")
public class ProxyRuleFilter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rule_id", nullable = false)
    private ProxyRule rule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filter_id", nullable = false)
    private Filter filter;
}
