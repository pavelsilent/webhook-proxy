package pro.sisit.utils.webhookproxy.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "target")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Target {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
