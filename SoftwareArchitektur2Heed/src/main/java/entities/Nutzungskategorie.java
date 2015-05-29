package entities;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Nutzungskategorie
 *
 */
@Entity

public class Nutzungskategorie implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition="TINYINT(8)")
	private int idNutzungskategorie;
	
	@Column
	private String bezeichnung;
	
	/*@OneToOne(mappedBy ="nutzungskategorie")
	private Raum raum;*/

	
	
	public int getIdNutzungskategorie() {
		return idNutzungskategorie;
	}

	public void setIdNutzungskategorie(int idNutzungskategorie) {
		this.idNutzungskategorie = idNutzungskategorie;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
	
	
}
