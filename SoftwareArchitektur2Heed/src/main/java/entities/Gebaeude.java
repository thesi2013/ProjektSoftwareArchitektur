package entities;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Gebaeude
 *
 */
@Entity

public class Gebaeude implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idGebaeude;
	
	@Column
	private String name;
	
	@Column
	private String bezeichnung;
	
	@Column
	private String strasse;
	
	@Column
	private int plz;
	
	@Column
	private String ort;
	
	@OneToOne(mappedBy ="gebaeude")
	private Raum raum;

	
	
	public int getIdGebaeude() {
		return idGebaeude;
	}

	public void setIdGebaeude(int idGebaeude) {
		this.idGebaeude = idGebaeude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}
	
	
	
	
	
}
