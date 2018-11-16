package messages;

import data.ActivityInformData;
import data.ActivityRequestData;
import data.ReservationInformData;
import data.ReservationRequestData;
import jade.core.AID;
import utilities.PlatformUtils;

public class Messages {
			
	/**
	 * @param data
	 * @return
	 */
	public static MessageContent createReservationRequestMessageContent(ReservationRequestData data) {

		AID aid = null;
		
		return new MessageContent(
				PlatformUtils.HANDLE_RESERVATION_SER,
				data,
				aid);
	}
	
	/**
	 * @param data
	 * @return
	 */
	public static MessageContent createActivityRequestMessageContent(ActivityRequestData data) {
		
		AID aid = null;
		
		return new MessageContent(
				PlatformUtils.HANDLE_ACTIVITY_SER,
				data,
				aid);
	}
	
	/**
	 * @param requestMessageContent
	 * @param data
	 * @return
	 */
	public static ResponseMessageContent createReservationInformMessageContent(
			MessageContent requestMessageContent,
			ReservationInformData data) {
				
		AID aid = null;
		
		return new ResponseMessageContent(
				PlatformUtils.HANDLE_RESERVATION_SER,
				data,
				requestMessageContent.getRequester(),
				aid);
		
		
	}

	/**
	 * @param requestMessageContent
	 * @param data
	 * @return
	 */
	public static ResponseMessageContent createActivityInformMessageContent(
			MessageContent requestMessageContent,
			ActivityInformData data) {
	
		AID aid = null;
		
		return new ResponseMessageContent(
				PlatformUtils.HANDLE_ACTIVITY_SER,
				data,
				requestMessageContent.getRequester(),
				aid);
	}
}
