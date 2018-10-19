/**
 * 
 */
package platform1;

/**
 * @author mrhyd
 *
 */
public class MessageContent<T> {
	
	/**
	 * Requested service from the list in PlatformData
	 */
	private String requestedService;
	
	/**
	 * The data to be transmitted to some recipient agent
	 */
	private T data;
		
	/**
	 * @param requestedService
	 * @param data
	 * @param requester
	 */
	public MessageContent(String requestedService, T data) {
		super();
		this.requestedService = requestedService;
		this.data = data;
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
