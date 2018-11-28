/**
 * 
 */
package data;

/**
 * @author Luis Blázquez Miñambres y Samuel Gómez Sánchez
 *
 */
public class InformData extends ServiceData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param server Agent answering to request
	 * @param data Service's associated data
	 */
	public InformData(String server) {
		super(server);
	}

	/**
	 * @return the server
	 */
	public String getServer() {
		return this.getSource();
	}

	/**
	 * @param server the server to set
	 */
	public void setServer(String server) {
		this.setSource(server);
	}

	

}
