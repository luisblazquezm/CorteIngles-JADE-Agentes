/**
 * 
 */
package platform1;

import jade.core.AID;
import jade.core.Agent;

/**
 * @author mrhyd
 *
 */
public class IdentifiedMessageContent<T> {
	
	/**
	 * Requested service from the list in PlatformData
	 */
	private String requestedService;
	
	/**
	 * The data to be transmitted to some recipient agent
	 */
	private T data;
	
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
		super();
		this.requestedService = requestedService;
		this.data = data;
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
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
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

	/**
	 * @return the requestedService
	 */
	public String getRequestedService() {
		return requestedService;
	}

	/**
	 * @param requestedService the requestedService to set
	 */
	public void setRequestedService(String requestedService) {
		this.requestedService = requestedService;
	}

}
