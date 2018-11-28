package data;

import java.io.Serializable;

/**
 * @author Luis Blázquez Miñambres y Samuel Gómez Sánchez
 *
 */
public class ServiceData implements Serializable {
	
	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Data source (agent role as defined in PlatformUtils)
	 */
	private String source;
		
	/**
	 * @param source Data source
	 */
	public ServiceData(String source) {
		this.source = source;
	}
	
	
	/**
	 * 
	 */
	public ServiceData() {
		this.source = null;
	}
	
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
}
