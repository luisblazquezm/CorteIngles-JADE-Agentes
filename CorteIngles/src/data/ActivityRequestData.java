package data;

import java.util.Date;
import jade.core.AID;
import jade.core.Agent;

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
	 * @param client
	 * @param data
	 * @param city
	 * @param startDate
	 */
	public ActivityRequestData(AID client, String city, Date startDate) {
		super(client);
		this.city = city;
		this.startDate = startDate;
	}
	
	/**
	 * @param client
	 * @param data
	 * @param city
	 * @param startDate
	 */
	public ActivityRequestData(Agent client, String city, Date startDate) {
		super(client);
		this.city = city;
		this.startDate = startDate;
	}
	
	/**
	 * @param client
	 * @param data
	 * @param city
	 * @param startDate
	 */
	public ActivityRequestData() {
		super((AID)null);
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
