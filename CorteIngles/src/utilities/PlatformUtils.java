/**
 * 
 * @author mrhyd
 * 
 * Class that stores information used by all agents in the platform.
 * It's program's configuration data, not user-valuable information.
 */

package utilities;

import jade.core.AID;
import jade.core.Agent;

/**
 * @author mrhyd
 *
 */
public class PlatformUtils {
	
	// Agents in platform
	public static final String USER_AGENT = "user";
	public static final String CORTE_INGLES_AGENT = "corte-ingles";
	public static final String RESERVATION_AGENT = "reservation";
	public static final String ACTIVITY_AGENT = "activity";
	
	// Agents' info
	private static AID USER_AID= null;
	private static AID CORTE_INGLES_AID = null;
	private static AID RESERVATION_AID = null;
	private static AID ACTIVITY_AID = null;
	/*
	public static String USER_ALIAS = "user-agent";
	public static String RESERVATION_ALIAS = "reservation-agent";
	public static String ACTIVITY_ALIAS = "activity-agent";
	public static String CORTE_INGLES_ALIAS = "corte-ingles-agent";
	*/
	
	// Services' names and types
	public static final String RETRIEVE_ACTIVITY_SER = "retrieve-activity";
	public static final String MAKE_RESERVATION_SER = "make-reservation";
	public static final String HANDLE_RESERVATION_SER = "handle-reservation-request";
	public static final String HANDLE_ACTIVITY_SER = "handle-activity-request";
	public static final String HANDLE_USER_REQUEST_SER = "handle-user-request";
	
	/*
	// Delimiter for message content
	public static final String DELIMITER = "#";
	public static final String ACTIVITIES_DELIMITER = "*";
	
	// Type of message
	public static final String ACTIVITY_MESSAGE = "activity-message";
	public static final String RESERVATION_MESSAGE = "reservation-message";
	*/
	
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
	public static final int MAX_ROOMS_IN_HOTEL = 5;
	
	/**
	 * @param agent
	 * @param role
	 * @throws Exception
	 */
	public static void registerAgentInPlatform(Agent agent, String role) throws Exception {
		
		Exception exception = new Exception("PlatformUtils: registerAgentInPlatform: agent's registered in this platform should follow one of the following roles:\n"
								            + "\t> PlatformUtils.USER_AGENT\n"
											+ "\t> PlatformUtils.CORTE_INGLES_AGENT\n"
								            + "\t> PlatformUtils.RESERVATION_AGENT\n"
											+ "\t> PlatformUtils.ACTIVITY_AGENT\n");
		
		if (agent == null)
			System.err.println("PlatformUtils: registerAgentInPlatform: no agent specified. Please specify an agent or delete call to method");
		
		if (role == null)
			throw exception;
		
		switch(role) {
		case PlatformUtils.USER_AGENT:
			PlatformUtils.USER_AID = agent.getAID();
		break;
		case PlatformUtils.CORTE_INGLES_AGENT:
			PlatformUtils.CORTE_INGLES_AID = agent.getAID();
			break;
		case PlatformUtils.RESERVATION_AGENT:
			PlatformUtils.RESERVATION_AID = agent.getAID();
			break;
		case PlatformUtils.ACTIVITY_AGENT:
			PlatformUtils.ACTIVITY_AID = agent.getAID();
			break;
		default:
			throw exception;
		}
	}
	
	/**
	 * @param role
	 * @return
	 */
	public static String getLocalName(String role) {
		
		String errorMessage1 = "PlatformUtils: getLocalName: agent's registered in this platform should follow one of the following roles:\n"
								            + "\t> PlatformUtils.USER_AGENT\n"
											+ "\t> PlatformUtils.CORTE_INGLES_AGENT\n"
								            + "\t> PlatformUtils.RESERVATION_AGENT\n"
											+ "\t> PlatformUtils.ACTIVITY_AGENT\n";
		String errorMessage2 = "PlatformUtils: getLocalName: no agent registered for that role";
		
		try {
			switch(role) {
			case PlatformUtils.USER_AGENT:
				return PlatformUtils.USER_AID.getLocalName();
			case PlatformUtils.CORTE_INGLES_AGENT:
				return PlatformUtils.CORTE_INGLES_AID.getLocalName();
			case PlatformUtils.RESERVATION_AGENT:
				return PlatformUtils.RESERVATION_AID.getLocalName();
			case PlatformUtils.ACTIVITY_AGENT:
				return PlatformUtils.ACTIVITY_AID.getLocalName();
			default:
				System.err.println(errorMessage1);
				return null;
			}
		} catch (NullPointerException e) {
			System.err.println(errorMessage2 +": '" + role + "'");
			return null;
		}
	}
	
	/**
	 * @param role
	 * @return
	 */
	public static AID getAid(String role) {
		
		String errorMessage1 = "PlatformUtils: getAid: agent's registered in this platform should follow one of the following roles:\n"
					            + "\t> PlatformUtils.USER_AGENT\n"
								+ "\t> PlatformUtils.CORTE_INGLES_AGENT\n"
					            + "\t> PlatformUtils.RESERVATION_AGENT\n"
								+ "\t> PlatformUtils.ACTIVITY_AGENT\n";
		String errorMessage2 = "PlatformUtils: getAid: no agent registered for that role";
		
		switch(role) {
		case PlatformUtils.USER_AGENT:
			if (PlatformUtils.USER_AID != null)
				return PlatformUtils.USER_AID;
			else
				break;
		case PlatformUtils.CORTE_INGLES_AGENT:
			if (PlatformUtils.CORTE_INGLES_AID != null)
				return PlatformUtils.CORTE_INGLES_AID;
			else
				break;
		case PlatformUtils.RESERVATION_AGENT:
			if (PlatformUtils.RESERVATION_AID != null)
				return PlatformUtils.RESERVATION_AID;
			else
				break;
		case PlatformUtils.ACTIVITY_AGENT:
			if (PlatformUtils.ACTIVITY_AID != null)
				return PlatformUtils.ACTIVITY_AID;
			else
				break;
		default:
			System.err.println(errorMessage1);
		}
		
		System.err.println(errorMessage2 +": '" + role + "'");
		return null;
	}

}
