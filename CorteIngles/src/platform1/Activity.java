package platform1;

import utilities.Activities;

public class Activity {

	private String name;
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
