package mn.cinema.modelWrap;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({ViewmovieW.class})
public class ViewMovieWL {
	
	private List<ViewmovieW> ViewMovieLstW = new ArrayList<>();
	
	public ViewMovieWL(List<ViewmovieW> ViewMovieLstW){
		this.ViewMovieLstW = ViewMovieLstW;
	}
	public ViewMovieWL(){
		
	}
	public List<ViewmovieW> getViewMovieLstW() {
		return ViewMovieLstW;
	}
	public void setViewMovieLstW(List<ViewmovieW> ViewMovieLstW) {
		this.ViewMovieLstW = ViewMovieLstW;
	}
	
}


