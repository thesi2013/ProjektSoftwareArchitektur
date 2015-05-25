package restfulService;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties ("pathToImage")
public class EmployeeTO{
	
	private int id;
	private String firstname;
	private String lastname;
	private String pathToImage;
	
	@Override
	public String toString() {
		return "Employee [id = " + id + ", vorname = " + firstname + ", " +
				"nachname = " + lastname + "]";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getPathToImage() {
		return pathToImage;
	}

	public void setPathToImage(String pathToImage) {
		this.pathToImage = pathToImage;
	}
	
	

}
