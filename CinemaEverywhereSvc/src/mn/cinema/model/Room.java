package mn.cinema.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ROOM database table.
 * 
 */
@Entity
public class Room implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ROOM_ID")
	private int roomId;

	@Column(name="TOTAL_SEATS")
	private int totalSeats;

	//bi-directional many-to-one association to Cinema
	@ManyToOne
	@JoinColumn(name="ROOM_CINEMA_ID")
	private Cinema cinema;

	//bi-directional many-to-one association to Viewmovie
	@OneToMany(mappedBy="room")
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