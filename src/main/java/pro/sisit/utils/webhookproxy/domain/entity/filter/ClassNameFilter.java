package pro.sisit.utils.webhookproxy.domain.entity.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pro.sisit.utils.webhookproxy.domain.entity.Filter;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "filter_class_name_filter")
public class ClassNameFilter extends Filter {

    @Column(name = "class_name", nullable = false)
    private String className;
}
