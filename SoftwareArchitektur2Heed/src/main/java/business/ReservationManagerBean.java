package business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Raum;
import entities.Reservation;

@Stateful
@Named
public class ReservationManagerBean {
	
	@PersistenceContext(unitName = "primary")
	EntityManager em;
	
	private ReservationData data = new ReservationData ();
	
	
	public void storeData(ReservationData resData){
		data.setDatumBis(resData.getDatumBis());
		data.setDatumVon(resData.getDatumVon());
		data.setRaumGroesse(resData.getRaumGroesse());
		System.out.println("Methode availableRooms aufgerufen");
	}
	
	
	public List<Raum> availableRooms(){
		String statement = "select r from Raum r";
		Query query = em.createQuery(statement);
		List <Raum> list =query.getResultList();
		//System.out.println(data.getRooms().get(0).getBezeichnung());
		return list;
		
	}
	
	public void addReservation(Reservation entity){
		System.out.println("hinzugefügt");
		em.persist(entity);
	}
	
	

}
