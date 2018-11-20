/**
 * @author mrhyd
 * 
 * This class is a data-type class that represents an activity.
 * Activities are always associated to a city (City class is client).
 * 
 */

package data;

public class Activity {

	/**
	 * Name of the activity
	 */
	private String name;	
	/**
	 * Start and end dates of the activity
	 */
	private int[] scheduleDescription;
	
	/**
	 * @param name
	 * @param scheduleDescription
	 */
	public Activity(String name, int[] scheduleDescription) {
		super();
		this.name = name;
		this.scheduleDescription = scheduleDescription;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String activity) {
		this.name = activity;
	}
	/**
	 * @return the scheduleDescription
	 */
	public int[] getScheduleDescription() {
		return scheduleDescription;
	}
	/**
	 * @param scheduleDescription the scheduleDescription to set
	 */
	public void setScheduleDescription(int[] scheduleDescription) {
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
