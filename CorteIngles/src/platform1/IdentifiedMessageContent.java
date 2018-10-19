/**
 * 
 */
package platform1;

import jade.core.AID;
import jade.core.Agent;

/**
 * @author mrhyd
 * @param <T>
 *
 */
public class IdentifiedMessageContent<T> extends MessageContent<T>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The AID of the agent who requested the data 
	 */
	private AID requester;
	
	/**
	 * @param requestedService
	 * @param data
	 * @param requester
	 */
	public IdentifiedMessageContent(String requestedService, T data, AID requester) {
		super(requestedService, data);
		this.requester = requester;
	}
	
	/**
	 * @param requestedService
	 * @param data
	 * @param requester
	 */
	public IdentifiedMessageContent(String requestedService, T data, Agent requester) {
		this(requestedService, data, requester.getAID());
	}
	
	/**
	 * @param requestedService
	 * @param data
	 * @param requester
	 */
	public IdentifiedMessageContent(MessageContent<T> messageContent, AID requester) {
		this(messageContent.getRequestedService(),
		     messageContent.getData(),
		     requester);
	}
	
	/**
	 * @param requestedService
	 * @param data
	 * @param requester
	 */
	public IdentifiedMessageContent(MessageContent<T> messageContent, Agent requester) {
		this(messageContent.getRequestedService(),
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
