package mn.cinema.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TICKET database table.
 * 
 */
@Entity
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TICKET_ID")
	private int ticketId;

	@Column(name="FIRST_NAME")
	private String firstName;

	@Column(name="LAST_NAME")
	private String lastName;	

	@Column(name="STATUS")
	private int status;

	//bi-directional many-to-one association to Viewmovie
	@ManyToOne
	@JoinColumn(name="TICKET_VIEWMOVIE_ID")
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