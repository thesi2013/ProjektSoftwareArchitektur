package business;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import entities.Raum;

public class ReservationData {
	
	private Date datumVon;
	private Date datumBis;
	private String raumGroesse;
	private int nutzungskateID;
	private List <Raum> rooms = new LinkedList <Raum>();
	private Raum identifyRoom = new Raum();
	private int employeeID;
	
	
	
	

	public Date getDatumVon() {
		return datumVon;
	}
	public void setDatumVon(Date datumVon) {
		this.datumVon = datumVon;
	}
	public Date getDatumBis() {
		return datumBis;
	}
	public void setDatumBis(Date datumBis) {
		this.datumBis = datumBis;
	}
	public List<Raum> getRooms() {
		return rooms;
	}
	public void setRooms(List<Raum> rooms) {
		this.rooms = rooms;
	}
	public String getRaumGroesse() {
		return raumGroesse;
	}
	public void setRaumGroesse(String raumGroesse) {
		this.raumGroesse = raumGroesse;
	}
	public int getNutzungskateID() {
		return nutzungskateID;
	}
	public void setNutzungskateID(int nutzungskateID) {
		this.nutzungskateID = nutzungskateID;
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public Raum getIdentifyRoom() {
		return identifyRoom;
	}
	public void setIdentifyRoom(Raum identifyRoom) {
		this.identifyRoom = identifyRoom;
	}
	
	
	
}
