package pro.sisit.utils.webhookproxy.domain.entity.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pro.sisit.utils.webhookproxy.domain.entity.Filter;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "filter_field_value_filter")
public class FieldValueFilter extends Filter {

    @Column(name = "class_name", nullable = false, length = 1000)
    private String className;

    @Column(name = "property_path", nullable = false, length = 1000)
    private String propertyPath;

    @Column(name = "property_value", nullable = false, length = 1000)
    private String propertyValue;
}
