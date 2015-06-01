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
	
	
	public List<Raum> availableRooms(){
		
		TypedQuery<Raum> query = em.createQuery("SELECT r FROM entities.Raum r", Raum.class);
		List <Raum> list =query.getResultList();
		/*System.out.println(data.getRooms().get(0).getBezeichnung());
		List<Raum> list = new LinkedList<Raum>();
		Raum testraum = new Raum();
		testraum.setBezeichnung("test");
		Raum testraum2 = new Raum();
		testraum2.setBezeichnung("test2");
		list.add(testraum);
		list.add(testraum2);
		System.out.println("availableRooms() durchgeführt");*/
		return list;
		
	}
	
	
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
//		reservation.setRaum();
		reservation.setReserviertBis(resData.getDatumBis());
		reservation.setReserviertVon(resData.getDatumVon());
		
		em.persist(reservation);
		System.out.println("Bean - in DB gespeichert.");
	}
	
	public List<Reservation> loadAllReservations(){
		System.out.println("RMB -  loadAllReservations() aufgerufen");
		List<Reservation> reservations = new LinkedList<Reservation>();
		TypedQuery<Reservation> query = em.createQuery("SELECT r FROM entities.Reservation r", Reservation.class);
		reservations =query.getResultList();
		Iterator <Reservation> iter = reservations.iterator();
		while (iter.hasNext()){
			System.out.println (iter.next().getReserviertBis() + "Warum ist .0 fraction, obwohl in DB datetime(0) definiert?");
		}
		
		
		return reservations;
		
	}
	
	

}
