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
	private int id;
	@Temporal(TemporalType.DATE)
	private Date datumVon;
	@Temporal(TemporalType.DATE)
	private Date datumBis;
	/*@Temporal(TemporalType.TIME)
	private Time zeitVon;
	@Temporal(TemporalType.TIME)
	private Time zeitBis;*/
	
	private int mitarbeiterId;
	
	@OneToOne
	@JoinColumn(name = "raumId_FK")
	private Raum raum;

	
	
	public Date getDatumVon() {
		return datumVon;
	}

	public void setDatumVon(Date datumVon) {
		this.datumVon = datumVon;
	}

	public Date getDatumBis() {
		return datumBis;
	}

	public void setDatumBis(Date datumBis) {
		this.datumBis = datumBis;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMitarbeiterId() {
		return mitarbeiterId;
	}

	public void setMitarbeiterId(int mitarbeiterId) {
		this.mitarbeiterId = mitarbeiterId;
	}

	
   
}