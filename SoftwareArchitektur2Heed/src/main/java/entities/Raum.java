package entities;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Raum
 *
 */


@Entity
@Table (name = "Raum")
public class Raum implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String bezeichnung;
	
	@Column
	private String groesse;
	
	@OneToOne(mappedBy ="raum")
	private Reservation reservation;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	public String getGroesse() {
		return groesse;
	}
	public void setGroesse(String groesse) {
		this.groesse = groesse;
	}
   
}
