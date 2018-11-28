package data;

/**
 * @author Luis Bl�zquez Mi�ambres y Samuel G�mez S�nchez
 *
 */
public class RequestData extends ServiceData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param client Agent requesting service
	 * @param data Service's associated data
	 */
	public RequestData(String client) {
		super(client);
	}
	
	/**
	 * @return the client
	 */
	public String getClient()
	{
		return this.getSource();
	}
	
	/**
	 * @param client the client to set
	 */
	public void setClient(String client)
	{
		this.setSource(client);
	}

}
