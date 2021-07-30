package com.knits.jpa.orm.entities04.custom;

import lombok.Data;
import org.junit.Test;

import com.hibernate.bootstrap.util.AbstractJPAProgrammaticBootstrapTest;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


public class Test_SelfReferenceBidirectional extends AbstractJPAProgrammaticBootstrapTest{

	   @Override
	    protected Class<?>[] entities() {
	        return new Class[] {    //...insert here your entities
				Employee.class
	        };
	    }
	   
	   
	   @Test
	   public void testCrud() {

		   doInJPATransaction(em -> {


			   Employee manager = new Employee();
			   manager.setEmployeeName("ManagerName1");

			   Employee subordinate1 = new Employee();
			   subordinate1.setEmployeeName("subordinate1");
			   Employee subordinate2 = new Employee();
			   subordinate2.setEmployeeName("subordinate2");


			   em.persist(manager);
			   em.persist(subordinate1);
			   em.persist(subordinate2);

			   subordinate1.setDottedLineManager(manager);
			   subordinate2.setDottedLineManager(manager);
		   });


		   doInJPATransaction(em -> {


			   Employee manager = new Employee();
			   manager.setEmployeeName("ManagerName1");

			   Employee subordinate1 = new Employee();
			   subordinate1.setEmployeeName("subordinate1");
			   Employee subordinate2 = new Employee();
			   subordinate2.setEmployeeName("subordinate2");


			   em.persist(manager);
			   em.persist(subordinate1);
			   em.persist(subordinate2);

			   subordinate1.setDottedLineManager(manager);
			   subordinate2.setDottedLineManager(manager);
		   });
		  
	   }
	   
	 	@Table(name = "employee")
		@Entity
		@Data
	   static class Employee implements Serializable {

			@Id
			@GeneratedValue(strategy = GenerationType.AUTO)
			private Long id;

			@Column(name = "last_synch")
			private String employeeName;


			//	OneToMany (mappedBy = "parentCategory")
			@OneToMany(mappedBy = "dottedLineManager")
			private List<Employee> subordinates;

			@ManyToOne()
			@JoinTable(name = "dl_manager_history",
					joinColumns = {@JoinColumn(name = "id")},
					inverseJoinColumns = {@JoinColumn(name = "manager_id")}
			)
			private Employee dottedLineManager;


	   }



}


