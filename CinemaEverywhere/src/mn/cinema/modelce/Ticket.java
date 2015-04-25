package mn.cinema.modelce;

import mn.cinema.modelce.Viewmovie;

public class Ticket {
	private int ticketId;
	
	private String firstName;

	private String lastName;	

	private int status;

	private Viewmovie viewmovie;

	public Ticket() {
	}

	public int getTicketId() {
		return this.ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Viewmovie getViewmovie() {
		return this.viewmovie;
	}

	public void setViewmovie(Viewmovie viewmovie) {
		this.viewmovie = viewmovie;
	}

}
