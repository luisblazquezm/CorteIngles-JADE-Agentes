/**
 * 
 */
package platform1;

/**
 * @author mrhyd
 *
 */
public class PlatformData {

	// Agents' alias
	public static final String USER_ALIAS = "user-agent";
	public static final String RESERVATION_ALIAS = "reservation-agent";
	public static final String ACTIVITY_ALIAS = "activity-agent";
	public static final String CORTE_INGLES_ALIAS = "corte-ingles-agent";
	
	// Services' names and types
	public static final String RETRIEVE_ACTIVITY_SER = "retrieve-activity";
	public static final String MAKE_RESERVATION_SER = "make-reservation";
	public static final String HANDLE_RESERVATION_SER = "handle-reservation-request";
	public static final String HANDLE_ACTIVITY_SER = "handle-activity-request";
	public static final String HANDLE_USER_REQUEST_SER = "handle-user-request";
	
	// Delimiter for message content
	public static final String DELIMITER = "#";
	
	// Type of message
	public static final String ACTIVITY_MESSAGE = "activity-message";
	public static final String RESERVATION_MESSAGE = "reservation-message";
	
	// Availability of reservation
	public static final String RESERVATION_AVAILABLE = "available";
	public static final String RESERVATION_NOT_AVAILABLE = "not-available";
}
