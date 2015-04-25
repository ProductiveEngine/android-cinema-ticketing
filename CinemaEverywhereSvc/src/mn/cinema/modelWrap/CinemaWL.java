package mn.cinema.modelWrap;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({CinemaW.class})
public class CinemaWL {
	
	private List<CinemaW> cinemaLstW = new ArrayList<>();
	
	public CinemaWL(List<CinemaW> cinemaLstW){
		this.cinemaLstW = cinemaLstW;
	}
	public CinemaWL(){
		
	}
	public List<CinemaW> getCinemaLstW() {
		return cinemaLstW;
	}
	public void setCinemaLstW(List<CinemaW> cinemaLstW) {
		this.cinemaLstW = cinemaLstW;
	}
	
}
