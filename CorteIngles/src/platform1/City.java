/**
 * 
 */
package platform1;

import java.util.ArrayList;
import java.util.List;


/**
 * @author mrhyd
 *
 */
public class City {
	
	private final String name;
	private List<Activity> listOfActivities;
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
	
	
	
	

}
