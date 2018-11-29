/**
 * @author mrhyd
 * 
 * This is a fully static class, that emulates a database from which agents get information.
 * It stores the list of cities, which in turn contain lists of hotels and activities. 
 */

package data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import utilities.Debug;

/**
 * @author Luis Blázquez Miñambres y Samuel Gómez Sánchez
 *
 */
public class Data {

	/**
	 * List of cities in 'database'
	 */
	private static List<City> listOfCities;
	
	/**
	 * Number of cities to generate
	 */
	final static int NUMBER_OF_CITIES = 10;
	
	static {
		
		// Don't know what I am doing wrong so that duplicates keep coming up \_('-')_/
		
		listOfCities = Cities.randomList(NUMBER_OF_CITIES);

		Set<City> s = new TreeSet<>(new CityComparator());
	    s.addAll(listOfCities); // Ordered, no duplicates
	    listOfCities = new ArrayList<City>();
	    listOfCities.addAll(s);
		
		for (City city : listOfCities) {
			
			List<Hotel> listOfHotels = city.getListOfHotels();
			Set<Hotel> setOfHotels = new TreeSet<>(new HotelComparator()); // Ordered, no duplicates
			setOfHotels.addAll(listOfHotels);         
			listOfHotels = new ArrayList<Hotel>();
			listOfHotels.addAll(setOfHotels);
			city.setListOfHotels(listOfHotels);
			
			List<Activity> listOfActivities = city.getListOfActivities();
			Set<Activity> setOfActivities = new TreeSet<>(new ActivityComparator()); // Ordered, no duplicates
			setOfActivities.addAll(listOfActivities);         
			listOfActivities = new ArrayList<Activity>();
			listOfActivities.addAll(setOfActivities);
			city.setListOfActivities(listOfActivities);
			
		}
	}
	
	
	/**
	 * @param citiName Name of the city in which we want to look for activities
	 * @param day Day in which we want to carry out the activity
	 * @return List of activities which comply with the requirements (city and dates)
	 */
	public static List<Activity> getActivities(String cityName, Date day) {
		
		if (cityName == null){
			System.err.println("Data: getActivities: invalid cityName");
			return null;
		}
		
		if (day == null){
			System.err.println("Data: getActivities: invalid day");
			return null;
		}
		
		List<Activity> list = new ArrayList<>();
		
		City city = null;
		for (City c : listOfCities) {
			if (c.getName().equalsIgnoreCase(cityName))
				city = c;
		}
		
		if (city == null) {
			return list;
		} else {
			for (Activity a : city.getListOfActivities()) {
				if (a.getScheduleDescription()[0].equals(day)) {
					list.add(a);
				}
			}
		}
		
		return list;
	}


	/**
	 * @param reservationData
	 * @return
	 */
	public static boolean reservationRequestIsAvailable(ReservationRequestData reservationData){
		
		//String cityName = reservationData[PlatformUtils.SENDER_CITY_INDEX];
		//String hotelName = reservationData[PlatformUtils.SENDER_HOTEL_INDEX];
		//String departureDate = reservationData[PlatformUtils.SENDER_DEPARTURE_INDEX];
		//String returnDate = reservationData[PlatformUtils.SENDER_RETURN_INDEX];
		
		final String dateFormatString = "dd/MM/yyyy";
		final DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		
		if (reservationData == null){
			System.err.println("Data: invalid reservationData");
			return false;
		}
		
		String cityName = reservationData.getDestinationCity();
		String hotelName = reservationData.getDestinationHotel();
		String departureDate = dateFormat.format(reservationData.getStartDate());
		String returnDate = dateFormat.format(reservationData.getEndDate());
		
		String[] partsOfDate = departureDate.split("/");
		int departureDay = Integer.parseInt(partsOfDate[0]); // Gets only the day dd from dd/MM/yyyy

		partsOfDate = returnDate.split("/");
		int returnDay = Integer.parseInt(partsOfDate[0]);
			
		City city = null;
		for (City c : listOfCities) {
			if (c.getName().equalsIgnoreCase(cityName))
				city = c;
		}
		
		Hotel hotel = null;
		if (city == null) {
			return false;
		} else {
			
			for (Hotel h : city.getListOfHotels()) {
				if (h.getName().equalsIgnoreCase(hotelName)) {
					hotel = h;
				}
			}
			
		}
		
		if (hotel == null) {
			return false;
		} else {
			try {
				return hotel.addCustomer(departureDay, returnDay, 1);
			} catch (Exception e) {
				Debug.message("Data: reservationRequestIsAvailable");
				e.printStackTrace();
			}
		}
		
		return false;
	   
	}

	/**
	 * @return the list of cities
	 */
	public static List<City> getListOfCities() {
		return listOfCities;
	}
	
	/**
	 * @return the list of city names
	 */
	public static List<String> getListOfCityNames() {
		List<String> list = new ArrayList<>();
		for (City c : listOfCities) {
			list.add(c.getName());
		}
		return list;
	}
	
	/**
	 * @return the array of city names
	 */
	public static String[] getArrayOfCityNames() {
		String[] array = new String[listOfCities.size()];
		array = getListOfCityNames().toArray(array);
		return array;
	}
	
	/**
	 * @param cityName The name in which the hotels must be searched
	 * @return The list of hotels of the selected city
	 */
	public static List<Hotel> getListOfHotels(String cityName) {

		City city = null;
		
		if (cityName == null)
			return null;
		
		for (City c : listOfCities) {
			if (c.getName().equalsIgnoreCase(cityName)) {
				city = c;
				break;
			}
		}
		
		if (city == null)
			return null;
		
		return city.getListOfHotels();
	}
	
	/**
	 * @param cityName The name in which the hotel names must be searched
	 * @return The list of hotel names of the selected city
	 */
	public static List<String> getListOfHotelNames(String cityName) {
		List<String> list = new ArrayList<>();
		for (Hotel c : Data.getListOfHotels(cityName)) {
			list.add(c.getName());
		}
		return list;
	}
	
	/**
	 * @param cityName The name in which the hotel names must be searched
	 * @return The array of hotel names of the selected city
	 */
	public static String[] getArrayOfHotelNames(String cityName) {
		
		List<String> list = Data.getListOfHotelNames(cityName);
		String[] array = new String[list.size()];
		array = list.toArray(array);
		return array;
	}
}
