package data;

import jade.core.AID;
import jade.core.Agent;

public class RequestData extends ServiceData {

	/**
	 * @param client
	 * @param data
	 */
	public RequestData(AID client) {
		super(client);
	}
	
	/**
	 * @param client
	 * @param data
	 */
	public RequestData(Agent client) {
		super();
		if (client != null)
			this.setSource(client.getAID());
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AID getClient()
	{
		return this.getSource();
	}
	
	public void setClient(AID client)
	{
		this.setSource(client);
	}

}
