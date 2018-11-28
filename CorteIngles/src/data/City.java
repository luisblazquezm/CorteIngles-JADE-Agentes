/**
 * @author mrhyd
 * 
 * This class represents a city, which contains a list of hotels and activities.
 * 
 */

package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Luis Blázquez Miñambres y Samuel Gómez Sánchez
 *
 */
public class City implements Serializable, Comparable<City> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * City's name
	 */
	private final String name;
	
	/**
	 * List of available activities in this city
	 */
	private List<Activity> listOfActivities;
	
	/**
	 * List of hotels in this city
	 */
	private List<Hotel> listOfHotels;
	
	/**
	 * @param name
	 * @param listOfActivities
	 * @param listOfHotels
	 */
	public City(String name, List<Hotel> listOfHotels, List<Activity> listOfActivities) {
		super();
		this.name = name;
		this.listOfActivities = listOfActivities;
		this.listOfHotels = listOfHotels;
	}
	
	/**
	 * @param name
	 */
	public City(String name) {
		super();
		this.name = name;
		this.listOfActivities = new ArrayList<>();
		this.listOfHotels = new ArrayList<>();
	}

	/**
	 * @return the listOfActivities
	 */
	public List<Activity> getListOfActivities() {
		return listOfActivities;
	}

	/**
	 * @param listOfActivities the listOfActivities to set
	 */
	public void setListOfActivities(List<Activity> listOfActivities) {
		this.listOfActivities = listOfActivities;
	}

	/**
	 * @return the listOfHotels
	 */
	public List<Hotel> getListOfHotels() {
		return listOfHotels;
	}

	/**
	 * @param listOfHotels the listOfHotels to set
	 */
	public void setListOfHotels(List<Hotel> listOfHotels) {
		this.listOfHotels = listOfHotels;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {

	    if(object instanceof City)
	    {
	    	City temp = (City) object;
	        if(this.name.equals(temp.getName()))
	            return true;
	    }
	    
	    return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
	    return (this.name.hashCode() + this.listOfHotels.hashCode() + this.listOfActivities.hashCode());        
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(City city) {

		if (city == null) {
			return 1;
		} else {
			return this.name.compareToIgnoreCase(city.getName());
		}
	}
}
