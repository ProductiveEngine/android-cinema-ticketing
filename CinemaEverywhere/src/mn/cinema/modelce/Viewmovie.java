package mn.cinema.modelce;

import java.sql.Timestamp;
import java.util.List;

import mn.cinema.modelce.Movie;
import mn.cinema.modelce.Room;
import mn.cinema.modelce.Ticket;


public class Viewmovie {
	private int viewmovieId;


	private Timestamp endTime;

	private Timestamp startTime;

	private double ticketPrice;
	

	private List<Ticket> tickets;

	private Movie movie;

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
