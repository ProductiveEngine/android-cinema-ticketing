package mn.cinema.view;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import mn.cinema.common.Action;
import mn.cinema.common.GlobalValues;
import mn.cinema.modelce.Cinema;
import mn.cinema.restclient.RestSvcClient;
import mn.cinema.restclient.RestSvcResponse;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;

/**
 * This shows how to create a simple activity with a map and a marker on the map.
 * <p>
 * Notice how we deal with the possibility that the Google Play services APK is not
 * installed/enabled/updated on a user's device.
 */
public class MainMenu extends FragmentActivity implements OnMarkerClickListener, OnInfoWindowClickListener, android.location.LocationListener {
    /**
     * Note that this may be null if the Google Play services APK is not available.
     */
    private GoogleMap mMap;
    private static LatLng lng;
    Drawable drawable;
    MapController mMapController;
    TextView location;
    LocationManager lm;
    GeoPoint gp;  
    CameraPosition myPosition;
    Location lastKnownLoc;
    List<LatLng> cinemaLocations;
    InfoWindowAdapter iwa;
    RestSvcClient restClient;
    RestSvcResponse responseHandler;
    List<Cinema> cinemaList;
    Action action;
    HashMap<Marker, Cinema> markerMap;
    List<String[]> listOfParams;
    String[] params;
    String provider;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
	
        location =(TextView)findViewById(R.id.textViewselectedMovieNameFinal);
		markerMap = new HashMap<Marker, Cinema>();

		setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
     
   
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
	
