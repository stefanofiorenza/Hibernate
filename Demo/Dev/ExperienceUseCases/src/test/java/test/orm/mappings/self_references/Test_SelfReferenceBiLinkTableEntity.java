package test.orm.mappings.self_references;


import lombok.Data;
import test.orm.core.AbstractJPAProgrammaticBootstrapTest;

import org.junit.Ignore;
import org.junit.Test;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;


@Ignore
public class Test_SelfReferenceBiLinkTableEntity extends AbstractJPAProgrammaticBootstrapTest{

	   @Override
	    protected Class<?>[] entities() {
	        return new Class[] {    //...insert here your entities
				Employee.class,
					EmployeeRelationHistory.class
	        };
	    }


	   
	   @Test
	   public void testCrud() {



		   doInJPATransaction(em -> {

			   allHiredFirstDay(em);
			   stefanoAndRaulAreAssignedToHugoBoss(em);



		   });

		   //.. some years after ..
		   doInJPATransaction(em -> {

		   	   hiredNewManager(em);
			   stefanoIsAssignedToNewManager(em);
		   });


		   //.. some years after ..
		   doInJPATransaction(em -> {

			   newHiredAssignedToHugoBoss(em);
			   queryHistories(em);
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

			@OneToMany(mappedBy = "manager")
			private List<EmployeeRelationHistory> subordinateHistory;

			@OneToMany(mappedBy = "subordinate")
			private List<EmployeeRelationHistory> managerHistory;

	   }



	private void allHiredFirstDay(EntityManager em){
			Employee hugoBoss = new Employee();
			Employee stefano = new Employee();
			Employee raul = new Employee();

			hugoBoss.setEmployeeName("HugoBoss");
			stefano.setEmployeeName("Stefano");
			raul.setEmployeeName("Raul");

			em.persist(hugoBoss);
			em.persist(stefano);
			em.persist(raul);

			hugoBossId =hugoBoss.getId();
			stefanoId =stefano.getId();
			raulId=raul.getId();
		}

		// ... 7 years ago...
	   private void stefanoAndRaulAreAssignedToHugoBoss(EntityManager em){

		   Employee hugoBoss = em.find(Employee.class, hugoBossId);
		   Employee stefano = em.find(Employee.class, stefanoId);
		   Employee raul = em.find(Employee.class, raulId);

		   EmployeeRelationHistory managementRecord1 = new EmployeeRelationHistory();
		   managementRecord1.setManager(hugoBoss);
		   managementRecord1.setSubordinate(stefano);
		   managementRecord1.setFrom(ZonedDateTime.now().minusYears(7));

		   EmployeeRelationHistory managementRecord2 = new EmployeeRelationHistory();
		   managementRecord2.setManager(hugoBoss);
		   managementRecord2.setSubordinate(raul);
		   managementRecord2.setFrom(ZonedDateTime.now().minusYears(7));

		   em.persist(managementRecord1);
		   em.persist(managementRecord2);
	   }


	   private void hiredNewManager(EntityManager em) {
		   Employee besos = new Employee();
		   besos.setEmployeeName("Besos");
		   em.persist(besos);
		   besosId=besos.getId();
	   }

		// ... 3 years ago...
		private void stefanoIsAssignedToNewManager(EntityManager em) {


			Employee stefano = em.find(Employee.class, stefanoId);
			Employee besos = em.find(Employee.class, besosId);

			//previous manager relation is closed
			stefano.getManagerHistory().get(0).setUntil(ZonedDateTime.now().minusYears(3));

			EmployeeRelationHistory managementRecord2 = new EmployeeRelationHistory();
			managementRecord2.setManager(besos);
			managementRecord2.setSubordinate(stefano);
			managementRecord2.setFrom(ZonedDateTime.now().minusYears(3));

			em.persist(managementRecord2);

	   }

		// ... today
		private void newHiredAssignedToHugoBoss(EntityManager em) {
			Employee hugoBoss = em.find(Employee.class, hugoBossId);

			Employee subordinate1 = new Employee();
			subordinate1.setEmployeeName("subordinate1");

			Employee subordinate2 = new Employee();
			subordinate2.setEmployeeName("subordinate2");

			Employee subordinate3 = new Employee();
			subordinate3.setEmployeeName("subordinate3");

			em.persist(subordinate1);
			em.persist(subordinate2);
			em.persist(subordinate3);

			EmployeeRelationHistory managementRecord1 = new EmployeeRelationHistory();
			managementRecord1.setManager(hugoBoss);
			managementRecord1.setSubordinate(subordinate1);
			managementRecord1.setFrom(ZonedDateTime.now());

			EmployeeRelationHistory managementRecord2 = new EmployeeRelationHistory();
			managementRecord2.setManager(hugoBoss);
			managementRecord2.setSubordinate(subordinate2);
			managementRecord2.setFrom(ZonedDateTime.now());

			EmployeeRelationHistory managementRecord3 = new EmployeeRelationHistory();
			managementRecord3.setManager(hugoBoss);
			managementRecord3.setSubordinate(subordinate3);
			managementRecord3.setFrom(ZonedDateTime.now());

			em.persist(managementRecord1);
			em.persist(managementRecord2);
			em.persist(managementRecord3);
		}


		private void queryHistories(EntityManager em) {



	   	}



		private static Long hugoBossId =null;
		private static Long stefanoId =null;
		private static Long raulId =null;
		private static Long besosId =null;

}


