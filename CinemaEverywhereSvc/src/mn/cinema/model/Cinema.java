package mn.cinema.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;


/**
 * The persistent class for the CINEMA database table.
 * 
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "CINEMA.findNearCinemas", query = "SELECT i FROM Cinema i WHERE ((i.x-:pointX)*(i.x-:pointX) + (i.y - :pointY)*(i.y - :pointY)) < :radius * :radius " )
})

@XmlRootElement
public class Cinema implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CINEMA_ID")
	private int cinemaId;

	@Column(name="CINEMA_NAME")
	private String cinemaName;

	private String info;

	private String phone;

	private double x;

	private double y;

	//bi-directional many-to-one association to Room
	@OneToMany(mappedBy="cinema")
	private List<Room> rooms;

	public Cinema() {
	}

	public int getCinemaId() {
		return this.cinemaId;
	}

	public void setCinemaId(int cinemaId) {
		this.cinemaId = cinemaId;
	}

	public String getCinemaName() {
		return this.cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public double getX() {
		return this.x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return this.y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public List<Room> getRooms() {
		return this.rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public Room addRoom(Room room) {
		getRooms().add(room);
		room.setCinema(this);

		return room;
	}

	public Room removeRoom(Room room) {
		getRooms().remove(room);
		room.setCinema(null);

		return room;
	}

	
}