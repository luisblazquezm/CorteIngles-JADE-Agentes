package platform1;

import utilities.Activities;

public class Activity {

	private String activity;
	private int[] scheduleDescription;
	
	/**
	 * @param activity
	 * @param scheduleDescription
	 */
	public Activity(String activity, int[] scheduleDescription) {
		super();
		this.activity = activity;
		this.scheduleDescription = scheduleDescription;
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
