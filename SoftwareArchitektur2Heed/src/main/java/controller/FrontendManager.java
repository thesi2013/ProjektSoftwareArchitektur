package controller;


import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

import business.ReservationManagerBean;
import entities.Nutzungskategorie;
import entities.Raum;
import entities.Reservation;

@Named
@RequestScoped
public class FrontendManager {
	
	private Reservation reservation = new Reservation();
	private Raum raum = new Raum();
	private Nutzungskategorie nutzungskategorie = new Nutzungskategorie();

	@EJB
	ReservationManagerBean bean;
	
	public String searchRooms(){
		System.out.println("methode searchRooms() aufgerufen");
		System.out.println(raum.getGroesse());
		System.out.println(nutzungskategorie.getBezeichnung());
		//System.out.println(reservation.getReserviertVon());
		//System.out.println(reservation.getReserviertBis());
		
		return "available.xhtml";
		//bean.availableRooms(raum);
	}
	
	public void checkEmployeeName (){
		System.out.println("Methode checkEmployeeName() aufgerufen");
	}
	
	
	public void store(){
		System.out.println("gespeichert");
		bean.addReservation(reservation);
	}
	
	

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Raum getRaum() {
		return raum;
	}

	public void setRaum(Raum raum) {
		this.raum = raum;
	}
	
	
	

	public Nutzungskategorie getNutzungskategorie() {
		return nutzungskategorie;
	}

	public void setNutzungskategorie(Nutzungskategorie nutzungskategorie) {
		this.nutzungskategorie = nutzungskategorie;
	}

	
}
