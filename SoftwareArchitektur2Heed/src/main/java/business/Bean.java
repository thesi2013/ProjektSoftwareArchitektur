package business;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.util.GenericType;

import restfulService.EmployeeTO;
import entities.Raum;
import entities.Reservation;
import restfulService.RestfulClient;

@Stateless
@Named
public class Bean {
	
	@PersistenceContext(unitName = "primary")
	EntityManager em;
	
	@Inject
	private ReservationData data = new ReservationData ();
	@Inject
	private Reservation reservation = new Reservation();
	
	public void storeData(ReservationData resData){
		System.out.println("RMB - storeData() aufgerufen!");
//		data.setEmployeeID(resData.getEmployeeID());
		data.setRaumGroesse(resData.getRaumGroesse());
		data.setDatumVon(resData.getDatumVon());
		data.setDatumBis(resData.getDatumBis());
		System.out.println("Zeit: ");
		System.out.println(resData.getDatumVon());
		System.out.println(resData.getDatumBis());
		System.out.println(data.getDatumVon());
		System.out.println(data.getDatumBis());
	}
	
	
	//Methode availableRooms() angepasst, um einen Raum abzufragen und zuzuweisen (Sven)
		public List <Raum> availableRooms(ReservationData resData){
			
			System.out.println("Bean - availableRooms aufgerufen");
			
			TypedQuery<Raum> query = em.createQuery("SELECT r FROM entities.Raum r"
	                + " WHERE (r.idRaum NOT IN ( SELECT raum FROM entities.Reservation res WHERE reserviertVon > :startDate AND reserviertVon < :endDate) "
	                + "AND r.idRaum NOT IN ( SELECT raum FROM entities.Reservation res WHERE reserviertBis > :startDate AND reserviertBis < :endDate) "
	                + "AND r.idRaum NOT IN ( SELECT raum FROM entities.Reservation res WHERE :startDate > reserviertVon AND :startDate < reserviertBis) "
	                + "AND r.idRaum NOT IN ( SELECT raum FROM entities.Reservation res WHERE :endDate > reserviertVon AND :endDate < reserviertBis)) "
	                + " AND r.nutzungskategorie = '" + resData.getNutzungskateID() + "' AND r.groesse = '" + resData.getRaumGroesse() +"' ORDER BY r.idRaum", Raum.class)
	    .setParameter("startDate", resData.getDatumVon(), TemporalType.TIMESTAMP)
	    .setParameter("endDate", resData.getDatumBis(), TemporalType.TIMESTAMP);
			
			List <Raum> list =query.getResultList();
			
			return list;
			
		}
		
		//Reservation UPDATE
		
//public List <Raum> updateRooms(ResData reservation){
//			
//			System.out.println("Bean - updateRooms aufgerufen");
//			System.out.println("............. Nutzungskategorie: "+ reservation.getRaum().getNutzungskategorie().getIdNutzungskategorie());
//			System.out.println("............. Grösse: "+ reservation.getRaum().getGroesse());
//			
//			TypedQuery<Raum> query = em.createQuery("SELECT r FROM entities.Raum r"
//	                + " WHERE (r.idRaum NOT IN ( SELECT raum FROM entities.Reservation res WHERE reserviertVon > :startDate AND reserviertVon < :endDate) "
//	                + "AND r.idRaum NOT IN ( SELECT raum FROM entities.Reservation res WHERE reserviertBis > :startDate AND reserviertBis < :endDate) "
//	                + "AND r.idRaum NOT IN ( SELECT raum FROM entities.Reservation res WHERE :startDate > reserviertVon AND :startDate < reserviertBis) "
//	                + "AND r.idRaum NOT IN ( SELECT raum FROM entities.Reservation res WHERE :endDate > reserviertVon AND :endDate < reserviertBis)) "
//	                + " AND r.nutzungskategorie = '" + reservation.getRaum().getNutzungskategorie().getIdNutzungskategorie() + "' AND r.groesse = '" + reservation.getRaum().getGroesse() +"' ORDER BY r.idRaum", Raum.class)
//	    .setParameter("startDate", reservation.getReserviertVon(), TemporalType.TIMESTAMP)
//	    .setParameter("endDate", reservation.getReserviertBis(), TemporalType.TIMESTAMP);
//			
//			List <Raum> list =query.getResultList();
//			
//			return list;
//			
//		}
	
	
//	public List<EmployeeTO> loadEmployeeName(){
//		List <EmployeeTO> employees = new LinkedList <EmployeeTO>();
//			try {
//				ClientRequest request = new ClientRequest("http://employeemanager-esaeservice.rhcloud.com/rs/Employees");
//				//request.accept("application/json");
//				
//				ClientResponse <List <EmployeeTO>> response = request.get(new GenericType<List<EmployeeTO>>(){}) ;
//				
//				if (response.getStatus() != 200){
//					throw new RuntimeException("Failed : HTTP error code : " 
//							+ response.getStatus());
//				}		
//				
//				
//				
//				employees = response.getEntity();			
//				
//				Iterator <EmployeeTO> iter = employees.iterator();
//				while (iter.hasNext()){
//					System.out.println (iter.next());
//				}
//				
//				
//				}
//				catch (Exception e) {
//					e.printStackTrace();
//				}
//			System.out.println ("RMB - Mitarbeiter geladen");
//			return employees;			
//		}
	
	
	
		public void addReservation(ReservationData resData){
			reservation.setIdMitarbeiter(resData.getEmployee().getId());
			List <Raum> list = availableRooms(resData);
			if(list.isEmpty()){
				System.out.println("kein Raum gefunden!");
				
			}
			else{
				Raum room = list.get(0);
				System.out.println( room.getIdRaum() + " = TEST: Raum ID");
				reservation.setRaum(room);
				reservation.setReserviertBis(resData.getDatumBis());
				reservation.setReserviertVon(resData.getDatumVon());
				
				em.persist(reservation);
				System.out.println("Bean - in DB gespeichert.");
			}

		}
	
	public List<Reservation> loadAllReservations(){
		System.out.println("RMB -  loadAllReservations() aufgerufen");
		List<Reservation> reservations = new LinkedList<Reservation>();
		TypedQuery<Reservation> query = em.createQuery("SELECT r FROM entities.Reservation r", Reservation.class);
		reservations =query.getResultList();
		Iterator <Reservation> iter = reservations.iterator();
		while (iter.hasNext()){
			System.out.println (iter.next().getReserviertBis());
		}
		
		
		return reservations;
		
	}
	
	//Added to Delete Reservation
	 public void deleteReservation(Reservation reservation) {
	        em.remove(em.merge(reservation));
	    }
	 
	 public void updateReservation(ReservationData resData) {
		 System.out.println("update Reservation: " + resData.getEmployee().getId());
		 	List <Raum> list = availableRooms(resData);
			if(list.isEmpty()){
				System.out.println("kein Raum gefunden!");
			}
			else{
				Raum room = list.get(0);
				System.out.println( room.getIdRaum() + " = TEST: Raum ID");
				reservation.setRaum(room);
				reservation.setReserviertBis(resData.getDatumBis());
				reservation.setReserviertVon(resData.getDatumVon());
				
				em.merge(reservation);
			}
		 
		 
		 	
//		 TypedQuery<Reservation> queryUpdate = em.createQuery("UPDATE entities.Reservation res SET res.raumgroesse = '" + reservation.getRaum().getGroesse() + "' WHERE res.idReservation = '" + reservation.getIdReservation() + "'", Reservation.class);
//		 reservation = (Reservation) queryUpdate;
//		 em.persist(reservation);
		 
//	        return reservation;
	    }
	
	

}
