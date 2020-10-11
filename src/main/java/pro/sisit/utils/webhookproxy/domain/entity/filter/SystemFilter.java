package pro.sisit.utils.webhookproxy.domain.entity.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pro.sisit.utils.webhookproxy.domain.entity.Filter;
import pro.sisit.utils.webhookproxy.domain.enumeration.SystemFilterEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "filter_system_filter")
public class SystemFilter extends Filter {

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private SystemFilterEnum type;
}
