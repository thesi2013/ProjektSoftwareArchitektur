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
	@Column(columnDefinition="TINYINT(8)")
	private int idRaum;
	
	@OneToOne
	@JoinColumn(name = "idGebaeude")
	private Gebaeude gebaeude;
	
	@OneToOne
	@JoinColumn(name = "idNutzungskategorie")
	private Nutzungskategorie nutzungskategorie;
	
	@Column
	private String groesse;
	
	@Column
	private String bezeichnung;
	
	
	@OneToOne(mappedBy ="raum")
	private Reservation reservation;


	public int getIdRaum() {
		return idRaum;
	}


	public void setIdRaum(int idRaum) {
		this.idRaum = idRaum;
	}


	public Gebaeude getGebaeude() {
		return gebaeude;
	}


	public void setGebaeude(Gebaeude gebaeude) {
		this.gebaeude = gebaeude;
	}


	public String getGroesse() {
		return groesse;
	}


	public void setGroesse(String groesse) {
		this.groesse = groesse;
	}


	public String getBezeichnung() {
		return bezeichnung;
	}


	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
	
   
}
