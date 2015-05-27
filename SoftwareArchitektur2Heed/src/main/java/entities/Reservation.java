package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * Entity implementation class for Entity: Reservation
 *
 */
@NamedQueries({
	@NamedQuery(name = Reservation.findAll, query = "SELECT Res FROM Reservation Res")
})


@Entity
@Table (name = "Reservation")

public class Reservation implements Serializable {

	public static final String findAll = "Reservation.findAll";
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idReservation;
	
	@OneToOne
	@JoinColumn(name = "idRaum")
	private Raum raum;
	
	private int idMitarbeiter;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date reserviertVon;
	@Temporal(TemporalType.TIMESTAMP)
	private Date reserviertBis;
	
	
	
	public int getIdReservation() {
		return idReservation;
	}
	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}
	public Raum getRaum() {
		return raum;
	}
	public void setRaum(Raum raum) {
		this.raum = raum;
	}
	public int getIdMitarbeiter() {
		return idMitarbeiter;
	}
	public void setIdMitarbeiter(int idMitarbeiter) {
		this.idMitarbeiter = idMitarbeiter;
	}
	public Date getReserviertVon() {
		return reserviertVon;
	}
	public void setReserviertVon(Date reserviertVon) {
		this.reserviertVon = reserviertVon;
	}
	public Date getReserviertBis() {
		return reserviertBis;
	}
	public void setReserviertBis(Date reserviertBis) {
		this.reserviertBis = reserviertBis;
	}
	
	
   
}