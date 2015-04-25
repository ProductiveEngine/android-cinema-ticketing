package mn.cinema.modelWrap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mn.cinema.model.TimestampAdapter;
import mn.cinema.model.Viewmovie;

public class ViewmovieW {

	private int viewmovieId;
	private Date startTime,endTime;
	private double ticketPrice;
	
	public ViewmovieW(){
		
	}
	
	public ViewmovieW(Viewmovie viewmovie){
			
		TimestampAdapter adapter = new TimestampAdapter();
		
		this.viewmovieId = viewmovie.getViewmovieId();
		this.startTime = adapter.marshal(viewmovie.getStartTime());
		this.endTime = adapter.marshal(viewmovie.getEndTime());
		this.ticketPrice = viewmovie.getTicketPrice();
	}

	public static Viewmovie unWrap(ViewmovieW vW){
		
		TimestampAdapter adapter = new TimestampAdapter();
		
		Viewmovie viewmovie = new Viewmovie();
		viewmovie.setViewmovieId(vW.viewmovieId);
		viewmovie.setStartTime(adapter.unmarshal(vW.startTime));
		viewmovie.setEndTime(adapter.unmarshal(vW.endTime));
		viewmovie.setTicketPrice(vW.ticketPrice);
		
		return viewmovie;
	}
	
	
	public static ViewMovieWL wrapList(List<Viewmovie> viewLst){
		
		List<ViewmovieW> viewmovieLstW = new ArrayList<>();
		for(Viewmovie viemovie : viewLst){
			viewmovieLstW.add(new ViewmovieW(viemovie));
	    }
		return new ViewMovieWL(viewmovieLstW);
	}
}
