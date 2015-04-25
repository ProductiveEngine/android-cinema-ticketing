package mn.cinema.modelWrap;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({RoomW.class})
public class RoomWL {
	private List<RoomW> RoomLstW = new ArrayList<>();

	public RoomWL(){
		
	}	
	public RoomWL(List<RoomW> RoomLstW){
		this.RoomLstW = RoomLstW;
	}	
	public List<RoomW> getRoomLstW() {
		return RoomLstW;
	}
	public void setRoomLstW(List<RoomW> RoomLstW) {
		this.RoomLstW = RoomLstW;
	}	
}

