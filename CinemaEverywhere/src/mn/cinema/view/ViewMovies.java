package mn.cinema.view;

import java.util.ArrayList;
import java.util.List;

import mn.cinema.common.Action;
import mn.cinema.common.GlobalValues;
import mn.cinema.modelce.Movie;
import mn.cinema.restclient.RestSvcClient;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewMovies extends Activity {

	//variable declaration
	TextView selectedCinemaName;
	RestSvcClient restClient;
	ListView ml;
	List<Movie> movieList;
	Action action;
	GlobalValues gv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_movies);
		
		setUpViewMovies();
		
	}

	private void setUpViewMovies() {
		
		Intent viewMoviesIntent = getIntent();
		//get cinema params from bundle on intent
		Bundle viewMoviesBundle =  viewMoviesIntent.getExtras();
		Integer  cinemaId= viewMoviesBundle.getInt("cinemaId");
		String cinemaName = viewMoviesBundle.getString("cinemaName");
		
		selectedCinemaName = (TextView)this.findViewById(R.id.selectedCinemaTextView);
		selectedCinemaName.setText(cinemaName);
		
		ml =(ListView)this.findViewById(R.id.moviesListView);
		
		ml.setOnItemClickListener(new OnItemClickListener() {//on listview item click

			@SuppressWarnings("unchecked")
			@Override
			public void onItemClick(AdapterView l, View v,int position, long id) {
               
				//should be replaced with something more... elegant
				for(Movie m:GlobalValues.getMovieList()){//if movie name is found in movie list, get the id
					if(m.getMovieName() == ml.getItemAtPosition(position).toString()){
						Toast.makeText(getBaseContext(), m.getMovieName().toString()+" id:"+m.getMovieId(), Toast.LENGTH_LONG).show();
        		 
						GlobalValues.setMovie(m);
					}
        	 
         }
         
				Intent movieDetails = new Intent(ViewMovies.this,MovieDetails.class);
       
				movieDetails.putExtra("movieId", GlobalValues.getMovie().getMovieId());
				movieDetails.putExtra("movieName",GlobalValues.getMovie().getMovieName());
 		
				startActivity(movieDetails);    
         
     }
 });
		//set the action for the service request and perform it
		action = Action.GET_MOVIES;
		communicateWithSvc(action);
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
			
		Intent goToBuyTickets  = new Intent (ViewMovies.this, BuyTicketsActivity.class);	
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
	
	private void communicateWithSvc(Action action){
		
		restClient = new RestSvcClient(action, this);
		restClient.retrieveData();
		
	}

	public void fillListView(List<Movie> movieLst){
	
		if(movieLst!=null){
			
			
			ArrayAdapter<String> adapter;
			ArrayList<String> mvList = new ArrayList<String>();
					
			for(Movie mv:movieLst){
				mvList.add(mv.getMovieName());
			}
			
			
			//arraylist Append
			adapter=new ArrayAdapter<String>(this,
			            android.R.layout.simple_list_item_1,
			            mvList);
			ml.setAdapter(adapter);
			
			GlobalValues.setMovieList(movieLst);
		}
		
	}
	
	
}
