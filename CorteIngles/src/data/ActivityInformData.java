package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActivityInformData extends InformData {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * City in which activities are carried out
	 */
	private String cityName;
	
	/**
	 * List of activities carried out in city
	 */
	private List<Activity> activities;
	
	/**
	 * @param server Agent answering to request
	 * @param cityName City in which activities are carried out
	 * @param activities List of activities carried out in city
	 */
	public ActivityInformData(String server, String cityName, List<Activity> activities) {
		super(server);
		this.cityName = cityName;
		this.activities = activities;
	}
	
	/**
	 * @param server Agent answering to request
	 * @param cityName City in which activities are carried out
	 * @param activities Array of activities carried out in city
	 */
	public ActivityInformData(String server, String cityName, Activity... activities) {
		super(server);
		this.cityName = cityName;
		List<Activity> list = new ArrayList<>();
		list = Arrays.asList(activities);
		this.activities = list;
	}
	
	
	/**
	 * 
	 */
	public ActivityInformData() {
		super(null);
		this.activities = null;
	}

	/**
	 * @return the activities
	 */
	public List<Activity> getListOfActivities() {
		return activities;
	}

	/**
	 * @param activities the activities to set
	 */
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	
	/**
	 * @return the activities
	 */
	public Activity[] getArrayOfActivities() {
		Activity[] array = new Activity[activities.size()];
		array = activities.toArray(array);
		return array;
	}

	/**
	 * @param activities the activities to set
	 */
	public void setActivities(Activity[] activities) {
		this.activities = new ArrayList<>();
		for (Activity a : activities) {
			this.activities.add(a);
		}
	}
	
	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return True if any activities were found, false otherwise
	 */
	public boolean activitiesWereFound() {
		if (this.activities == null || this.activities.size() == 0)
			return false;
		else
			return true;
	}
}
