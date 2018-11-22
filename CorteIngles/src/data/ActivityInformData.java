package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jade.core.AID;
import jade.core.Agent;

public class ActivityInformData extends InformData {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private List<Activity> activities;
	
	/**
	 * @param server
	 * @param data
	 * @param activities
	 */
	public ActivityInformData(AID server, List<Activity> activities) {
		super(server);
		this.activities = activities;
	}
	
	/**
	 * @param server
	 * @param data
	 * @param activities
	 */
	public ActivityInformData(AID server, Activity... activities) {
		super(server);
		List<Activity> list = new ArrayList<>();
		list = Arrays.asList(activities);
		this.activities = list;
	}
	
	/**
	 * @param server
	 * @param data
	 * @param activities
	 */
	public ActivityInformData(Agent server, List<Activity> activities) {
		super(server);
		this.activities = activities;
	}
	
	/**
	 * @param server
	 * @param data
	 * @param activities
	 */
	public ActivityInformData(Agent server, Activity... activities) {
		super(server);
		List<Activity> list = new ArrayList<>();
		list = Arrays.asList(activities);
		this.activities = list;
	}
	
	/**
	 * @param server
	 * @param data
	 * @param activities
	 */
	public ActivityInformData() {
		super((AID)null);
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
	 * @return True if any activities were found, false otherwise
	 */
	public boolean activitiesWereFound() {
		if (this.activities == null || this.activities.size() == 0)
			return false;
		else
			return true;
	}
	
	

}
