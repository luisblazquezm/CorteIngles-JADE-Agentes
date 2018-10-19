package platform1;

import java.util.Date;

import utilities.Activities;

public class Activity {

	private String city;
	private String activity;
	private Date scheduleDescription;
	
	/**
	 * @param city
	 * @param activity
	 * @param scheduleDescription
	 */
	public Activity(String city, String activity, Date scheduleDescription) {
		super();
		this.city = city;
		this.activity = activity;
		this.scheduleDescription = scheduleDescription;
	}
	/**
	 * 
	 */
	public Activity() {
		super();
		this.city = "Default";
		this.activity = "Default";
		this.scheduleDescription = null;
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
	 * @return the activity
	 */
	public String getActivity() {
		return activity;
	}
	/**
	 * @param activity the activity to set
	 */
	public void setActivity(String activity) {
		this.activity = activity;
	}
	/**
	 * @return the scheduleDescription
	 */
	public String getScheduleDescription() {
		return scheduleDescription;
	}
	/**
	 * @param scheduleDescription the scheduleDescription to set
	 */
	public void setScheduleDescription(String scheduleDescription) {
		this.scheduleDescription = scheduleDescription;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Activities.stringDescription(this);
	}
	
    
}
