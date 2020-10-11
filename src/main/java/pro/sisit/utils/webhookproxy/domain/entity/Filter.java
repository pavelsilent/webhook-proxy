package pro.sisit.utils.webhookproxy.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "filter")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Filter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
