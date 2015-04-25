package mn.cinema.bl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import mn.cinema.model.Cinema;
import mn.cinema.modelWrap.CinemaW;
import mn.cinema.resource.EntityManagerEM;

public class CinemaBL {

	EntityManager em = null;
	
	
	public List<Cinema> getNearCinemas(double x,double y){
		
		List<Cinema> cinemaLst = null;				
		
		EntityManagerEM.getInstance();
		em = EntityManagerEM.getEm();
		
		Query q = em.createNamedQuery("CINEMA.findNearCinemas");
	    q.setParameter("pointX", x);
	    q.setParameter("pointY", y);
	    q.setParameter("radius", 10 ); // ??????
	    cinemaLst = q.getResultList();
	    	    
		return cinemaLst;
	}
}
