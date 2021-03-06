package restfulService;


import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.util.GenericType;


public class RestfulClient {

	@SuppressWarnings("deprecation")
	public List<EmployeeTO> loadEmployees() {
		
		List <EmployeeTO> employees = new LinkedList <EmployeeTO>();
		
		try {
			ClientRequest request = new ClientRequest("http://employeemanager-esaeservice.rhcloud.com/rs/Employees");
			//request.accept("application/json");
			
			ClientResponse <List <EmployeeTO>> response = request.get(new GenericType<List<EmployeeTO>>(){}) ;
			
			if (response.getStatus() != 200){
				throw new RuntimeException("Failed : HTTP error code : " 
						+ response.getStatus());
			}		
			
			System.out.println ("laden funktioniert!");
			
			employees = response.getEntity();			
			
			Iterator <EmployeeTO> iter = employees.iterator();
			while (iter.hasNext()){
				System.out.println (iter.next());
			}
			
			
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		
		return employees;
		
	}
}
