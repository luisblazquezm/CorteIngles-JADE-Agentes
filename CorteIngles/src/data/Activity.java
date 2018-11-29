/**
 * @author mrhyd
 * 
 * This class is a data-type class that represents an activity.
 * Activities are always associated to a city (City class is client).
 * 
 */

package data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class Activity implements Serializable, Comparable<Activity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Name of the activity
	 */
	private String name;
	
	/**
	 * Start and end dates of the activity
	 */
	private Date[] scheduleDescription;
	
	/**
	 * @param name
	 * @param scheduleDescription
	 */
	public Activity(String name, Date[] scheduleDescription) {
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
	public Date[] getScheduleDescription() {
		return scheduleDescription;
	}
	/**
	 * @param scheduleDescription the scheduleDescription to set
	 */
	public void setScheduleDescription(Date[] scheduleDescription) {
		this.scheduleDescription = scheduleDescription;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {

	    if(object instanceof Activity)
	    {
	    	Activity temp = (Activity) object;
	        if(this.name.equals(temp.getName()) && Arrays.equals(this.scheduleDescription, temp.getScheduleDescription()));
	            return true;
	    }
	    
	    return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
	    return (this.name.hashCode() + this.scheduleDescription.hashCode());        
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Activity activity) {

		if (activity == null) {
			return 1;
		} else {
			return this.name.compareToIgnoreCase(activity.getName());
		}
	}
    
}
