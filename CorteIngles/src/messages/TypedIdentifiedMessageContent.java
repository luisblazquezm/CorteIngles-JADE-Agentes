package messages;

import jade.core.AID;
import jade.core.Agent;

public class TypedIdentifiedMessageContent <T>
	extends IdentifiedMessageContent<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Message's type
	 */
	private String type;
	
	

	/**
	 * @param service
	 * @param data
	 * @param requester
	 * @param type
	 */
	public TypedIdentifiedMessageContent(String service, T data, AID requester, String type) {
		super(service, data, requester);
		this.type = type;
	}
	
	/**
	 * @param service
	 * @param data
	 * @param requester
	 * @param type
	 */
	public TypedIdentifiedMessageContent(String service, T data, Agent requester, String type) {
		super(service, data, requester);
		this.type = type;
	}
	
	/**
	 * @param identifiedMessageContent
	 * @param type
	 */
	public TypedIdentifiedMessageContent(IdentifiedMessageContent<T> identifiedMessageContent, String type) {
		super(identifiedMessageContent.getService(),
			  identifiedMessageContent.getData(),
			  identifiedMessageContent.getRequester());
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	

}
