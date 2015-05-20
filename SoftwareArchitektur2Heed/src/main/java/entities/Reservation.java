package entities;

import java.io.Serializable;

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
	
	@Column (nullable = false)
	private String day;

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
	
	
   
}