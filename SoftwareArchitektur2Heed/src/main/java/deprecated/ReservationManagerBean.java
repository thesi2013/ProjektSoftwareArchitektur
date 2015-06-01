package deprecated;

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

import business.ReservationData;
import restfulService.EmployeeTO;
import entities.Raum;
import entities.Reservation;
import restfulService.RestfulClient;

@Stateful
@Named
public class ReservationManagerBean {
	
	@PersistenceContext(unitName = "primary")
	EntityManager em;
	
	@Inject
	private ReservationData data = new ReservationData ();
	@Inject
	private Reservation reservation = new Reservation();
	
	public void storeData(ReservationData resData){
		System.out.println("RMB - storeData() aufgerufen!");
//		data.setEmployeeID(resData.getEmployeeID());
		data.setNutzungskateID(resData.getNutzungskateID());
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
		
		System.out.println("RMB - availableRooms() aufgerufen");
		
//		TypedQuery<Raum> query = em.createQuery("SELECT r FROM entities.Raum r", Raum.class);
		
		
		TypedQuery<Raum> query = em.createQuery("SELECT r FROM entities.Raum r"
                + " WHERE (r.idRaum NOT IN ( SELECT res FROM entities.Reservation res WHERE reserviertVon > :startDate AND reserviertVon < :endDate) "
                + "AND r.idRaum NOT IN ( SELECT res FROM entities.Reservation res WHERE reserviertBis > :startDate AND reserviertBis < :endDate) "
                + "AND r.idRaum NOT IN ( SELECT res FROM entities.Reservation res WHERE :startDate > reserviertVon AND :startDate < reserviertBis) "
                + "AND r.idRaum NOT IN ( SELECT res FROM entities.Reservation res WHERE :endDate > reserviertVon AND :endDate < reserviertBis)) "
                + " AND r.nutzungskategorie = '" + data.getNutzungskateID() + "' AND r.groesse = '" + data.getRaumGroesse() +"' ORDER BY r.idRaum", Raum.class)
    .setParameter("startDate", data.getDatumVon(), TemporalType.DATE)
    .setParameter("endDate", data.getDatumBis(), TemporalType.DATE);

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
	
	
	
	public void addReservation(int employeeId, Raum raum){
		System.out.println("Zeit: ");
		System.out.println(data.getDatumVon());
		System.out.println(data.getDatumBis());
		reservation.setIdMitarbeiter(employeeId);
		reservation.setRaum(raum);
		reservation.setReserviertBis(data.getDatumBis());
		reservation.setReserviertVon(data.getDatumVon());
		em.persist(reservation);
		System.out.println("RMB - Reservation hinzugefügt");
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
