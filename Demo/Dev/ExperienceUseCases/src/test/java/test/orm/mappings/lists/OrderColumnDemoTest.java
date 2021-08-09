package test.orm.mappings.lists;

import static org.junit.Assert.assertEquals;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.junit.Test;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import test.orm.core.AbstractJPAProgrammaticBootstrapTest;

@Slf4j
public class OrderColumnDemoTest extends AbstractJPAProgrammaticBootstrapTest{

	
	   private static Long roadMapId =null;
	   
	   @Table(name = "training")
	   @Entity
	   @Data
	   static class Training implements Serializable {
		   
		   	@Id
		    @GeneratedValue(strategy = GenerationType.AUTO)
		    @Column(name = "id")
		    private Long id;

		    @Column(name = "title")
		    private String title;

		    @Column(name = "description")
		    private String description;

		    @Lob
		    @Column(name = "content")
		    private byte[] content;
	   }
	   
	   @Table(name = "learning_roadmap")
	   @Entity
	   @Data
	   static class LearningRoadMap implements Serializable {
		   
		   	@Id
		    @GeneratedValue(strategy = GenerationType.AUTO)
		    @Column(name = "id")
		    private Long id;

		    @Column(name = "title")
		    private String title;

		    @OrderColumn(name = "step")
		    @OneToMany
		    @JoinTable(name = "learning_roadmap_trainings",
	        	joinColumns = @JoinColumn(name = "roadmap_id"),
	        	inverseJoinColumns = @JoinColumn(name = "training_id")
		    )
		    private List<Training> trainings= new ArrayList<>();
	   }
	   
	 
	   
	   
	   @Test
	   public void testCrud() {

		  
				   
		  doInJPATransaction(em -> {
			   Training t1 = new Training();
			   t1.setTitle("t1.title");
			  
			   Training t2 = new Training();
			   t2.setTitle("t2.title");
			   
			   Training t3 = new Training();
			   t3.setTitle("t3.title");
			   
			   LearningRoadMap roadmap = new LearningRoadMap();
			   roadmap.getTrainings().add(t1);
			   roadmap.getTrainings().add(t2);
			   roadmap.getTrainings().add(t3);
			   
			   
			   em.persist(t1);
			   em.persist(t2);
			   em.persist(t3);
			   em.persist(roadmap);		
			   
			   roadMapId= roadmap.getId();
					   
		   });
		   
		   doInJPATransaction(em -> {
			   
			   LearningRoadMap roadmap = em.find(LearningRoadMap.class,roadMapId);
			   assertEquals(3, roadmap.getTrainings().size());
			   roadmap.getTrainings().forEach(training ->{ 
				   log.info(training.toString());
			   });
		   });
		   
		   
	   }
	   
	   
	   @Override
	    protected Class<?>[] entities() {
	        return new Class[] {    //...insert here your entities
				Training.class,
				LearningRoadMap.class
	        };
	    }
	   
}
