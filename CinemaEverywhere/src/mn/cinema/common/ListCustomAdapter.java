package mn.cinema.common;

import mn.cinema.view.Booking;
import mn.cinema.view.MovieDetails;
import mn.cinema.view.R;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ListCustomAdapter extends ArrayAdapter<String> {
	
	  private Context context;
	  private String[] values = null;
	  private String[] viewsDate = null;
	  private String[] viewsStart = null;
	  private String[] viewsEnd = null;
	  private String[] viewsId = null;
	  private String[] price = null;


	  public ListCustomAdapter(Context context, String[] values) {
	    super(context, R.layout.activity_movie_details, values);
	    this.context = context;
	    this.values = values;
	  }

	  public ListCustomAdapter(Context context, String[] values,
			String[] viewsDate, String[] viewsStart, String[] viewsEnd, String[] viewsId, String[] price){
		  super(context, R.layout.activity_movie_details, values);
		  	this.context = context;
		    this.values = values;
		    this.viewsDate = viewsDate;
		    this.viewsStart = viewsStart;
		    this.viewsEnd = viewsEnd;
		    this.viewsId = viewsId;
		    this.price = price;
	}

	public ListCustomAdapter(Context context) {
		 super(context, R.layout.activity_movie_details);
		
	}

	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    
	    View rowView = inflater.inflate(R.layout.listview_item, parent, false);
	    TextView date = (TextView) rowView.findViewById(R.id.date);
	    TextView startTime = (TextView) rowView.findViewById(R.id.textviewStartTime);
	    TextView endTime = (TextView) rowView.findViewById(R.id.textviewEndTime);

	    Button btn = (Button)rowView.findViewById(R.id.btn_book);
	    btn.setText("BOOK");
	    date.setText("Room:"+values[position]+ " Date: " +viewsDate[position] + " Price:"+ price[position]);
	    startTime.setText(viewsStart[position]);
	    endTime.setText(viewsEnd[position]);
	    rowView.setTag(viewsId[position]);
	    
	    OnClickListener itemClickListener = new OnClickListener() {
            public void onClick(View v) {
            	
        	    TextView date = (TextView) v.findViewById(R.id.date);
        	    TextView startTime = (TextView) v.findViewById(R.id.textviewStartTime);
        	    TextView endTime = (TextView) v.findViewById(R.id.textviewEndTime);
            	
            	Intent book = new Intent( v.getContext(), Booking.class);
        		book.putExtra("date", date.getText() );
        		book.putExtra("startTime", startTime.getText() );
        		book.putExtra("endTime", endTime.getText() );
        		book.putExtra("viewsId", v.getTag().toString());
        		
        		MovieDetails mvd =(MovieDetails)v.getContext();
        		mvd.onBookClick(book);
        		
            }
        };
	  
        rowView.setOnClickListener(itemClickListener);

	    return rowView;
	  }
	
	@Override
    public boolean areAllItemsEnabled() 
    {
        return true;
    }

    @Override
    public boolean isEnabled(int arg0) 
    {
        return true;
    }

}
