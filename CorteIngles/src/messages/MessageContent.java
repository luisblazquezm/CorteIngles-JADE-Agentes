/**
 * @author mrhyd
 * 
 * Class that wraps up the information needed to identify the requested service
 * for which a message asks.
 * 
 */

package messages;

import java.io.Serializable;

/**
 * @author mrhyd
 *
 */
public class MessageContent<T>
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
	private T data;

	/**
	 * @param service
	 * @param data
	 */
	public MessageContent(String service, T data) {
		super();
		this.service = service;
		this.data = data;
	}

	/**
	 * @return the service
	 */
	public String getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */
	public void setService(String service) {
		this.service = service;
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
	
	

}
