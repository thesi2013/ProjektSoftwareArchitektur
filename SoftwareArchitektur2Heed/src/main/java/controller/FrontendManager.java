package controller;

import java.util.List;

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
	private Nutzungskategorie kategorie = new Nutzungskategorie();

	@EJB
	ReservationManagerBean bean;
	
	public void searchRooms(){
		System.out.println("methode searchBooks() aufgerufen");
		System.out.println(raum.getGroesse());
		System.out.println(kategorie.getKategorie());
		//bean.availableRooms(raum);
	}
	
	
	
	public void store(){
		System.out.println("gespeichert");
		bean.addReservation(reservation);
	}
	
	public List <String> getReservation(){
		return bean.getReservations();
	}

	public Reservation getEntity() {
		return reservation;
	}

	public void setEntity(Reservation entity) {
		this.reservation = entity;
	}

	public Raum getRaum() {
		return raum;
	}

	public void setRaum(Raum raum) {
		this.raum = raum;
	}



	public Nutzungskategorie getKategorie() {
		return kategorie;
	}



	public void setKategorie(Nutzungskategorie kategorie) {
		this.kategorie = kategorie;
	}
	
	

}
