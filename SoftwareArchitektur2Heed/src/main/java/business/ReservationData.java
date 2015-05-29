package business;

import java.util.LinkedList;
import java.util.List;

import entities.Raum;

public class ReservationData {
	
	private String datumVon;
	private String datumBis;
	private String raumGroesse;
	private int nutzungskateID;
	private List <Raum> rooms = new LinkedList <Raum>();
	private int employeeID;
	
	
	public String getDatumVon() {
		return datumVon;
	}
	public void setDatumVon(String datumVon) {
		this.datumVon = datumVon;
	}
	public String getDatumBis() {
		return datumBis;
	}
	public void setDatumBis(String datumBis) {
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
	
	
	
}
