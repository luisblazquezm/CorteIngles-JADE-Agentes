/**
 * 
 */
package data;

import java.io.Serializable;

import jade.core.AID;

/**
 * @author mrhyd
 *
 */
public class InformData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private AID server;

	/**
	 * @param server
	 */
	public InformData(AID server) {
		this.server = server;
	}
	
	/**
	 * @param server
	 */
	public InformData() {
		this(null);
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

	

}
