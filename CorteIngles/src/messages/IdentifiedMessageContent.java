/**
 * @author mrhyd
 * 
 * Type of MessageContent that allows to identify the last agent
 * that must receive the information being exchanged
 * 
 */

package messages;

import jade.core.AID;
import jade.core.Agent;

/**
 * @author mrhyd
 * @param <T>
 *
 */
public class IdentifiedMessageContent<T>
	extends MessageContent<T>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The AID of the agent who requested the data in first place (usually one UserAgent)
	 */
	private AID requester;
	
	/**
	 * @param requestedService
	 * @param data
	 * @param requester
	 */
	public IdentifiedMessageContent(String service, T data, AID requester) {
		super(service, data);
		this.requester = requester;
	}
	
	/**
	 * @param requestedService
	 * @param data
	 * @param requester
	 */
	public IdentifiedMessageContent(String service, T data, Agent requester) {
		this(service, data, requester.getAID());
	}
	
	/**
	 * @param requestedService
	 * @param data
	 * @param requester
	 */
	public IdentifiedMessageContent(MessageContent<T> messageContent, AID requester) {
		this(messageContent.getService(),
		     messageContent.getData(),
		     requester);
	}
	
	/**
	 * @param requestedService
	 * @param data
	 * @param requester
	 */
	public IdentifiedMessageContent(MessageContent<T> messageContent, Agent requester) {
		this(messageContent.getService(),
			     messageContent.getData(),
			     requester.getAID());
	}

	/**
	 * @return the requester
	 */
	public AID getRequester() {
		return requester;
	}

	/**
	 * @param requestor the requester to set
	 */
	public void setRequestor(AID requester) {
		this.requester = requester;
	}
}
