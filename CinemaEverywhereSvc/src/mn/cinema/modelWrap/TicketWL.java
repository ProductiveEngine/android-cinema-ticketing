package mn.cinema.modelWrap;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({TicketW.class})
public class TicketWL {
	
	private List<TicketW> TicketLstW = new ArrayList<>();
	
	public TicketWL(List<TicketW> TicketLstW){
		this.TicketLstW = TicketLstW;
	}
	public TicketWL(){
		
	}
	public List<TicketW> getTicketLstW() {
		return TicketLstW;
	}
	public void setTicketLstW(List<TicketW> TicketLstW) {
		this.TicketLstW = TicketLstW;
	}
	
}
