package mn.cinema.modelWrap;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({MovieInCinemaW.class})
public class MovieInCinemaWL {
	
	private List<MovieInCinemaW> movieInCinemaLstW = new ArrayList<>();
	
	public MovieInCinemaWL(){
		
	}
	public MovieInCinemaWL(List<MovieInCinemaW> movieInCinemaLstW){
		this.movieInCinemaLstW = movieInCinemaLstW;
	}
	public List<MovieInCinemaW> getMovieInCinemaLstW() {
		return movieInCinemaLstW;
	}
	public void setMovieInCinemaLstW(List<MovieInCinemaW> movieInCinemaLstW) {
		this.movieInCinemaLstW = movieInCinemaLstW;
	}
	
}