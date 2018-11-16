package data;

import java.util.Date;

public class ActivityRequestData {
	
	/**
	 * City in which the activity is carried on
	 */
	private String city;
	
	/**
	 * Activity's start date
	 */
	private Date startDate;
	
	/**
	 * 
	 */
	public ActivityRequestData() {
		this.city = null;
		this.startDate = null;
	}

	/**
	 * @param city City in which the activity is carried on
	 * @param startDate Activity's start date
	 */
	public ActivityRequestData(String city, Date startDate) {
		this.city = city;
		this.startDate = startDate;
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
