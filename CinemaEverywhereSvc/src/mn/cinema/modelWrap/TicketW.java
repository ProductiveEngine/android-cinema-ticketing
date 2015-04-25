package mn.cinema.modelWrap;

import java.util.ArrayList;
import java.util.List;

import mn.cinema.model.Cinema;
import mn.cinema.model.Ticket;

public class TicketW {

	private int ticketId,status;
	private String firstName,lastName;	

	public TicketW(){
		
	}
	
	public TicketW(Ticket ticket){
		this.ticketId = ticket.getTicketId();
		this.firstName = ticket.getFirstName();
		this.lastName = ticket.getLastName();
		this.status = ticket.getStatus();
	}
	
	public static Ticket unWrap(TicketW tW){
		
		Ticket ticket = new Ticket();
		ticket.setTicketId(tW.ticketId);
		ticket.setFirstName(tW.firstName);
		ticket.setLastName(tW.lastName);
		ticket.setStatus(tW.status);
		
		return ticket;
	}
	public static TicketWL wrapList(List<Ticket> ticketLst){
		
		List<TicketW> ticketLstW = new ArrayList<>();
		for(Ticket ticket : ticketLst){
			ticketLstW.add(new TicketW(ticket));
	    }
		return new TicketWL(ticketLstW);
	}
		
}
