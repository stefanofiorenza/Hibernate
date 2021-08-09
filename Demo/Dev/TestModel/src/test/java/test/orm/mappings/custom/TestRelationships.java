package test.orm.mappings.custom;

import static test.orm.core.JpaContextUtil.close;
import static test.orm.core.JpaContextUtil.doInJPATransaction;
import static test.orm.core.JpaContextUtil.getEntityManagerFactory;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import test.orm.domain.Member;


@Slf4j
public class TestRelationships {

	

		@Before
		public void init() {
			getEntityManagerFactory("JPA-ORM-TEST-PU"); //see /META-INF/persistence.xml
		}
	 
	   
	   
	   @Test
	   public void testCrud() {
		   doInJPATransaction(em -> {
			  
			   log.info("Created tables");
//			   Employee e = new Employ
//			   Member m1 = new Member();
//			   m1.setOnBoardDate(LocalDateTime.now());
//			   m1.setOnBoardDate(LocalDateTime.now().plusDays(12));
//			   em.persist(m1);
		   });
	   }
	   
	   	@After
		public void end() {
			close();
		}
}
