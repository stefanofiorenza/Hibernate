package test.orm.domain.history;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import test.orm.domain.RelationType;
import test.orm.domain.base.AbstractEmployeeHistory;
import test.orm.domain.ems.Employee;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "employee_employee_history")
@Entity
public class EmployeeHistoryRecord extends AbstractEmployeeHistory {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ref_employee_id")
    private Employee referenced;

    @Column(name = "relation_type")
    private RelationType relationType;

}
