package mn.cinema.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import mn.cinema.bl.CinemaBL;
import mn.cinema.model.Cinema;
import mn.cinema.modelWrap.CinemaW;
import mn.cinema.modelWrap.CinemaWL;

@Path("/cinema")
public class CinemaResource {	

	CinemaBL cinemaBL = new CinemaBL();
	Cinema cnm;
	// Allows to have certain contextual object 
	//injected into this class
	@Context
	UriInfo uriInfo;
	
	//Allows us to use the information part of any incoming request
	@Context
	Request request;
		
	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getNearCinemas(){
		System.out.println("test");
		return "getNearCinemas!";
	}
	
	/**Return ids and info on cinemas 
	 * around a given x,y area 
	 */
	 
	@GET
	@Path("nearcinemas")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.TEXT_PLAIN)
    public CinemaWL getNearCinemas(@QueryParam("x") double x,
    								   @QueryParam("y") double y){
									
		return CinemaW.wrapList(cinemaBL.getNearCinemas(x, y));
	}


    
}
