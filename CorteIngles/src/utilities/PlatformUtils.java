/**
 * 
 * @author mrhyd
 * 
 * Class that stores information used by all agents in the platform.
 * It's program's configuration data, not user-valuable information.
 */

package utilities;

import jade.content.lang.sl.SLCodec;

/**
 * @author Luis Blázquez Miñambres y Samuel Gómez Sánchez
 *
 */
public class PlatformUtils {
	
	// Agents in platform
	public static final String USER_AGENT = "user";
	public static final String CORTE_INGLES_AGENT = "corte-ingles";
	public static final String RESERVATION_AGENT = "reservation";
	public static final String ACTIVITY_AGENT = "activity";
	
	// Services' names and types
	public static final String RETRIEVE_ACTIVITY_SER = "retrieve-activity";
	public static final String MAKE_RESERVATION_SER = "make-reservation";
	public static final String HANDLE_RESERVATION_SER = "handle-reservation-request";
	public static final String HANDLE_ACTIVITY_SER = "handle-activity-request";
	public static final String HANDLE_USER_REQUEST_SER = "handle-user-request";
	
	// Packets' associated data
	public static final String PLATFORM_ONTOLOGY = "corte-ingles-ontology";
	public static final String PLATFORM_LANGUAGE = new SLCodec().getName();
		
	// Availability of reservation
	public static final String RESERVATION_AVAILABLE = "available";
	public static final String RESERVATION_NOT_AVAILABLE = "not-available";
	
	// Indexes from reservation message object
	public static final int SENDER_CITY_INDEX = 0;
	public static final int SENDER_HOTEL_INDEX = 1;
	public static final int SENDER_DEPARTURE_INDEX = 2;
	public static final int SENDER_RETURN_INDEX = 3;
	
	// Indexes from activity message object
	//public static final int SENDER_CITY_INDEX = 0;
	public static final int SENDER_START_OF_ACTIVITY_INDEX = 0;
	public static final int SENDER_END_OF_ACTIVITY_INDEX = 1;
	
	// Indexes from receiving message the userAgent has to show the real user (RESERVATION)
	public static final int RECEIVER_CITY_INDEX = 0;
	public static final int RECEIVER_HOTEL_INDEX = 1;
	public static final int RECEIVER_DEPARTURE_INDEX = 2;
	public static final int RECEIVER_RETURN_INDEX = 3;
	public static final int RECEIVER_AVAILABILITY_INDEX = 4;
	
	// Indexes from receiving message the userAgent has to show the real user (ACTIVITY)
	//public static final int RECEIVER_TYPE_INDEX = 0;
	public static final int RECEIVER_ACTIVITY_INDEX = 0;
	public static final int RECEIVER_ACTIVITY_CITY_INDEX = 1;
	public static final int RECEIVER_START_OF_ACTIVITY_INDEX = 2;
	public static final int RECEIVER_END_OF_ACTIVITY_INDEX = 3;
	
	// Indexes from activity message object
	// cityIndex = 0
	public static final int SCHEDULE_DESCRIPTION_INDEX = 1;
	
	// Others
	public static final int DAYS_OF_MAY = 31;
}
