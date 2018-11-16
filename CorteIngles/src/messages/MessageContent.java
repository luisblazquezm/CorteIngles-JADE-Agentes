/**
 * @author mrhyd
 * 
 * Class that wraps up the information needed to identify the requested service
 * for which a message asks.
 * 
 */

package messages;

import java.io.Serializable;

import jade.core.AID;
import jade.core.Agent;

/**
 * @author mrhyd
 *
 */
public class MessageContent
	implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Service being carried on
	 */
	private String service;
	
	/**
	 * Service's associated data
	 */
	private Object data;
	
	/**
	 * 
	 */
	private AID requester;

	/**
	 * @param service
	 * @param data
	 * @param requester
	 */
	public MessageContent(String service, Object data, AID requester) {
		super();
		this.service = service;
		this.data = data;
		this.requester = requester;
	}
	
	/**
	 * @param service
	 * @param data
	 * @param requester
	 */
	public MessageContent(String service, Object data, Agent requester) {
		super();
		this.service = service;
		this.data = data;
		this.requester = requester.getAID();
	}

	/**
	 * @return the service
	 */
	public String getService() {
		return service;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @return the requester
	 */
	public AID getRequester() {
		return requester;
	}

	/**
	 * @param sender The sender of the message
	 */
	public void identify(AID sender) {
		this.requester = sender;
	}
	
	/**
	 * @param sender The sender of the message
	 */
	public void identify(Agent sender) {
		this.requester = sender.getAID();
	}
	
	
	
	
}