		//set an intent to menu to start buy tickets
		Intent goToBuyTickets  = new Intent (MainMenu.this, BuyTicketsActivity.class);	
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
	
	
    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not have been
     * completely destroyed during this process (it is likely that it would only be stopped or
     * paused), {@link #onCreate(Bundle)} may not be called again so we should call this method in
     * {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

  
    /**
     * Used to add markers etc
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
    	
    		mMap.setMyLocationEnabled(true);//to get the users location
    		
    		mMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener(){//when an info window is clicked
    		    public void onInfoWindowClick(Marker marker){    		    	
    		    	if(marker.getTitle().equalsIgnoreCase("You are here!!")){//if marker shows user position on click, refresh cinemas around
    		    		
    		    		action = Action.GET_CINEMAS;
    		    		communicateWithSvc(action);
    		    		
    				}
    		    	else{

    		    		GlobalValues.setCinema(markerMap.get(marker));
    		    		
    		    		Intent viewMovies = new Intent(MainMenu.this,ViewMovies.class);
    		    		viewMovies.putExtra("cinemaId", markerMap.get(marker).getCinemaId());
    		    		viewMovies.putExtra("cinemaName", markerMap.get(marker).getCinemaName());
    		    		startActivity(viewMovies);
    		    	}
    		          
    		      }
    		    }
    		  );
    		
    		
    		
    		lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    		//lastKnownLoc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    		Criteria criteria = new Criteria();
    	    provider = lm.getBestProvider(criteria, false);
    	    lm.requestLocationUpdates(provider, 1000, 1, this);
    		lastKnownLoc = lm.getLastKnownLocation(provider);
		
			getUsersCurrentLocation();
			getCinemasAroundMe();
        
    }
    
    public void getCinemasAroundMe() {// interact with service and get cinemas
	
    	action = Action.GET_CINEMAS;
    	
    	communicateWithSvc(action);

	}

	private void getUsersCurrentLocation() {

		try{ 
			if(mMap.getMyLocation()!=null){// one way to get location
		
				location.setText("Your current location is:" +lastKnownLoc.getLongitude()+" "+lastKnownLoc.getLatitude());
				
				lng = new LatLng(mMap.getMyLocation().getLatitude(), mMap.getMyLocation().getLongitude());
				
				CameraPosition POSITION = new CameraPosition.Builder().target(lng).zoom(17).bearing(320).tilt(30).build();
				mMap.moveCamera(CameraUpdateFactory.newCameraPosition(POSITION));
			}
        
			else{//using a location manager instead
				
				
				if(lastKnownLoc!=null){

					location.setText("Your current location is:" +lastKnownLoc.getLongitude()+" "+lastKnownLoc.getLatitude());
				
					lng = new LatLng(lastKnownLoc.getLatitude(), lastKnownLoc.getLongitude());
					
					myPosition = new CameraPosition.Builder().target(lng).zoom(17).bearing(320).tilt(30).build();
					
					mMap.moveCamera(CameraUpdateFactory.newCameraPosition(myPosition));
					mMap.addMarker(new MarkerOptions().position(lng).title("You are here!!").snippet("Tap to refresh cinemas around you!"));
					
				}

				else{//changed settings for provider so we should not be needing this... 
					
					lastKnownLoc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
					location.setText("Your current location is:" +lastKnownLoc.getLongitude()+" "+lastKnownLoc.getLatitude());
					
					lng = new LatLng(lastKnownLoc.getLatitude(), lastKnownLoc.getLongitude());
					
					myPosition = new CameraPosition.Builder().target(lng).zoom(17).bearing(320).tilt(30).build();
					
					mMap.moveCamera(CameraUpdateFactory.newCameraPosition(myPosition));
					mMap.addMarker(new MarkerOptions().position(lng).title("You are here!!").snippet("Tap to refresh cinemas around you!"));
				}
			}
		}
		catch(Exception ex){
			
			Toast.makeText(getBaseContext(), "Cannot get your location... Please, try again later on!"+ex.toString(), Toast.LENGTH_LONG).show();
			myPosition = new CameraPosition.Builder().target(new LatLng(0,0)).zoom(17).bearing(320).tilt(30).build();
			mMap.moveCamera(CameraUpdateFactory.newCameraPosition(myPosition));
			
			}
		
		GlobalValues.setUserLocation(lng);
	}


	@Override
	public boolean onMarkerClick(Marker marker) {

		marker.showInfoWindow();
		
		return false;
	}


	@Override
	public void onInfoWindowClick(Marker arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public LatLng getUsersLocation(){
		
		return lng;
	}
	

private void communicateWithSvc(Action action){
	
	restClient = new RestSvcClient(action, this);
	restClient.retrieveData();
	
}
	
public void setCinemaList(List<Cinema> list){
	
	this.cinemaList = list;
	
}
public void populateMap(List<Cinema> list){

	cinemaList = list;
	
	if(list!=null){
    	
    	Iterator<Cinema> cnm = cinemaList.iterator();
    	
    	while(cnm.hasNext()){
    		Cinema cinema = cnm.next();
    		Double x = cinema.getX();
    		Double y = cinema.getY();
    		
    		myPosition = new CameraPosition.Builder().target(new LatLng(x,y)).zoom(17).bearing(320).tilt(30).build();
			//mMap.moveCamera(CameraUpdateFactory.newCameraPosition(myPosition));
			
			MarkerOptions markerOptions = new MarkerOptions().position(myPosition.target).title(cinema.getCinemaName()).snippet("Info:"+cinema.getInfo()+"\nPhone: "+cinema.getPhone()+"\n Tap here to select this cinema!");

			Marker marker = mMap.addMarker(markerOptions);
			

			//for future reference to specific markers
			markerMap.put(marker, cinema);
			System.out.println(marker);
    		
    		
    	}
	}

	else {//might be handled by rest client code...
		Toast.makeText(getBaseContext(), "No cinemas around you!", Toast.LENGTH_LONG).show();
	}
	
}

public int getClickedCinemaId(Marker marker){
	
	return (markerMap.get(marker)).getCinemaId();
	
	
}

@Override
public void onLocationChanged(Location location) {

	lastKnownLoc = location; 
	getUsersCurrentLocation();
	
}

@Override
public void onProviderDisabled(String arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void onProviderEnabled(String arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
	// TODO Auto-generated method stub
	
}
	
}
