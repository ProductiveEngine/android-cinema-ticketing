package mn.cinema.restclient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import mn.cinema.common.Action;
import mn.cinema.modelce.Cinema;
import mn.cinema.modelce.Movie;
import mn.cinema.modelce.MovieInCinema;
import mn.cinema.modelce.Room;
import mn.cinema.modelce.Ticket;
import mn.cinema.modelce.Viewmovie;
import mn.cinema.view.Booking;
import mn.cinema.view.MainMenu;
import mn.cinema.view.MovieDetails;
import mn.cinema.view.R;
import mn.cinema.view.ViewMovies;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

public class RestSvcResponse {
	
	private static final String TAG = "RestSvcResponse";
	//variable declaration
	String response;
	Action action;
	Context context;
	MainMenu mainMenu;
	ViewMovies viewMovies;
	MovieDetails movieDetails;
	Booking booking;
	TextView location;
	int book =-1;
	Cinema cinema;
	Movie movie;
	Room room;
	MovieInCinema mic;
	Ticket ticket;
	Viewmovie vm;
	List<Cinema> cinemaList;
	List<Movie> movieLst;
	List<MovieInCinema> movieInCinema;
	
	JSONObject jso;
	JSONArray jArray;

	
	
	public RestSvcResponse(String response, Action action, Context context){
		this.response = response;
		this.action = action;
		this.context = context;
	
		handleResponse();
	}



	private void handleResponse() {
		
		switch(action){
		 case TEST://get cinemas around
			  testSvc();
			  break;
		  case GET_CINEMAS://get cinemas around
			  getCinemasAround();
			  break;
		  case GET_MOVIES://get movies
			  getListOfMovies();
			  break;
		  case GET_MOVIE_DETAILS://get movie details
			  getMovieDetails();
			  break;
		  case BOOK://book
			  bookTickets();
			  break;
		  case BUY://buy - currently not implemented
			  buyTickets();
			  break;
		  case TICKET_STATUS://get booked ticket status
			  getTicketStatus();
			  break;
		  }
		
	}
	
	private void getTicketStatus() {//not implemented yet
		// TODO Auto-generated method stub
		
	}

	private void testSvc(){//to test the service
		mainMenu = (MainMenu)context;
		location =(TextView)mainMenu.findViewById(R.id.textViewselectedMovieNameFinal);
		location.setText(" "+response);
	}

	private void buyTickets() {//not implemented due to lack of time
		// TODO Auto-generated method stub
		System.out.println("buyTickets");
		
	}

	private void bookTickets() {//get the confirmation that tickets were booked
		System.out.println("bookTickets" + response );
		try {
			 if(response!=null){ 
				 jso = new JSONObject(response);
				 ticket = new Ticket();
				 ticket.setTicketId(jso.getInt("ticketId"));
				 ticket.setStatus(jso.getInt("status"));
				 ticket.setFirstName(jso.getString("firstName"));
				 ticket.setLastName(jso.getString("lastName"));				 
			 }
        } 
		catch (Exception e) {
       	 Log.e(TAG, e.getLocalizedMessage(), e);
        }
		booking = (Booking)context;
		booking.bookingResult(ticket);	
	}

	@SuppressLint("SimpleDateFormat") private void getMovieDetails() {//retrieve movie details from svc
		System.out.println("getMovieDetails");
		try {
			 if(response!=null){
       
				 movieInCinema = new ArrayList<MovieInCinema>();
				 
			 if(response.contains("[")){//when [] are present, they indicate an array of JSon objects. 
				 						//A better way to distinguish this should be found
			 
				 jArray = new JSONArray((new JSONObject(response)).getString("movieInCinemaLstW"));
			 			 
				 for(int i=0;i<jArray.length();i++){
					 jso = jArray.getJSONObject(i);
					
					 room = new Room();
					 movie = new Movie();
					 vm = new Viewmovie();
										 
					 movie.setMovieId(jso.getInt("movieId"));
					 movie.setInfo(jso.getString("movieName"));
					 movie.setMovieName(jso.getString("info"));
					 
					 room.setRoomId(jso.getInt("roomId"));
					 room.setTotalSeats(jso.getInt("totalSeats"));
					 
					 try{
						 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
						 java.util.Date parsedDateStart = dateFormat.parse(jso.getString("startTime"));
						 java.util.Date parsedDateEnd = dateFormat.parse(jso.getString("endTime"));
						 java.sql.Timestamp timestampStart = new java.sql.Timestamp(parsedDateStart.getTime());
						 java.sql.Timestamp timestampEnd = new java.sql.Timestamp(parsedDateEnd.getTime());
						 
						 
						 vm.setStartTime(timestampStart);
						 vm.setEndTime(timestampEnd);
						 }
						 catch(Exception e){
							 Log.e(TAG, e.getLocalizedMessage(), e);
						 }

					 vm.setTicketPrice(jso.getDouble("ticketPrice"));
					 vm.setViewmovieId(jso.getInt("viewmovieId"));
								 
					 mic = new MovieInCinema(movie, vm, room );					 
					 movieInCinema.add(mic);		
				 }		 	
			 }
			 else{//if not an array but a single object				 
				 jso = new JSONObject(response);
				
				 room = new Room();
				 movie = new Movie();
				 vm = new Viewmovie();
				 
				 movie.setMovieId(jso.getJSONObject("movieInCinemaLstW").getInt("movieId"));
				 movie.setInfo(jso.getJSONObject("movieInCinemaLstW").getString("movieName"));
				 movie.setMovieName(jso.getJSONObject("movieInCinemaLstW").getString("info"));
				
				 room.setRoomId(jso.getJSONObject("movieInCinemaLstW").getInt("roomId"));
				 room.setTotalSeats(jso.getJSONObject("movieInCinemaLstW").getInt("totalSeats"));
				 
				 try{
					 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
					 java.util.Date parsedDateStart = dateFormat.parse(jso.getJSONObject("movieInCinemaLstW").getString("startTime"));
					 java.util.Date parsedDateEnd = dateFormat.parse(jso.getJSONObject("movieInCinemaLstW").getString("endTime"));
					 java.sql.Timestamp timestampStart = new java.sql.Timestamp(parsedDateStart.getTime());
					 java.sql.Timestamp timestampEnd = new java.sql.Timestamp(parsedDateEnd.getTime());
				 
					 vm.setStartTime(timestampStart);
					 vm.setEndTime(timestampEnd);
				 }
				 catch(Exception e){
					 Log.e(TAG, e.getLocalizedMessage(), e);
				 }				 
				 vm.setTicketPrice(jso.getJSONObject("movieInCinemaLstW").getDouble("ticketPrice"));
				 vm.setViewmovieId(jso.getJSONObject("movieInCinemaLstW").getInt("viewmovieId"));
							 
				 mic = new MovieInCinema(movie, vm, room );				 
				 movieInCinema.add(mic);		 
				 } 		 
			 }
	         } catch (Exception e) {
	        	 Log.e(TAG, e.getLocalizedMessage(), e);    	
	         }
		
		movieDetails = (MovieDetails)context;
		movieDetails.fillMovieDetailsListView(movieInCinema);	 
	}



