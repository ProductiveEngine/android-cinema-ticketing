package mn.cinema.resource;

import java.util.List;

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
import javax.ws.rs.Consumes;

import mn.cinema.bl.MovieBL;
import mn.cinema.modelWrap.CinemaWL;
import mn.cinema.modelWrap.MovieW;
import mn.cinema.modelWrap.MovieWL;


@Path("/movie")
public class MovieResource {

	MovieBL movieBL = new MovieBL();
	
	// Allows to have certain contextual object 
	//injected into this class
	@Context
	UriInfo uriInfo;
	
	//Allows us to use the information part of any incoming request
	@Context
	Request request;
		
	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMovieList(){
		System.out.println("test");
		return "getMovieList!";
	}

	//Returns movie list in the selected cinema	
	@GET
	@Path("movielist")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)	
	public MovieWL getMoviesInCinema(@QueryParam("cinemaId")int cinemaId)		
	{							
		return MovieW.wrapList(movieBL.getAllMovies(cinemaId));
	}
	
}

