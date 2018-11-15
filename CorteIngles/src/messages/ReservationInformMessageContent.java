/**
 * 
 */
package messages;

import jade.core.AID;
import jade.core.Agent;

/**
 * @author mrhyd
 *
 */
public class ReservationInformMessageContent<T>
	extends TypedIdentifiedMessageContent<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private boolean availability;

	/**
	 * @param service
	 * @param data
	 * @param requester
	 * @param type
	 * @param availability
	 */
	public ReservationInformMessageContent(String service, T data, AID requester, String type, boolean availability) {
		super(service, data, requester, type);
		this.availability = availability;
	}
	
	/**
	 * @param typedIdentifiedMessageContent
	 * @param availability
	 */
	public ReservationInformMessageContent(TypedIdentifiedMessageContent<T> typedIdentifiedMessageContent, boolean availability) {
		super(typedIdentifiedMessageContent.getService(),
				typedIdentifiedMessageContent.getData(),
				typedIdentifiedMessageContent.getRequester(),
				typedIdentifiedMessageContent.getType());
		this.availability = availability;
	}

	/**
	 * @return the availability
	 */
	public boolean isAvailable() {
		return availability;
	}

	/**
	 * @param availability the availability to set
	 */
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	
	
	
	

}
