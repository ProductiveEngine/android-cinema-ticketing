package mn.cinema.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the VIEWMOVIE database table.
 * 
 */
@Entity
public class Viewmovie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="VIEWMOVIE_ID")
	private int viewmovieId;

	@XmlJavaTypeAdapter( TimestampAdapter.class)
	@Column(name="END_TIME")
	private Timestamp endTime;

	@XmlJavaTypeAdapter( TimestampAdapter.class)
	@Column(name="START_TIME")
	private Timestamp startTime;

	@Column(name="TICKET_PRICE")
	private double ticketPrice;
	
	//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy="viewmovie")
	private List<Ticket> tickets;

	//bi-directional many-to-one association to Movie
	@ManyToOne
	@JoinColumn(name="VIEWMOVIE_MOVIE_ID")
	private Movie movie;

	//bi-directional many-to-one association to Room
	@ManyToOne
	@JoinColumn(name="VIEWMOVIE_ROOM_ID")
	private Room room;

	public Viewmovie() {
	}

	public int getViewmovieId() {
		return this.viewmovieId;
	}

	public void setViewmovieId(int viewmovieId) {
		this.viewmovieId = viewmovieId;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public List<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket addTicket(Ticket ticket) {
		getTickets().add(ticket);
		ticket.setViewmovie(this);

		return ticket;
	}

	public Ticket removeTicket(Ticket ticket) {
		getTickets().remove(ticket);
		ticket.setViewmovie(null);

		return ticket;
	}

	public Movie getMovie() {
		return this.movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	public double getTicketPrice() {
		return this.ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
}