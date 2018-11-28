/**
 * @author mrhyd
 * 
 * Dummy class, created to emulate conditional compilation
 * 
 */

package utilities;


/**
 * @author Luis Bl�zquez Mi�ambres y Samuel G�mez S�nchez
 *
 */
public final class Debug {
	
	// Set to true or false depending on whether prints are wanted or not
	public static final boolean IS_ON = false;
	
	public static void message(String message) {
		
		if (Debug.IS_ON)
			System.out.println(message);
		
	}
	
	public static void formattedMessage(String format, Object... args) {
		
		if (Debug.IS_ON)
			System.out.printf(format, args);
		
	}

}
