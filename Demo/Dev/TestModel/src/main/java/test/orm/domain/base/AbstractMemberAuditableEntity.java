package test.orm.domain.base;


import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import test.orm.domain.Member;

@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractMemberAuditableEntity extends AbstractAuditableEntity{

	 @ManyToOne
     @JoinColumn(name = "created_by_id")
     private Member createdBy;
}
