package test.orm.mappings05.self;


import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.junit.Before;
import org.junit.Test;

import lombok.Data;
import test.orm.core.AbstractJPAProgrammaticBootstrapTest;


public class Test_SelfReferenceBiLinkTableEntityHistory extends AbstractJPAProgrammaticBootstrapTest{

	   @Override
	    protected Class<?>[] entities() {
	        return new Class[] {    //...insert here your entities
				Employee.class,
					EmployeeRelationHistory.class
	        };
	    }


	    @Before
	    public void init() {
	    	
	    }
	   
	   @Test
	   public void testCrud() {



		   doInJPATransaction(em -> {
			   	 //em.createNativeQuery("drop database testOrm").executeUpdate();
			   	 //em.createNativeQuery("create database testOrm").executeUpdate();
				 allHiredFirstDay(em); 
				
		   });

		  

	   }




	@Table(name = "history")
		@Entity
		@Data
		static class EmployeeRelationHistory implements Serializable {

			@Id
			@GeneratedValue(strategy = GenerationType.AUTO)
			private Long id;

			@Column(name = "start")
			private ZonedDateTime from;

			@Column(name = "end")
			private ZonedDateTime until;

			@ManyToOne(cascade = CascadeType.ALL)
			@JoinColumn(name = "manager_id")
			public Employee manager;

			@ManyToOne(cascade = CascadeType.ALL)
			@JoinColumn(name = "subordinate_id")
			public Employee subordinate;

			@Column(name = "relationType")
			public RelationType relationType;


		}

		enum RelationType{
	   		DOTTED_LINE,
			SOLID_LINE
		}

	 	@Table(name = "employee")
		@Entity
		@Data
	   static class Employee implements Serializable {

			@Id
			@GeneratedValue(strategy = GenerationType.AUTO)
			private Long id;

			@Column(name = "name")
			private String employeeName;

//			@OneToMany(mappedBy = "manager")
//			private List<EmployeeRelationHistory> subordinateHistory;
//
//			@OneToMany(mappedBy = "subordinate")
//			private List<EmployeeRelationHistory> managerHistory;
			
			
			@ManyToOne(cascade = CascadeType.ALL)
			@JoinColumn(name = "manager_id")
			public Employee solidLineManager;

			@ManyToOne(cascade = CascadeType.ALL)
			@JoinColumn(name = "subordinate_id")
			public Employee dottedLineManager;
			
			@ManyToOne(cascade = CascadeType.ALL)
			@JoinColumn(name = "hr_responsible_id")
			public Employee hrResponsible;			

	   }


		private void allHiredFirstDay(EntityManager em){
			Employee manager1 = new Employee();
			Employee manager2 = new Employee();
			Employee stefano = new Employee();
			Employee raul = new Employee();

			manager1.setEmployeeName("HugoBoss1");
			manager2.setEmployeeName("HugoBoss2");
			stefano.setEmployeeName("Stefano");
			raul.setEmployeeName("Raul");

			em.persist(manager1);
			em.persist(manager2);
			em.persist(stefano);
			em.persist(raul);

			stefano.setDottedLineManager(manager1);
			stefano.setSolidLineManager(manager2);
			stefano.setHrResponsible(raul);
			
		}
}


