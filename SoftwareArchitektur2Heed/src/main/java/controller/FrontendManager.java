package controller;


import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

import business.ReservationData;
import business.ReservationManagerBean;
import entities.Nutzungskategorie;
import entities.Raum;
import entities.Reservation;

@Named
@RequestScoped
public class FrontendManager {
	
	private ReservationData resData= new ReservationData();

	@EJB
	ReservationManagerBean bean;
	
	public String searchRooms(){
		System.out.println("methode searchRooms() aufgerufen");
		bean.storeData(resData);
		System.out.println("methode searchRooms() beendet");
		return "available.xhtml";
		
	}
	
	public List <Raum> getAvailableRooms(){
		System.out.println("frontendmanager getRaumGroesse() aufgerufen");
		return bean.availableRooms();
	}
	
	public void checkEmployeeName (){
		System.out.println("Methode checkEmployeeName() aufgerufen");
	}
	
	
	public void store(){
		System.out.println("gespeichert");
		//bean.addReservation(reservation);
	}

	
	
	
	
	
	
	public ReservationData getResData() {
		return resData;
	}

	public void setResData(ReservationData resData) {
		this.resData = resData;
	}
	
	

	
}