	private void getListOfMovies() {

		System.out.println("getListOfMovies"+response);
		 movieLst = new ArrayList<Movie>();
		 try {
			 if(response!=null){
       
			 if(response.contains("[")){//need to find a better way to handle the difference between arrays and 
			 
				 jArray = new JSONArray((new JSONObject(response)).getString("movieLstW"));
		 			 
				 for(int i=0;i<jArray.length();i++){
					 jso = jArray.getJSONObject(i);
					 movie = new Movie();
			 
					 movie.setMovieId(jso.getInt("movieId"));
					 movie.setInfo(jso.getString("info"));
					 movie.setMovieName(jso.getString("movieName"));
								 
					 movieLst.add(movie);		
				 }		 	
			 }
			 else{
				 
				 jso = new JSONObject(response);

				 movie = new Movie();
				 movie.setMovieId(jso.getJSONObject("movieLstW").getInt("movieId"));
				 movie.setInfo(jso.getJSONObject("movieLstW").getString("info"));
				 movie.setMovieName(jso.getJSONObject("movieLstW").getString("movieName"));
							 
				 movieLst.add(movie);
			 	} 
			 }
	         } catch (Exception e) {
	        	 Log.e(TAG, e.getLocalizedMessage(), e);   	
	         }
			viewMovies = (ViewMovies)context;
			viewMovies.fillListView(movieLst);
	}


	public List<Cinema> getCinemasAround() {
		 cinemaList = new ArrayList<Cinema>();
		 try {
        
			 if(response.contains("[")){
			jArray = new JSONArray((new JSONObject(response)).getString("cinemaLstW"));
			 		 
			 for(int i=0;i<jArray.length();i++){
			 
				 jso = jArray.getJSONObject(i);
			
				 cinema = new Cinema();
			 
				 cinema.setCinemaId(jso.getInt("cinemaId"));
				 cinema.setCinemaName(jso.getString("cinemaName"));
				 cinema.setInfo(jso.getString("info"));
				 cinema.setPhone(jso.getString("phone"));
				 cinema.setX(jso.getDouble("x"));
				 cinema.setY(jso.getDouble("y"));
				 
				 cinemaList.add(cinema);	 
			 }
			}
			 else{
				 jso = new JSONObject(response);
				 cinema = new Cinema();
				 cinema.setCinemaId(jso.getJSONObject("cinemaLstW").getInt("cinemaId"));
				 cinema.setCinemaName(jso.getJSONObject("cinemaLstW").getString("cinemaName"));
				 cinema.setInfo(jso.getJSONObject("cinemaLstW").getString("info"));
				 cinema.setPhone(jso.getJSONObject("cinemaLstW").getString("phone"));
				 cinema.setX(jso.getJSONObject("cinemaLstW").getDouble("x"));
				 cinema.setY(jso.getJSONObject("cinemaLstW").getDouble("y"));
				 
				 cinemaList.add(cinema);
			 }  
     } catch (Exception e) {
         Log.e(TAG, e.getLocalizedMessage(), e);
         handleExInResponse(e.getMessage());
     }	
		  mainMenu = (MainMenu)context;
		  mainMenu.populateMap(cinemaList);
		  
		 return cinemaList;
	}
	
	
	private void handleExInResponse(String ex){//error codes and responses taken from another project -- need to be checked and verified
	  	  if (ex.contains("401") == true)
          {
	  		System.out.println("Could not get response from feed! Unauthorized Access! please check credentials!");
          }
          else if (ex.contains("403") == true)
          {
        	  System.out.println("Could not get response from feed! Unauthorized account! Please check credentials!");
          }

          else if (ex.contains("420") == true)
          {
        	  System.out.println("Rate Limited!!! Try again later or change credentials!");
          }

          else if (ex.contains("503") == true)
          {
        	  System.out.println("Oops! Service Unavailable! They'll fix it soon! Please try again later!");
          }
          else
          {
        	  System.out.println("Could not get response from feed!");
          }
		
	}

}
