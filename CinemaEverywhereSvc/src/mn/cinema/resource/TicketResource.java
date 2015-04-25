package mn.cinema.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import mn.cinema.bl.TicketBL;
import mn.cinema.model.Ticket;

@Path("/ticket")
public class TicketResource {

	TicketBL ticketBL = new TicketBL();
	List<Ticket> ticketList;
	// Allows to have certain contextual object 
	//injected into this class
	@Context
	UriInfo uriInfo;
	
	//Allows us to use the information part of any incoming request
	@Context
	Request request;
		

	//1 success , -1 error
	@POST
	@Path("book")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public int bookTicket(MultivaluedMap<String,String> params)		
	{
		int viewId = Integer.parseInt(params.getFirst("viewId"));	
		String firstName = params.getFirst("firstName");
		String lastName = params.getFirst("lastName");
							
		return ticketBL.bookTicket(viewId,firstName,lastName);
	}		
	
	//1 success , -1 error
	@POST
	@Path("confirm")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public int confirmTicket(MultivaluedMap<String,String> params)		
	{
		int ticketId = Integer.parseInt(params.getFirst("ticketId"));	
		int ticketStatus = Integer.parseInt(params.getFirst("ticketStatus"));		
							
		return ticketBL.confirmTicket(ticketId,ticketStatus);
	}		
	
	//to be re - thought
	//@POST
	//@Path("retrieve")
	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	//@Produces(MediaType.APPLICATION_JSON)
	//public List<Ticket> retrieveTickets(String mvId)		
	//{
	//	int movieId = Integer.parseInt(mvId);	
		
	//	ticketList = ticketBL.getTickets(mvId);
		
	//	return ticketList;
	//}
}
