package mn.cinema.view;

import java.util.List;

import mn.cinema.common.Action;
import mn.cinema.modelce.DatabaseConn;
import mn.cinema.restclient.RestSvcClient;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class BuyTicketsActivity extends Activity {
	List<String> groupList;
    List<String> childList;
    java.util.Map<String, List<String>> laptopCollection;
    ExpandableListView expListView;
    DatabaseConn db;
    Cursor ticketCursor;
    ListView ticketLV;
    RestSvcClient restClient;
    Action action;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_tickets);
 
        getTicketList();
    }
 
    @SuppressWarnings("deprecation")
	private void getTicketList() {
    	
    	db = new DatabaseConn(this);
    	
    	db.open();
    	ticketCursor = db.fetchAllDb();
    	String[] columns = new String[] {
    			db.KEY_ROWID,
    			db.KEY_FIRST_NAME,
    			db.KEY_LAST_NAME, 
    			db.KEY_SERVER_ID,  
    			db.KEY_INFO,
    			db.KEY_STATUS
    		  };
    	  int[] to = new int[] { 
    			    R.id.ticketId,
    			    R.id.ticketFirstName,
    			    R.id.ticketLastName,
    			    R.id.ticketServerId,
    			    R.id.ticketInfo,
    			    R.id.ticketStatus
    			  };
    			 
    		 
    	startManagingCursor(ticketCursor);
		
		SimpleCursorAdapter ticketList = new SimpleCursorAdapter(this,R.layout.ticket_list,ticketCursor,columns, to);
    	
		ticketLV = (ListView) findViewById(R.id.listViewTicketList);
		  // Assign adapter to ListView
		ticketLV.setAdapter(ticketList);
		
		ticketLV.setOnItemClickListener(new OnItemClickListener(){
			   @Override
			   public void onItemClick(AdapterView<?> listView, View view, 
			     int position, long id) {
			   // Get the cursor, positioned to the corresponding row in the result set
			   Cursor cursor = (Cursor) listView.getItemAtPosition(position);
			 
			   // Get the server id for the selected ticket
			   String ticketId = cursor.getString(cursor.getColumnIndexOrThrow("ServerId"));
			   Toast.makeText(getApplicationContext(),
					   ticketId, Toast.LENGTH_SHORT).show();
			 
			   }
			  });
		db.close();
    }
 
 
    // Convert pixel to dip
    public int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.booking, menu);
        return true;
    }

    //NOT IMPLEMENTED DUE TO LACK OF TIME
    public void onBuyButtonClick(View v){
    	TextView serverId =(TextView)findViewById(R.id.ticketServerId);
	
    	Toast.makeText(getApplicationContext(), "Tickets Bought!!!", Toast.LENGTH_LONG).show();
    	
    	action = Action.BUY;
    	

    }
    //NOT IMPLEMENTED DUE TO LACK OF TIME
    private void communicateWithSvc(Action action){
    	
    	restClient = new RestSvcClient(action, this);
    	restClient.postData();
    	
    }

}
