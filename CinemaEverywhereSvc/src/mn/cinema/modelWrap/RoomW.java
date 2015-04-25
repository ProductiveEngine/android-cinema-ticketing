package mn.cinema.modelWrap;

import java.util.ArrayList;
import java.util.List;

import mn.cinema.model.Cinema;
import mn.cinema.model.Room;

public class RoomW {

	private int roomId,totalSeats;

	public RoomW() {
		super();
	}	
	public RoomW(Room room){
		this.roomId = room.getRoomId();
		this.totalSeats = room.getTotalSeats();
	}
	public static Room unWrap(RoomW rW){
		Room room = new Room();
		room.setRoomId(rW.roomId);
		room.setTotalSeats(rW.totalSeats);
		
		return room;
	}
	public static RoomWL wrapList(List<Room> roomLst){
		
		List<RoomW> roomLstW = new ArrayList<>();
		for(Room room : roomLst){
	    	roomLstW.add(new RoomW(room));
	    }
		return new RoomWL(roomLstW);
	}
}
