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
	private int id;
	
	@Column
	private String kategorie;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKategorie() {
		return kategorie;
	}

	public void setKategorie(String kategorie) {
		this.kategorie = kategorie;
	}
   
}
