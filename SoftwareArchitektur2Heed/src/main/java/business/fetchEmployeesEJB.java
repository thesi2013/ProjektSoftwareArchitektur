package business;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.util.GenericType;

import restfulService.EmployeeTO;

@Stateless
public class fetchEmployeesEJB {

	public List<EmployeeTO> loadEmployeeName(){
		List <EmployeeTO> employees = new LinkedList <EmployeeTO>();
			try {
				ClientRequest request = new ClientRequest("http://employeemanager-esaeservice.rhcloud.com/rs/Employees");
				//request.accept("application/json");
				
				ClientResponse <List <EmployeeTO>> response = request.get(new GenericType<List<EmployeeTO>>(){}) ;
				
				if (response.getStatus() != 200){
					throw new RuntimeException("Failed : HTTP error code : " 
							+ response.getStatus());
				}		
				
				
				
				employees = response.getEntity();			
				
				System.out.println(Arrays.toString(employees.toArray()));
				
				
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			System.out.println ("fetchEmployeesEJB - Mitarbeiter geladen");
			return employees;			
		}

}
