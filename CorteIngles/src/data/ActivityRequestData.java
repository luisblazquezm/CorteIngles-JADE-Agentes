package data;

import java.util.Date;

/**
 * @author Luis Blázquez Miñambres y Samuel Gómez Sánchez
 *
 */
public class ActivityRequestData extends RequestData{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * City in which the activity is carried on
	 */
	private String city;
	
	/**
	 * Activity's start date
	 */
	private Date startDate;
	
	/**
	 * @param client Requester of the service (Agent role as defined in PlatformUtils)
	 * @param city Activity's city
	 * @param startDate Activity's date
	 */
	public ActivityRequestData(String client, String city, Date startDate) {
		super(client);
		this.city = city;
		this.startDate = startDate;
	}
	
	/**
	 * 
	 */
	public ActivityRequestData() {
		super(null);
		this.city = null;
		this.startDate = null;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
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
}
