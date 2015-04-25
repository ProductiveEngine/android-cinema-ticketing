package mn.cinema.bl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import mn.cinema.model.MovieInCinema;
import mn.cinema.resource.EntityManagerEM;

public class ViewMovieBL {

	EntityManager em = null;
	
	public List<MovieInCinema> getMovieView(int cinemaId, int movieId){
		
		List<MovieInCinema> viewList = null;
		
		EntityManagerEM.getInstance();
		em = EntityManagerEM.getEm();
		
		Query q = em.createNamedQuery("MOVIE.findViewMovieInACinema");
	    q.setParameter("selectedCinema", cinemaId);
	    q.setParameter("selectedMovie", movieId);
	    
	    viewList = q.getResultList();
	   	    
		return viewList;
	}
}
