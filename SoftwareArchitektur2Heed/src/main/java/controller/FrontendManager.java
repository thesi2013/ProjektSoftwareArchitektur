package controller;

import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

import business.ReservationManagerBean;
import entities.Reservation;

@Named
@RequestScoped
public class FrontendManager {
	
	private Reservation entity = new Reservation();

	@EJB
	ReservationManagerBean bean;
	
	public void searchRooms(){
		System.out.println("methode searchBooks() aufgerufen");
		
	}
	
	
	
	public void store(){
		System.out.println("gespeichert");
		bean.addReservation(entity);
	}
	
	public List <String> getReservation(){
		return bean.getReservations();
	}

	public Reservation getEntity() {
		return entity;
	}

	public void setEntity(Reservation entity) {
		this.entity = entity;
	}
	
	

}
