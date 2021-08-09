package test.orm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import test.orm.domain.base.AbstractMemberAuditableEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "rate")
public class Rate extends AbstractMemberAuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "rate")
    private Rating rate;

    @Column(name = "comment")
    private String comment;

}
