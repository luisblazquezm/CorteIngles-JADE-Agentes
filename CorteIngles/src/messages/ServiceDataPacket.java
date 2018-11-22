/**
 * @author mrhyd
 * 
 * Class that wraps up the information needed to identify the requested service
 * for which a message asks.
 * 
 */

package messages;

import java.io.Serializable;

import data.ServiceData;
import jade.core.AID;
import jade.core.Agent;

/**
 * @author mrhyd
 *
 */
public class ServiceDataPacket implements Serializable {
	
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
	private ServiceData data;
	
	/**
	 * 
	 */
	private AID requester;

	/**
	 * @param service
	 * @param data
	 * @param requester
	 */
	public ServiceDataPacket(String service, ServiceData data, AID requester) {
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
	public ServiceDataPacket(String service, ServiceData data, Agent requester) {
		super();
		this.service = service;
		this.data = data;
		if (requester != null)
			this.requester = requester.getAID();
		else
			this.requester = null;
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
	public ServiceData getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(ServiceData data) {
		this.data = data;
	}

	/**
	 * @return the requester
	 */
	public AID getRequester() {
		return requester;
	}

	/**
	 * @param requester the requester to set
	 */
	public void setRequester(AID requester) {
		this.requester = requester;
	}	
}
