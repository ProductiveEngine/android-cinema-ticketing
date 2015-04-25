package mn.cinema.restclient;

import java.util.List;

import com.google.android.gms.maps.model.LatLng;

import mn.cinema.common.Action;
import mn.cinema.common.GlobalValues;
import android.content.Context;
import android.widget.Toast;

public class RestSvcClient {
	
	private static String SERVICE_URL = "http://94.71.203.166:8085/CinemaEverywhereSvc/ce/cinema";//to be changed
	  Action action;
	  Context context;

	  RestSvcTask rst;
	  RestSvcResponse responseHandler;
	  LatLng lng;
	  List<String[]> listOfParams;

	  
	  public RestSvcClient(Action action, Context context){
		  this.action = action;
		  this.context = context;  
	  }
	  
	  RestSvcClient(){  		  
	  }
	  
	  //to be used when parameters need to be set
	  public RestSvcClient(Action action, Context context, List<String[]> listOfParams){
		  this.action = action;
		  this.context = context;
		  this.listOfParams = listOfParams;	  
	  }
	  
	  private void setAction(Action action){// to be reconsidered
		  switch(action){
		  case TEST://test service
			  testSvc();
			  break;
		  case GET_CINEMAS://get list of cinemas
			  getListOfCinemas();
			  break;
		  case GET_MOVIES://get list of movies
			  getListOfMovies();
			 
			  break;
		  case GET_MOVIE_DETAILS://get specific movie details
			  getMovieDetails();
			  
			  break;
		  case BOOK://book tickets
			  bookTickets();
			 
			  break;
		  case BUY://buy tickets
			  buyTickets();
			 
			  break;		  
		  case TICKET_STATUS://retrieve info about tickets
			  
			  break;
		  }  
		 }
		  
	  //testing the service
	    private void testSvc() {	    	
	    	SERVICE_URL = "http://94.71.203.166:8085/CinemaEverywhereSvc/ce/cinema";	
	}
		private void buyTickets() {
	    	 SERVICE_URL = "http://94.71.203.166:8085/CinemaEverywhereSvc/ce/ticket/confirm";	
	}
		private void bookTickets() {
			SERVICE_URL = "http://94.71.203.166:8085/CinemaEverywhereSvc/ce/ticket/book";
			//add params
			 rst.addNameValuePair("viewId",String.valueOf(GlobalValues.getViewId()));
			 rst.addNameValuePair("firstName",String.valueOf(GlobalValues.getName()));
			 rst.addNameValuePair("lastName",String.valueOf(GlobalValues.getSurname()) );		
	}
		private void getMovieDetails() {
			 SERVICE_URL = "http://94.71.203.166:8085/CinemaEverywhereSvc/ce/viewmovie/movieViewDetails";
			//add params
			 rst.addNameValuePair("cinemaId",String.valueOf(GlobalValues.getCinema().getCinemaId()) );
			 rst.addNameValuePair("movieId",String.valueOf(GlobalValues.getMovie().getMovieId()) );
	}
		private void getListOfMovies() {
			 SERVICE_URL = "http://94.71.203.166:8085/CinemaEverywhereSvc/ce/movie/movielist";
			//add params
			 rst.addNameValuePair("cinemaId",String.valueOf(GlobalValues.getCinema().getCinemaId()) );	
	}
		private void getListOfCinemas() {		
			SERVICE_URL = "http://94.71.203.166:8085/CinemaEverywhereSvc/ce/cinema/nearcinemas";
			
			lng = GlobalValues.getUserLocation();
			//add user's current location to rst as parameters
		      rst.addNameValuePair("x", String.valueOf(lng.latitude));
		      rst.addNameValuePair("y", String.valueOf(lng.longitude));  		
	}
		//just to test the web service
		public void retrieveData() {
	        //create a new 
	        rst = new RestSvcTask(RestSvcTask.GET_TASK, context, "GETting data...", this);   
			 
	        setAction(action);
	        String sampleURL = SERVICE_URL;
	        
	        rst.execute(new String[] { sampleURL });     
	    }
	    
		  public void postData(){
			  rst = new RestSvcTask(RestSvcTask.POST_TASK, context, "Posting data...", this);
			  setAction(action);	 	 
		        // the passed String is the URL we will POST to
			  rst.execute(new String[] { SERVICE_URL });
		  }
		   
		  //handles response - if not null, it creates an instance of RestSvcResponse
		  public void handleResponse(String response) {
			  if(response!=null){
			  		responseHandler = new RestSvcResponse(response, action, context);
			  	}
			  	else{
			  		System.out.println("null response");
			  		Toast.makeText(context, "No data from the Web Service!!", Toast.LENGTH_LONG).show();
			  }
		    }
}
