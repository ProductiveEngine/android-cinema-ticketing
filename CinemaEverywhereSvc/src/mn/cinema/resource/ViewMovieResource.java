package mn.cinema.resource;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

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

import mn.cinema.bl.ViewMovieBL;
import mn.cinema.model.MovieInCinema;
import mn.cinema.modelWrap.CinemaW;
import mn.cinema.modelWrap.CinemaWL;
import mn.cinema.modelWrap.MovieInCinemaW;
import mn.cinema.modelWrap.MovieInCinemaWL;
import mn.cinema.modelWrap.ViewmovieW;

@Path("/viewmovie")
public class ViewMovieResource {

	ViewMovieBL viewMoviewBL = new ViewMovieBL();
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
		return "viewmovie test!";
	}
	//Returns a list of details and views for the selected movie
	@GET
	@Path("movieViewDetails")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public MovieInCinemaWL movieViewDetails(@QueryParam("cinemaId") int cinemaId,
			   								@QueryParam("movieId") int movieId)		
	{							
		return MovieInCinemaW.wrapList(viewMoviewBL.getMovieView(cinemaId,movieId));
		//return MovieInCinemaW.wrapList(viewMoviewBL.getMovieView(cinemaId,movieId)).getCinemaLstW().get(0);
	}
	
	
}
