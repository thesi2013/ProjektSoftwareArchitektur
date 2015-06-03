package controller;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import restfulService.EmployeeTO;
import business.Bean;
import business.ReservationData;
import entities.Nutzungskategorie;
import entities.Raum;
import entities.Reservation;

import java.io.Serializable;

@Named
@RequestScoped
public class Controller {
	
	private ReservationData resData= new ReservationData();
	private EmployeeTO employeeData = new EmployeeTO();
	private Reservation reservation = new Reservation();
	private HtmlDataTable reservationHtmlDataTable;
	private List<Reservation> allReservations;

	@EJB
	Bean bean;
	
	
	public String store(){
//		System.out.println("Controller - store() aufgerufen.");
//		System.out.println("Mitarbeiter " + resData.getEmployee().getId() + " ausgewählt.");
//		System.out.println("Nutzungskategorie " + resData.getNutzungskateID() + " ausgewählt.");
//		System.out.println("Datum von " + resData.getDatumVon());
//		System.out.println("Datum bis " + resData.getDatumBis());
//		System.out.println("Gebäude " + resData.getGebaeude().getIdGebaeude() + " ausgewählt");
		bean.addReservation(resData);
		return "confirmation.xhtml";
	}

	public List<Reservation> getAllReservations(){
		System.out.println("FEM - getAllReservations() - aufgerufen");
		
		return bean.loadAllReservations();
		
	}
	
	
	
	
	public ReservationData getResData() {
		return resData;
	}

	public void setResData(ReservationData resData) {
		this.resData = resData;
	}

	public EmployeeTO getEmployeData() {
		return employeeData;
	}

	public void setEmployeData(EmployeeTO employeData) {
		this.employeeData = employeData;
	}
	
	
	public Date getMinDate() {
		
		Calendar c = Calendar.getInstance();
		int diffToAdd = 15 - (c.getTime().getMinutes() % 15);
		c.add(Calendar.MINUTE, diffToAdd);		
		return c.getTime();
	}
	
	
	
	
	//Added from Sven
	
	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public HtmlDataTable getReservationHtmlDataTable() {
		return reservationHtmlDataTable;
	}

	public void setReservationHtmlDataTable(HtmlDataTable reservationHtmlDataTable) {
		this.reservationHtmlDataTable = reservationHtmlDataTable;
	}



    //Added from Sven
	private static final ArrayList<ReservationData> orderList = new ArrayList<ReservationData>();
	
	public void onRowEdit(RowEditEvent event) {
		
		Reservation reservation = new Reservation();
		reservation = (Reservation)event.getObject();
		System.out.println("RESERVIERT BIS: " + reservation.getReserviertBis());
		bean.updateReservation(reservation);
		FacesMessage msg = new FacesMessage("Reservation geändert");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
	
	
	
	
	public String prepareEditReservation()
    {
        this.reservation = (Reservation) this.reservationHtmlDataTable.getRowData();
        return "editReservation.xhtml";
    }	
	
    public String editReservation()
    {
        reservation = bean.updateReservation(reservation);
        allReservations = bean.loadAllReservations();
        return "listReservation";
    }
    
    public String deleteReservation()
    {
        this.reservation = (Reservation) this.reservationHtmlDataTable.getRowData();
        bean.deleteReservation(reservation);
        reservation=new Reservation();
        allReservations = bean.loadAllReservations();
        return "listReservation";
    }
	
}
