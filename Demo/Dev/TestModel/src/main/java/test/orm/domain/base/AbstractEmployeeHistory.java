package test.orm.domain.base;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import test.orm.domain.ems.Employee;
import test.orm.domain.history.History;

@MappedSuperclass
public abstract class AbstractEmployeeHistory extends History {

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
