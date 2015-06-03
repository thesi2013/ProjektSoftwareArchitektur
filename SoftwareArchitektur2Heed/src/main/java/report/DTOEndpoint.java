package report;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import business.Bean;
import entities.Reservation;

@RequestScoped
@Path("/ReportReservations")
public class DTOEndpoint {
	
	@EJB
	Bean bean;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List <Reservation> listAll() {
		
		List <Reservation>list = new LinkedList<Reservation>();	
		list = bean.loadAllReservations();
		
		return list;
	}

}
