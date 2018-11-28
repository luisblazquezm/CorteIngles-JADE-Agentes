package packets;

import data.ActivityInformData;
import data.ActivityRequestData;
import data.ReservationInformData;
import data.ReservationRequestData;
import data.ServiceData;
import jade.core.AID;
import jade.core.Agent;
import utilities.PlatformUtils;

/**
 * @author Luis Blázquez Miñambres y Samuel Gómez Sánchez
 *
 */
public class Packets {
		
	/**
	 * @param data Service's associated data
	 * @param service Packet's service
	 * @return ServiceDataPacket object
	 */
	private static ServiceDataPacket createServiceDataPacket(String service, ServiceData data) {
		
		return new ServiceDataPacket(service, 
									 data, 
				                     (AID) null);
	}
	
	/**
	 * @param requester Agent requesting service
	 * @param data Service's associated data
	 * @return ServiceDataPacket object
	 */
	public static ServiceDataPacket createReservationRequestServiceDataPacket(AID requester, ReservationRequestData data) {

		ServiceDataPacket serviceDataPacket = createServiceDataPacket(PlatformUtils.HANDLE_RESERVATION_SER, data);
		serviceDataPacket.setRequester(requester);
		return serviceDataPacket;
	}
	
	/**
	 * @param requester Agent requesting service
	 * @param data Service's associated data
	 * @return ServiceDataPacket object
	 */
	public static ServiceDataPacket createActivityRequestServiceDataPacket(AID requester, ActivityRequestData data) {
		
		ServiceDataPacket serviceDataPacket = createServiceDataPacket(PlatformUtils.HANDLE_ACTIVITY_SER, data);
		serviceDataPacket.setRequester(requester);
		return serviceDataPacket;
	}
	
	
	/**
	 * @param requester Agent requesting service
	 * @param data Service's associated data
	 * @return ServiceDataPacket object
	 * @throws IllegalArgumentException
	 */
	public static ServiceDataPacket createReservationRequestServiceDataPacket(Agent requester, ReservationRequestData data) 
			throws IllegalArgumentException {

		if (requester == null)
			throw new IllegalArgumentException("Packets: createReservationRequestServiceDataPacket: requester cannot be null");
		
		ServiceDataPacket serviceDataPacket = createServiceDataPacket(PlatformUtils.HANDLE_RESERVATION_SER, data);
		serviceDataPacket.setRequester(requester.getAID());
		return serviceDataPacket;
	}
	
	/**
	 * @param requester Agent requesting service
	 * @param data Service's associated data
	 * @return ServiceDataPacket object
	 * @throws IllegalArgumentException
	 */
	public static ServiceDataPacket createActivityRequestServiceDataPacket(Agent requester, ActivityRequestData data)
		throws IllegalArgumentException {
		
		if (requester == null)
			throw new IllegalArgumentException("Packets: createActivityRequestServiceDataPacket: requester cannot be null");
		
		ServiceDataPacket serviceDataPacket = createServiceDataPacket(PlatformUtils.HANDLE_ACTIVITY_SER, data);
		serviceDataPacket.setRequester(requester.getAID());
		return serviceDataPacket;
	}
	
	/**
	 * @param requestPacket Request packet received. This method builds a response packet to that packet
	 * @param data Service's associated data
	 * @return ServiceDataPacket object
	 * @throws IllegalArgumentException
	 */
	public static ServiceDataPacket createReservationInformServiceDataPacket(ServiceDataPacket requestPacket, ReservationInformData data)
		throws IllegalArgumentException {

		if (requestPacket == null)
			throw new IllegalArgumentException("Packets: createReservationInformServiceDataPacket: requestPacket cannot be null");
		
		ServiceDataPacket serviceDataPacket = createServiceDataPacket(PlatformUtils.HANDLE_RESERVATION_SER, data);
		serviceDataPacket.setRequester(requestPacket.getRequester());
		return serviceDataPacket;
		
		
	}
	
	/**
	 * @param requestPacket Request packet received. This method builds a response packet to that packet
	 * @param data Service's associated data
	 * @return ServiceDataPacket object
	 * @throws IllegalArgumentException
	 */
	public static ServiceDataPacket createActivityInformServiceDataPacket(ServiceDataPacket requestPacket, ActivityInformData data)
		throws IllegalArgumentException {

		if (requestPacket == null)
			throw new IllegalArgumentException("Packets: createActivityInformServiceDataPacket: requestPacket cannot be null");
		
		ServiceDataPacket serviceDataPacket = createServiceDataPacket(PlatformUtils.HANDLE_ACTIVITY_SER, data);
		serviceDataPacket.setRequester(requestPacket.getRequester());
		return serviceDataPacket;
	}
}
