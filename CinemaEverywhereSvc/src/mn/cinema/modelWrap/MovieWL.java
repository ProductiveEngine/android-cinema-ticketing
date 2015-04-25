package mn.cinema.modelWrap;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({MovieW.class})
public class MovieWL {

	private List<MovieW> movieLstW = new ArrayList<>();

	public MovieWL(){
		
	}	
	public MovieWL(List<MovieW> movieLstW){
		this.movieLstW = movieLstW;
	}	
	public List<MovieW> getMovieLstW() {
		return movieLstW;
	}
	public void setMovieLstW(List<MovieW> movieLstW) {
		this.movieLstW = movieLstW;
	}	
}
