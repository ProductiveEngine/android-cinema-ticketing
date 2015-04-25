package mn.cinema.view;

import mn.cinema.common.Action;
import mn.cinema.common.GlobalValues;
import mn.cinema.modelce.DatabaseConn;
import mn.cinema.modelce.Ticket;
import mn.cinema.restclient.RestSvcClient;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Booking extends Activity {

	TextView selectedCinemaName;
	TextView selectedMovieName;
	TextView bookingDetailsTv;
	EditText ticketNo;
	EditText name;
	EditText surname;
	RestSvcClient restClient;
	Action action;
	int viewsId;
	String bookingDetails;
	DatabaseConn db;
	Button book;
	String startTime;
	String endTime;
	String date;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_booking);
		
		//get details from intent
		Intent getBookingDetailsIntent = getIntent();
		Bundle getBookingDetailsBundle =  getBookingDetailsIntent.getExtras();
		startTime = getBookingDetailsBundle.getString("startTime");
		endTime = getBookingDetailsBundle.getString("endTime");
		date =getBookingDetailsBundle.getString("date");
		bookingDetails = date +"\n"+startTime+"-"+endTime;
		int vid = Integer.valueOf(getBookingDetailsBundle.getString("viewsId"));
		GlobalValues.setViewId(vid);
		viewsId = vid;
		
		selectedCinemaName = (TextView)this.findViewById(R.id.textViewSelectedCinemaNameFinal);
		selectedMovieName = (TextView)this.findViewById(R.id.textViewselectedMovieNameFinal);
		bookingDetailsTv = (TextView)this.findViewById(R.id.bookingDetailsTextView);
		ticketNo = (EditText)this.findViewById(R.id.ticketNumberEditText);
		name = (EditText)this.findViewById(R.id.nameEditText);
		surname = (EditText)this.findViewById(R.id.surnameEditText);
		book = (Button)this.findViewById(R.id.bookingButton);
		
		//set initial values
		selectedCinemaName.setText(GlobalValues.getCinema().getCinemaName());
		selectedMovieName.setText(GlobalValues.getMovie().getMovieName());
		bookingDetailsTv.setText(bookingDetails);
		ticketNo.setEnabled(true);
		name.setEnabled(true);
		surname.setEnabled(true);
		book.setEnabled(true);
	}

	public void onBookTicketsClick(View v){
		
		if(!ticketNo.getText().toString().equalsIgnoreCase("") && !name.getText().toString().equalsIgnoreCase("") && !surname.getText().toString().equalsIgnoreCase("")){
			
			GlobalValues.setName(name.getText().toString());
			GlobalValues.setSurname(surname.getText().toString());
	
			action = Action.BOOK;
			communicateWithSvc(action);
		}
		
		else{
			
			Toast.makeText(getApplicationContext(), "Please fill in your information!!!", Toast.LENGTH_LONG).show();

		}
	}
	
	private void communicateWithSvc(Action action){
		
		restClient = new RestSvcClient(action, this);
		restClient.postData();
		
		
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
			
		Intent goToBuyTickets  = new Intent (Booking.this, BuyTicketsActivity.class);	
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
	public void bookingResult(Ticket ticket){
		
		if(ticket.getTicketId()>0){//if ticket saved successfully
			
			ticketNo.setEnabled(false);
			name.setEnabled(false);
			surname.setEnabled(false);
			book.setEnabled(false);
			
			Toast.makeText(getApplicationContext(), "Booked "+ticketNo.getText()+" tickets for "+surname.getText()+" "+ name.getText(), Toast.LENGTH_SHORT).show();

			//Save ticket in database for future reference
			String info = GlobalValues.getCinema().getCinemaName()+" : " +GlobalValues.getMovie().getMovieName()+"\n"+date+"\nView:"+startTime+"-"+ endTime;
			db = new DatabaseConn(this);
	    	
	    	db.open();
	    	long result = db.saveBookedTicket(GlobalValues.getName(), GlobalValues.getSurname(), ticket.getTicketId(), info, 0);
	    	
	    	if(result>0){
				Toast.makeText(getApplicationContext(), "Booking Saved Successfully!\n"+info, Toast.LENGTH_LONG).show();
	    	}
	    	else{
	    		Toast.makeText(getApplicationContext(), "Booking NOT saved!!!!", Toast.LENGTH_LONG).show();
	    	}
	    	
	    	db.close();
	    	
		}
		
		else{	
			Toast.makeText(getApplicationContext(), "Could not book tickets", Toast.LENGTH_LONG).show();
		}
		
	}

}
