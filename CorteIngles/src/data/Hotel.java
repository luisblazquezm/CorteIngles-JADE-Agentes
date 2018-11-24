/**
 * @author mrhyd
 * 
 * This class represents a hotel, which has a name and a list of rooms, 
 * 
 */

package data;

import java.io.Serializable;
import utilities.Debug;

/**
 * @author mrhyd
 *
 */
public class Hotel implements Serializable, Comparable<Hotel> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *  Hotel name
	 */
	private final String name;
	
	/**
	 * List of rooms. Array index is room number, content is size of empty room.
	 */
	private int[] occupationCalendar;
	
	/**
	 * @param name
	 * @param occupationCalendar
	 */
	public Hotel(String name, int[] occupationCalendar) {
		super();
		this.name = name;
		this.occupationCalendar = occupationCalendar;
	}
	/**
	 * 
	 */
	public Hotel() {
		super();
		this.name = null;
		this.occupationCalendar = null;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the occupationCalendar
	 */
	public int[] getOccupationCalendar() {
		return occupationCalendar;
	}
	/**
	 * @param occupationCalendar the occupationCalendar to set
	 */
	public void setOccupationCalendar(int[] occupationCalendar) {
		this.occupationCalendar = occupationCalendar;
	}
	/**
	 * @param roomNumber Number of the room to be reserved
	 * @param numberOfCustomers Number of customers to be added
	 */
	public boolean addCustomer(int departureDate, int returnDate, int numberOfCustomers)
	{
		boolean customerCanBeAdded = true;
		
		if (departureDate == 0 || returnDate == 0 || numberOfCustomers == 0) {
			Debug.formattedMessage("%naddCustomer: invalid parameters%n");
		} else {
			for (int i = departureDate ; i <= returnDate ; i++) {
				if (this.occupationCalendar[i] < numberOfCustomers) {
					Debug.formattedMessage("%naddCustomer: room not available%n");
					customerCanBeAdded = false;
				}
			}
			
			if (customerCanBeAdded){
				Debug.formattedMessage("%nRoom available for days passed as parameters%n");
				for (int i = departureDate ; i <= returnDate ; i++) 
					this.occupationCalendar[i] -= numberOfCustomers;
					
				return true;
			}
		}
		
		return false;
		
	}
	
	@Override
	public boolean equals(Object object) {

	    if(object instanceof Hotel)
	    {
	    	Hotel temp = (Hotel) object;
	        if(this.name.equals(temp.getName()))
	            return true;
	    }
	    
	    return false;
	}
	
	@Override
	public int hashCode() {
	    return (this.name.hashCode() + this.occupationCalendar.hashCode());        
	}

	@Override
	public int compareTo(Hotel hotel) {

		if (hotel == null) {
			return 1;
		} else {
			return this.name.compareToIgnoreCase(hotel.getName());
		}
	}
}
