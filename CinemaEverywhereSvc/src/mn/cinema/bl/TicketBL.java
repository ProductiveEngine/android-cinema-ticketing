package mn.cinema.bl;

import javax.persistence.EntityManager;

import mn.cinema.model.Ticket;
import mn.cinema.model.TicketStatus;
import mn.cinema.resource.EntityManagerEM;

public class TicketBL {	
	
	EntityManager em = null;
	
	public int bookTicket(int viewId,String firstName,String lastName){
		
		EntityManagerEM.getInstance();
		em = EntityManagerEM.getEm();
		
		int result = 1;
		Ticket ticket = new Ticket();
		ticket.setFirstName(firstName);
		ticket.setLastName(lastName);
		ticket.setStatus(TicketStatus.BOOKED.ordinal());			   
	    em.getTransaction().begin();
	    em.persist(ticket);
	    em.getTransaction().commit();
		    
		return result;
	}
	
	public int confirmTicket(int ticketId, int ticketStatus){
		 Ticket myTicket = em.find(Ticket.class,ticketId);            
		 myTicket.setStatus(ticketStatus);    
        em.persist(myTicket);
         
        return 1;
	}
	
	
}
