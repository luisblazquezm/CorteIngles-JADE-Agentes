/**
 * 
 */
package platform1;

/**
 * @author mrhyd
 *
 */
public class Hotel {
	
	private final String name;
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
	public void addCustomer(int roomNumber, int numberOfCustomers)
		throws Exception 
	{
		if (this.occupationCalendar[roomNumber] >= numberOfCustomers)
			this.occupationCalendar[roomNumber] -= numberOfCustomers;
		else
			throw new Exception("addCustomer: room not available");
	}
}
