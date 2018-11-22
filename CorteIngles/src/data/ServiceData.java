/**
 * 
 */
package data;

import java.io.Serializable;

import jade.core.AID;
import jade.core.Agent;

/**
 * @author i0910465
 *
 */
public class ServiceData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private AID source;
		
	/**
	 * @param source
	 * @param data
	 */
	public ServiceData(AID source) {
		super();
		this.source = source;
	}
	
	/**
	 * @param source
	 * @param data
	 */
	public ServiceData(Agent source) {
		super();
		if (source != null)
			this.source = source.getAID();
		else
			this.source = null;
	}
	
	/**
	 * @param source
	 * @param data
	 */
	public ServiceData() {
		this.source = null;
	}
	
	/**
	 * @return the source
	 */
	public AID getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(AID source) {
		this.source = source;
	}
}
