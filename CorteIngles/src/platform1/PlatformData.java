/**
 * 
 */
package platform1;

/**
 * @author mrhyd
 *
 */
public class PlatformData {

	// Agents' aliases
	public static final String USER_ALIAS = "user-agent";
	public static final String RESERVATION_ALIAS = "reservation-agent";
	public static final String ACTIVITY_ALIAS = "activity-agent";
	public static final String CORTE_INGLES_ALIAS = "corte-ingles-agent";
	
	// Services' names and types
	/**
	 * Service provided by ActivityAgent
	 */
	public static final String RETRIEVE_ACTIVITY_SER = "retrieve-activity";
	
	/**
	 * Service provided by ReservationAgent
	 */
	public static final String MAKE_RESERVATION_SER = "make-reservation";
	
	/**
	 * Services provided by CorteInglesAgent
	 */
	public static final String HANDLE_RESERVATION_SER = "handle-reservation-request";
	public static final String HANDLE_ACTIVITY_SER = "handle-activity-request";
	
	/**
	 * Service provided by UserAgent
	 */
	public static final String HANDLE_USER_REQUEST_SER = "handle-user-request";
}
