package mn.cinema.modelWrap;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import mn.cinema.model.Movie;
import mn.cinema.model.MovieInCinema;
import mn.cinema.model.Room;
import mn.cinema.model.TimestampAdapter;
import mn.cinema.model.Viewmovie;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="movieInCinema")
public class MovieInCinemaW {

	private int movieId;
	private String info,movieName;	
	private int roomId,totalSeats;
	private int viewmovieId;
	private String startTime,endTime;
	private double ticketPrice;	
	
	public MovieInCinemaW() {
		
	}
	
	public MovieInCinemaW(MovieInCinema mic) {
		this.movieId = mic.getMovie().getMovieId();
		this.info = mic.getMovie().getInfo();
		this.movieName = mic.getMovie().getMovieName();
		
		this.roomId = mic.getRoom().getRoomId();
		this.totalSeats = mic.getRoom().getTotalSeats();
		
		this.viewmovieId = mic.getViewMovie().getViewmovieId();
		this.startTime = mic.getViewMovie().getStartTime().toString();
		this.endTime = mic.getViewMovie().getEndTime().toString();
		this.ticketPrice = mic.getViewMovie().getTicketPrice();
		
	}	
	
	public static MovieInCinemaWL wrapList(List<MovieInCinema> movieInCinemaLst){
		
		List<MovieInCinemaW> movieInCinemaLstW = new ArrayList<>();
		for(MovieInCinema movieInCinema : movieInCinemaLst){
			movieInCinemaLstW.add(new MovieInCinemaW(movieInCinema));
	    }
		return new MovieInCinemaWL(movieInCinemaLstW);
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getViewmovieId() {
		return viewmovieId;
	}

	public void setViewmovieId(int viewmovieId) {
		this.viewmovieId = viewmovieId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	
}
