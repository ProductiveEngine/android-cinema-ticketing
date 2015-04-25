package mn.cinema.view;

import java.util.ArrayList;
import java.util.List;

import mn.cinema.common.Action;
import mn.cinema.common.GlobalValues;
import mn.cinema.common.ListCustomAdapter;
import mn.cinema.modelce.MovieInCinema;
import mn.cinema.modelce.Room;
import mn.cinema.modelce.Viewmovie;
import mn.cinema.restclient.RestSvcClient;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MovieDetails extends Activity {

	TextView selectedCinemaName;
	TextView selectedMovieName;
	TextView selectedMovieInfo;
	RestSvcClient restClient;
	Action action;
	ListView lv;
	MovieInCinema mic;
	LinearLayout doBook;
	ListCustomAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_details);
				
		lv = (ListView)findViewById(R.id.movieDetailsListView);
		
		setUpMovieDetails();

	}

	private void setUpMovieDetails() {
		
		
		Intent movieDetailsIntent = getIntent();
		
		Bundle movieDetailsBundle =  movieDetailsIntent.getExtras();
		Integer  movieId= movieDetailsBundle.getInt("movieId");
		String movieName = movieDetailsBundle.getString("movieName");
		
		selectedCinemaName = (TextView)this.findViewById(R.id.selectedCinemaNameTextView);
		selectedCinemaName.setText(GlobalValues.getCinema().getCinemaName());
		
		//if GlobalValues is used then no need for bundle to pass values
		selectedMovieName = (TextView)this.findViewById(R.id.textViewselectedMovieNameFinal);
		selectedMovieName.setText(GlobalValues.getMovie().getMovieName());
		
		selectedMovieInfo = (TextView)this.findViewById(R.id.textViewMovieInfo);
		selectedMovieInfo.setText(GlobalValues.getMovie().getInfo());
			
		action = Action.GET_MOVIE_DETAILS;
		communicateWithSvc(action);
		
	}


	private void communicateWithSvc(Action action){
		
		restClient = new RestSvcClient(action, this);
		restClient.retrieveData();
		
		
	}
	

    //-----------------------------------------------------------------
	//Options Menu
	
	/**
	 * Create the Options menu
	 * 
	 * @param menu 
	 * 
	 * @return true
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_menu, menu);
	   
	    return true;
	} 
	
	/**
	 * Before creating the menu refresh the shuffle and loop states
	 * 
	 * @param menu
	 * 
	 * @return true
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu){
			
		Intent goToBuyTickets  = new Intent (MovieDetails.this, BuyTicketsActivity.class);	
		menu.getItem(1).setIntent(goToBuyTickets);
		
		return true;
	}
	
	/**
	 * Listener for the options menu
	 * 
	 * @param item
	 * 
	 * @return true
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	   
		// Handle item selection
	    switch (item.getItemId()) {
	    case 1:
	    	startActivity(item.getIntent());
	    	break;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
		return false;
	}

	@SuppressWarnings("deprecation")
	public void fillMovieDetailsListView(List<MovieInCinema> movieInCinema){
		
		
		if(movieInCinema!=null){
		
			List<Room> roomList = new ArrayList<Room>();
			List<Viewmovie> vmList = new ArrayList<Viewmovie>();
		
		
		for(MovieInCinema mic:movieInCinema){		
			
			roomList.add(mic.getRoom());
			vmList.add(mic.getViewMovie());
			
		}
		
		String[] room = new String[roomList.size()];
		String[] viewsDate = new String[vmList.size()];
		String[] viewsStart = new String[vmList.size()];
		String[] viewsEnd = new String[vmList.size()];
		String[] viewsId = new String[vmList.size()];
		String[] price = new String[vmList.size()];
		
		for(int i =0; i<roomList.size();i++){
			
			room[i] =  String.valueOf(roomList.get(i).getRoomId());
			
		}
		for(int i =0; i < vmList.size();i++){
			
			viewsDate[i] =  String.valueOf(vmList.get(i).getStartTime().getDate()+"/"+vmList.get(i).getStartTime().getMonth()+"/"+vmList.get(i).getStartTime().getYear());
			viewsStart[i] =  String.valueOf(vmList.get(i).getStartTime().getHours()+":"+vmList.get(i).getStartTime().getMinutes());
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(vmList.get(i).getEndTime().getHours());
			stringBuilder.append(":");
			stringBuilder.append(vmList.get(i).getEndTime().getMinutes());
			viewsEnd[i] =  String.valueOf(stringBuilder.toString());
			viewsId[i] =  String.valueOf(vmList.get(i).getViewmovieId());
			price[i]=String.valueOf(vmList.get(i).getTicketPrice());
			
		}

		
		 adapter = new ListCustomAdapter(this, room, viewsDate,viewsStart, viewsEnd, viewsId, price);
		
	     lv.setAdapter(adapter);

		}
		else{
			Toast.makeText(getApplicationContext(), "Could not load movie details!", Toast.LENGTH_LONG).show();
		}
	}
	
	public void onBookClick(Intent intent){
		
		Intent book = intent;
		startActivity(book);  
	}
}
