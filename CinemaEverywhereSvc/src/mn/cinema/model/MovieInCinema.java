package mn.cinema.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import mn.cinema.modelWrap.MovieInCinemaW;

public class MovieInCinema {
	
	private Movie movie = null;
	private Viewmovie viewMovie = null;
	private Room  room= null;
	
	public MovieInCinema() {
		super();
	}
	
	
	public MovieInCinema(Movie movie, Viewmovie viewMovie, Room room) {
		super();
		this.movie = movie;
		this.viewMovie = viewMovie;
		this.room = room;
	}

	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public Viewmovie getViewMovie() {
		return viewMovie;
	}
	public void setViewMovie(Viewmovie viewMovie) {
		this.viewMovie = viewMovie;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	
	public static MovieInCinema unWrap(MovieInCinemaW micW){
		
		Movie movie = new Movie();						
		movie.setMovieId(micW.getMovieId());
		movie.setInfo(micW.getInfo());
		movie.setMovieName(micW.getMovieName());
		
		Room room = new Room();
		room.setRoomId(micW.getRoomId());
		room.setTotalSeats(micW.getTotalSeats());
		
		Viewmovie viewMovie = new Viewmovie();
		TimestampAdapter adapter = new TimestampAdapter();
		DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss zzz yyyy");
		
		Viewmovie viewmovie = new Viewmovie();
		viewmovie.setViewmovieId(micW.getViewmovieId());		
		try {
			viewmovie.setStartTime(adapter.unmarshal(df.parse(micW.getStartTime())));
			viewmovie.setEndTime(adapter.unmarshal(df.parse(micW.getEndTime())));
		} catch (ParseException e) {			
			e.printStackTrace();
		}
		viewmovie.setTicketPrice(micW.getTicketPrice());
		
		return new MovieInCinema(movie,viewMovie,room);
	}
	
}
