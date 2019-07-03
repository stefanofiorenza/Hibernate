package corso.hibernate.demo.primo.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.knits.jpa.basic.demo.model.CD;

public class TestCrudCD {

	/**
	 * @param args
	 */
	
	private static 	SessionFactory sessionFactory;
	
	public static void main(String[] args) {
			
		getFactory().getCurrentSession();
		
		//1) insert CD
		CD nuovo = new CD();
		nuovo.setArtist("Bob Dylan");
		nuovo.setTitle("Empire Burlesque");
		nuovo.setCompany("RCA");
		nuovo.setPrice(10.90);
		nuovo.setYear(1966);
		nuovo.setCountry("USA");
		nuovo.setQuantity(12);
		
		insert(nuovo);
		
		
		
		//2) modifica
		System.out.println("IN MEMORIA");
		System.out.println("prima modifica:"+nuovo+"\n");
		nuovo.setPrice(13.20);
		System.out.println("dopo modifica:"+nuovo+"\n");
		
		System.out.println("Rendo persistente la modifica");
		update(nuovo);
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		//3)Recupero dal Db di un altro oggetto CD
		
		CD trovato =findById(2);
		
		System.out.println(trovato);

		

	}
	
	
	public static void insert (CD daInserire){
		
		Session sessione =getFactory().getCurrentSession();
		sessione.beginTransaction();
		System.out.println("prima inserimento ID: "+daInserire.getPrimaryKey());
		
		sessione.save(daInserire);
		System.out.println("inserito con ID: "+daInserire.getPrimaryKey());
		
		sessione.getTransaction().commit();
	    
	
	}

	public static void update (CD daModificare){
		
		Session sessione =getFactory().getCurrentSession();
		sessione.beginTransaction();
		System.out.println("ID oggetto in memoria: "+daModificare.getPrimaryKey());
				
		sessione.update(daModificare);
		sessione.saveOrUpdate(daModificare);
		System.out.println("modifica oggetto con ID: "+daModificare.getPrimaryKey());
		sessione.getTransaction().commit();
		
	}
	
	
	public static void delete (CD daCancellare){
		
		Session sessione =getFactory().getCurrentSession();
		sessione.beginTransaction();
		sessione.delete(daCancellare);
		System.out.println("ID oggetto cancellato: "+daCancellare.getPrimaryKey());
		sessione.getTransaction().commit();
		
		
	}
	
	
	public static CD findById (long id){
		
		Session sessione =getFactory().getCurrentSession();
		sessione.beginTransaction();
		
		Object cd =sessione.get("corso.hibernate.demo.primo.model.CD", (java.io.Serializable)id);
		return (CD)cd;
	}
	
	
	public static SessionFactory getFactory(){
		
		if (sessionFactory==null){
			sessionFactory= new Configuration().configure().buildSessionFactory();
		}
		
		return sessionFactory;
	}


}
