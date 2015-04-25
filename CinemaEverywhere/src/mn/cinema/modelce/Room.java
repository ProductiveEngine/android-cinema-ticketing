package mn.cinema.modelce;

import java.util.List;


import mn.cinema.modelce.Cinema;
import mn.cinema.modelce.Viewmovie;

public class Room {
	private int roomId;


	private int totalSeats;

	private Cinema cinema;

	private List<Viewmovie> viewmovies;

	public Room() {
	}

	public int getRoomId() {
		return this.roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getTotalSeats() {
		return this.totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public Cinema getCinema() {
		return this.cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public List<Viewmovie> getViewmovies() {
		return this.viewmovies;
	}

	public void setViewmovies(List<Viewmovie> viewmovies) {
		this.viewmovies = viewmovies;
	}

	public Viewmovie addViewmovy(Viewmovie viewmovy) {
		getViewmovies().add(viewmovy);
		viewmovy.setRoom(this);

		return viewmovy;
	}

	public Viewmovie removeViewmovy(Viewmovie viewmovy) {
		getViewmovies().remove(viewmovy);
		viewmovy.setRoom(null);

		return viewmovy;
	}
}
