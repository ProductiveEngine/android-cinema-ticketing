package mn.cinema.modelce;

import mn.cinema.modelce.Movie;
import mn.cinema.modelce.Room;
import mn.cinema.modelce.Viewmovie;

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

}
