package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActivityInformData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private List<Activity> activities;
	
	/**
	 *
	 */
	public ActivityInformData() {
		this.activities = null;
	}
	
	/**
	 * @param activities
	 */
	public ActivityInformData(List<Activity> activities) {
		this.activities = activities;
	}

	/**
	 * @param activities
	 */
	public ActivityInformData(Activity... activities) {
		this.activities = new ArrayList<>();
		for (Activity a : activities) {
			this.activities.add(a);
		}
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
	 * @return True if any activities were found, false otherwise
	 */
	public boolean activitiesWereFound() {
		if (this.activities == null || this.activities.size() == 0)
			return false;
		else
			return true;
	}
	
	

}
