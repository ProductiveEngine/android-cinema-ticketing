package mn.cinema.modelWrap;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

import mn.cinema.model.Cinema;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="cinema")
public class CinemaW {
	
	private int cinemaId;	
	private String cinemaName,info,phone;		
	private double x,y;	
	
	public CinemaW(){
		
	}
	public CinemaW(Cinema cinema){
	  this.cinemaId = cinema.getCinemaId();	
	  this.cinemaName = cinema.getCinemaName();
	  this.info = cinema.getInfo();
	  this.phone = cinema.getPhone();
	  this.x = cinema.getX();
	  this.y = cinema.getY();
	}
	public static Cinema unWrap(CinemaW cW){
		Cinema cinema = new Cinema();
		cinema.setCinemaId(cW.cinemaId);
		cinema.setCinemaName(cW.cinemaName);
		cinema.setInfo(cW.info);
		cinema.setPhone(cW.phone);
		cinema.setX(cW.x);
		cinema.setY(cW.y);
		
		return cinema;
	}
	
	public static CinemaWL wrapList(List<Cinema> cinemaLst){
		
		List<CinemaW> cinemaLstW = new ArrayList<>();
		for(Cinema cinema : cinemaLst){
	    	cinemaLstW.add(new CinemaW(cinema));
	    }
		return new CinemaWL(cinemaLstW);
	}
	
}
