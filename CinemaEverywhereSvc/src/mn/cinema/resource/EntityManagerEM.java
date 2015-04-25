package mn.cinema.resource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerEM {

	private static volatile EntityManagerEM instance = null;
	private static final String PERSISTENCE_UNIT_NAME = "CinemaEverywhereSvc";
	private static EntityManagerFactory factory;
	private static EntityManager em = null;
	
	public static EntityManagerEM getInstance(){
		if(instance == null){
			synchronized (EntityManagerEM.class) {
				if(instance == null){
					instance = new EntityManagerEM();
				}
			}
		}
		return instance;
	}
	
	private  EntityManagerEM() {
	    factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	    em = factory.createEntityManager();
	}

	public static EntityManager getEm() {
		return em;
	}

	public static void setEm(EntityManager em) {
		EntityManagerEM.em = em;
	}
		
}
