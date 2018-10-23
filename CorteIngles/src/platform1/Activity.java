package platform1;

import utilities.Activities;

public class Activity {

	private String city;
	private String activity;
	private int startActivityDay;
	private int endActivityDay;
	
	/**
	 * @param city
	 * @param activity
	 * @param scheduleDescription
	 */
	public Activity(String city, String activity, int startActivityDay, int endActivityDay) {
		super();
		this.city = city;
		this.activity = activity;
		this.startActivityDay = startActivityDay;
		this.endActivityDay = endActivityDay;
	}
	/**
	 * 
	 */
	public Activity() {
		super();
		this.city = "Default";
		this.activity = "Default";
		this.startActivityDay = 1;
		this.endActivityDay = 1;
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

	public int getStartActivityDay() {
		return startActivityDay;
	}
	
	public int getEndActivityDay() {
		return endActivityDay;
	}
	
	public void setStartActivityDay(int startActivityDay) {
		this.startActivityDay = startActivityDay;
	}
	
	public void setEndActivityDay(int endActivityDay) {
		this.endActivityDay = endActivityDay;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Activities.stringDescription(this);
	}
	
    
}
