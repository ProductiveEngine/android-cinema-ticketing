package mn.cinema.bl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import mn.cinema.model.Cinema;
import mn.cinema.model.Movie;
import mn.cinema.modelWrap.CinemaW;
import mn.cinema.modelWrap.MovieW;
import mn.cinema.resource.EntityManagerEM;

public class MovieBL {

	EntityManager em = null;
	
	public List<Movie> getAllMovies(int cinemaId){
		
		List<Movie> movieList = null;		
		EntityManagerEM.getInstance();
		em = EntityManagerEM.getEm();
		
		Query q = em.createNamedQuery("MOVIE.findAllMoviesInACinema");
	    q.setParameter("selectedCinema", cinemaId);
	    
	    movieList = q.getResultList();
	   
	    //Take distinct Movies 
	    int sameId = 0 ;
	    for(Movie movie : movieList){			
	    	if( sameId == movie.getMovieId())
	    	{
	    		movieList.remove(movie);
	    	}
	    	else{
	    		sameId = movie.getMovieId();
	    	}
	    }
	    
		return movieList;
	}
}
