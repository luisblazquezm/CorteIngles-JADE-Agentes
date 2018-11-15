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
public class ActivityInformMessageContent<T>
	extends TypedIdentifiedMessageContent<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param identifiedMessageContent
	 * @param type
	 */
	public ActivityInformMessageContent(IdentifiedMessageContent<T> identifiedMessageContent, String type) {
		super(identifiedMessageContent, type);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param service
	 * @param data
	 * @param requester
	 * @param type
	 */
	public ActivityInformMessageContent(String service, T data, Agent requester, String type) {
		super(service, data, requester, type);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param service
	 * @param data
	 * @param requester
	 * @param type
	 */
	public ActivityInformMessageContent(String service, T data, AID requester, String type) {
		super(service, data, requester, type);
		// TODO Auto-generated constructor stub
	}
	
	

}
