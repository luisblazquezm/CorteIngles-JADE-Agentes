/**
 * 
 */
package data;

import jade.core.AID;
import jade.core.Agent;

/**
 * @author mrhyd
 *
 */
public class InformData extends ServiceData{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param server
	 * @param data
	 */
	public InformData(AID server) {
		super(server);
	}
	
	/**
	 * @param server
	 * @param data
	 */
	public InformData(Agent server) {
		super();
		if (server != null)
			this.setSource(server.getAID());
	}

	/**
	 * @return the server
	 */
	public AID getServer() {
		return this.getSource();
	}

	/**
	 * @param server the server to set
	 */
	public void setServer(AID server) {
		this.setSource(server);
	}

	

}
