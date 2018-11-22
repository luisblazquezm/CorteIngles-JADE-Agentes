package messages;

import data.ActivityInformData;
import data.ActivityRequestData;
import data.ReservationInformData;
import data.ReservationRequestData;
import data.ServiceData;
import jade.core.Agent;
import utilities.PlatformUtils;

public class Messages {
		
	/**
	 * @param data
	 * @param service
	 * @return
	 */
	private static ServiceDataPacket createServiceDataPacket(ServiceData data, String service) {

		return new ServiceDataPacket(
				service, 
				data, 
				(Agent)null);
	}
	
	/**
	 * @param data
	 * @return
	 */
	public static ServiceDataPacket createReservationRequestServiceDataPacket(ReservationRequestData data) {

		ServiceDataPacket serviceDataPacket = createServiceDataPacket(data, PlatformUtils.HANDLE_RESERVATION_SER);
		serviceDataPacket.setRequester(data.getClient());
		return serviceDataPacket;
	}
	
	/**
	 * @param data
	 * @return
	 */
	public static ServiceDataPacket createActivityRequestServiceDataPacket(ActivityRequestData data) {
		
		ServiceDataPacket serviceDataPacket = createServiceDataPacket(data, PlatformUtils.HANDLE_ACTIVITY_SER);
		serviceDataPacket.setRequester(data.getClient());
		return serviceDataPacket;
	}
	
	/**
	 * @param requestPacket
	 * @param data
	 * @return
	 */
	public static ServiceDataPacket createReservationInformServiceDataPacket(ServiceDataPacket requestPacket, ReservationInformData data) {

		ServiceDataPacket serviceDataPacket = createServiceDataPacket(data, PlatformUtils.HANDLE_RESERVATION_SER);
		serviceDataPacket.setRequester(requestPacket.getRequester());
		return serviceDataPacket;
		
		
	}
	
	/**
	 * @param requestPacket
	 * @param data
	 * @return
	 */
	public static ServiceDataPacket createActivityInformServiceDataPacket(ServiceDataPacket requestPacket, ActivityInformData data) {

		ServiceDataPacket serviceDataPacket = createServiceDataPacket(data, PlatformUtils.HANDLE_ACTIVITY_SER);
		serviceDataPacket.setRequester(requestPacket.getRequester());
		return serviceDataPacket;
	}
}
