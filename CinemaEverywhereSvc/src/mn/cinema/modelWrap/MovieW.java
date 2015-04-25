package mn.cinema.modelWrap;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import mn.cinema.model.Cinema;
import mn.cinema.model.Movie;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="movie")
public class MovieW {
	
	private int movieId;
	private String info,movieName;		
	
	public MovieW(){
		
	}
	
	public MovieW(Movie movie){
		this.movieId = movie.getMovieId();
		this.info = movie.getInfo();
		this.movieName = movie.getMovieName();
	}

	public static Movie unWrap(MovieW movieW){
		
		Movie movie = new Movie();		
		movie.setMovieId(movieW.movieId);
		movie.setInfo(movieW.info);
		movie.setMovieName(movieW.movieName);
		
		return movie;
	}
	public static MovieWL wrapList(List<Movie> movieLst){
		
		List<MovieW> movieLstW = new ArrayList<>();
		for(Movie movie : movieLst){
			movieLstW.add(new MovieW(movie));
	    }
		return new MovieWL(movieLstW);
	}
	
}
