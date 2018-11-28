package data;

import java.util.Date;

/**
 * @author Luis Bl�zquez Mi�ambres y Samuel G�mez S�nchez
 *
 */
public class ReservationRequestData extends RequestData{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * City of destination
	 */
	private String destinationCity;
	
	/**
	 * Hotel of destination in city
	 */
	private String destinationHotel;
	
	/**
	 * Reservation's start date
	 */
	private Date startDate;
	
	/**
	 * Reservation's end date
	 */
	private Date endDate;
	
	/**
	 * @param client Requester of the service
	 * @param data Service's associated data
	 * @param destinationCity Destination city
	 * @param destinationHotel Destination hotel
	 * @param startDate Reservation's start date
	 * @param endDate Reservation's end date
	 */
	public ReservationRequestData(String client, String destinationCity, String destinationHotel,
			Date startDate, Date endDate) {
		super(client);
		this.destinationCity = destinationCity;
		this.destinationHotel = destinationHotel;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	
	/**
	 * 
	 */
	public ReservationRequestData() {
		super(null);
		this.destinationCity = null;
		this.destinationHotel = null;
		this.startDate = null;
		this.endDate = null;
	}
	
	/**
	 * @return the destinationCity
	 */
	public String getDestinationCity() {
		return destinationCity;
	}
	/**
	 * @param destinationCity the destinationCity to set
	 */
	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}
	/**
	 * @return the destinationHotel
	 */
	public String getDestinationHotel() {
		return destinationHotel;
	}
	/**
	 * @param destinationHotel the destinationHotel to set
	 */
	public void setDestinationHotel(String destinationHotel) {
		this.destinationHotel = destinationHotel;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
