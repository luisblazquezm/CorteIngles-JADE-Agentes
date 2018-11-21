package messages;

import data.ActivityInformData;
import data.ActivityRequestData;
import data.ReservationInformData;
import data.ReservationRequestData;
import jade.core.Agent;
import utilities.PlatformUtils;

public class Messages {
			
	/**
	 * @param data Request data
	 * @param requester Agent asking for service
	 * @return
	 */
	public static MessageContent createReservationRequestMessageContent(ReservationRequestData data, Agent requester) {

		return new MessageContent(
				PlatformUtils.HANDLE_RESERVATION_SER,
				data,
				requester.getAID());
	}
	
	/**
	 * @param data Request data
	 * @param requester Agent asking for service
	 * @return
	 */
	public static MessageContent createActivityRequestMessageContent(ActivityRequestData data, Agent requester) {
		
		return new MessageContent(
				PlatformUtils.HANDLE_ACTIVITY_SER,
				data,
				requester.getAID());
	}
	
	/**
	 * @param data Response data
	 * @param server Agent who has answered to request
	 * @return
	 */
	public static MessageContent createReservationInformMessageContent(ReservationInformData data, Agent server) {

		return new MessageContent(
				PlatformUtils.HANDLE_RESERVATION_SER,
				data,
				server.getAID());
		
		
	}

	/**
	 * @param data Response data
	 * @param server Agent who has answered to request
	 * @return
	 */
	public static MessageContent createActivityInformMessageContent(ActivityInformData data, Agent server) {

		return new MessageContent(
				PlatformUtils.HANDLE_ACTIVITY_SER,
				data,
				server.getAID());
	}
}
