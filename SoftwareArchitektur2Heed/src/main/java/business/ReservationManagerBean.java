package business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Raum;
import entities.Reservation;

@Stateless
@Named
public class ReservationManagerBean {
	
	@PersistenceContext(unitName = "primary")
	EntityManager em;
	
	
	public void availableRooms(Raum raum){
		
	}
	
	public void addReservation(Reservation entity){
		System.out.println("hinzugefügt");
		em.persist(entity);
	}
	
	public List<String> getReservations(){
		List <String> res = new ArrayList <String>();
		TypedQuery<Reservation> query = em.createNamedQuery(Reservation.findAll, Reservation.class);
		List <Reservation> resultat = query.getResultList();
		for (Reservation entity : resultat){
			res.add(entity.getDay());
		}
		return res;
	}

}
