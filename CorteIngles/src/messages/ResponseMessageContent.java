package messages;

import jade.core.AID;
import jade.core.Agent;

public class ResponseMessageContent
	extends MessageContent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Message's type
	 */
	private AID server;

	/**
	 * @param service
	 * @param data
	 * @param requester
	 * @param server
	 */
	public ResponseMessageContent(String service, Object data, AID requester, AID server) {
		super(service, data, requester);
		this.server = server;
	}
	
	/**
	 * @param service
	 * @param data
	 * @param requester
	 * @param server
	 */
	public ResponseMessageContent(String service, Object data, Agent requester, AID server) {
		super(service, data, requester);
		this.server = server;
	}
	
	/**
	 * @param identifiedMessageContent
	 * @param type
	 */
	public ResponseMessageContent(MessageContent messageContent, AID server) {
		super(messageContent.getService(),
			  messageContent.getData(),
			  messageContent.getRequester());
		this.server = server;
	}

	/**
	 * @return the server
	 */
	public AID getServer() {
		return server;
	}

	/**
	 * @param server the server to set
	 */
	public void setServer(AID server) {
		this.server = server;
	}
	
	@Override
	public void identify(Agent sender) {
		this.identify(sender.getAID());;
	}
	
	public void identify(AID sender) {
		this.server = sender;
	}
	
}
