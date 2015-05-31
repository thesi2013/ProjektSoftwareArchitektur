package controller;

import java.util.LinkedList;
import java.util.List;

import business.fetchEmployeesEJB;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import restfulService.EmployeeTO;

@Named
@RequestScoped
public class fetchEmployeesController {
	
	@EJB
	private fetchEmployeesEJB fetchEmployeesEJB;
	private EmployeeTO employeeData = new EmployeeTO();


	public List<EmployeeTO> getCheckEmployeeName(){
		System.out.println("fetchEmployeesController - getCheckEmployeeName() aufgerufen");
		return fetchEmployeesEJB.loadEmployeeName();
	}
	
	public EmployeeTO getEmployeData() {
		return employeeData;
	}

	public void setEmployeData(EmployeeTO employeData) {
		this.employeeData = employeData;
	}

}
