package utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Utils {
	
	/**
	 * To read input
	 */
	private static Scanner scanner = new Scanner(System.in);

    /**
     * @param title Table's title
     * @param array Content of the table
     * @param width Table's width
     */
    public static void printStringTable(String title, String[] array, int width) {
    	
    	String newLine = String.format("%n");
    	String headerFrame = "=";
    	String bodyFrame = "-";
    	String contentFormat = "| %-" + (width - 2) + "s |" + newLine;
    	String leftBorder = "+";
    	String rightBorder = "+" + newLine;
    	    	
    	StringBuilder stringBuilder = new StringBuilder();
    	
    	stringBuilder.append(newLine + leftBorder);
    	for (int i = 0; i < width; ++i)
    		stringBuilder.append(headerFrame);
    	stringBuilder.append(rightBorder);
    	
    	stringBuilder.append(String.format(contentFormat, title));
    	
    	stringBuilder.append(leftBorder);
    	for (int i = 0; i < width; ++i)
    		stringBuilder.append(headerFrame);
    	stringBuilder.append(rightBorder);
    	
    	for (String c : array){
    		stringBuilder.append(String.format(contentFormat, c));
    		stringBuilder.append(leftBorder);
        	for (int i = 0; i < width; ++i)
        		stringBuilder.append(bodyFrame);
        	stringBuilder.append(rightBorder);
    	}
    	
    	System.out.println(stringBuilder);
    }
		
	/**
	 * @param prompt Message to be displayed to user
	 * @return User input
	 */
	public static String getUserInput(String prompt) {
		return getUserInput(prompt,
		 				   false,
	 				       0,
	 				       null,
	 				       (String[]) null);
	}
	
	/**
	 * @param prompt Message to be displayed to user
	 * @param defaultOption Option to be used if user input is just [RETURN]
	 * @return User input
	 */
	public static String getUserInput(String prompt,
			  						  String defaultOption)
	{
		return getUserInput(prompt,
					 		false,
				 			0,
				 			defaultOption,
				 			(String[]) null);
	}
	
	/**
	 * @param prompt Message to be displayed to user
	 * @param ignoreCase Indicates whether case must be taken into account in relation to options. If options is null, this is ignored
	 * @param numberOfAttempts Maximum number of attempts to read the input. If options is null, this is ignored
	 * @param defaultOption Option to be used if user input is just [RETURN]
	 * @param options Available options. Read input must be one of them. If null, ignored
	 * @return User input
	 */
	public static String getUserInput(String prompt,
									  boolean ignoreCase,
									  int numberOfAttempts,
									  String defaultOption,
									  String... options)
	{
	
		final String INPUT_ERROR_MESSAGE = "Opción no disponible";
		final String MAX_ATTEMPTS_REACHED_MESSAGE = "Ha realizado demasiados intentos. Inténtelo más tarde";
		String input = null;
		int attempts = 1;
		
		/*
		 * Si no hay opciones:
		 * 		Devolver input
		 * Si hay opciones:
		 * 		Hacer
	     *			Leer(input)
		 * 			Si (Vacio(input))
		 * 				Si (defaultOption)
		 * 					input = defaultOption
		 * 					salirDeBucle
		 * 				SiNo
		 * 					imprimirMensajeError
		 * 					++intentos
		 * 					Si (intentos > maxIntentos)
		 * 						salirDeBucle
		 * 					continuarBucle
		 * 			SiNo
		 * 				Si (input in opciones)
		 * 					salirDeBucle
		 * 				SiNo
		 * 					imprimirMensajeError
		 * 					++intentos
		 * 					Si (intentos > maxIntentos)
		 * 						salirDeBucle
		 * 					continuarBucle
		 * 		Mientras (NoSeDigaOtraCosa)
		 * 		
		 * 		Cerrar(scanner)
		 * 		Devolver(input)
		 * 
		 */
				
		if (options == null) {
			if (prompt != null)
				System.out.print(prompt);
			input = scanner.nextLine();
		} else {
			
			do {
				
				if (prompt != null)
					System.out.print(prompt);
				input = scanner.nextLine();

				if (input.equals("")) {
					
					if (defaultOption != null) {
						input = defaultOption;
						break;
					} else {
						System.out.println(INPUT_ERROR_MESSAGE);
						++attempts;
						if (attempts > numberOfAttempts) {
							System.out.println(MAX_ATTEMPTS_REACHED_MESSAGE);
							break;
						} else {
							continue;
						}
					}
					
				} else {
					if (input.equals(defaultOption) || isContained(input, ignoreCase, options)) {
						break;
					} else {
						System.out.println(INPUT_ERROR_MESSAGE);
						++attempts;
						if (attempts > numberOfAttempts) {
							System.out.println(MAX_ATTEMPTS_REACHED_MESSAGE);
							break;
						} else {
							continue;
						}
					}
				}
				
			} while (true);
		}
		
		return input;
		
		
	}

	/**
	 * @param option Option to be tested
	 * @param ignoreCase Indicates whether case must be taken into account in relation to options
	 * @param options Available options
	 * @return True if option is contained in options
	 */
	private static boolean isContained(String option,
									   boolean ignoreCase,
									   String... options)
	{
		if (option == null || options == null)
			return false;
		
		for (String s : options) {
			if (ignoreCase) {
				if (option.equalsIgnoreCase(s))
					return true;
			} else {
				if (option.equals(s))
					return true;
			}
		}
		
		return false;
	}
	
	/**
	 * @param prompt
	 * @param startLimitDate
	 * @param endLimitDate
	 * @param dateFormat
	 * @return
	 */
	public static Date getDateFromUser(String prompt, Date startLimitDate, Date endLimitDate,
			DateFormat dateFormat, int numberOfAttempts) {
		
		String userInputString = null;
		Date userInputDate = null;
		final String maxAttemptsReachedMessage = "Demasiados intentos. Pruebe de nuevo más tarde.";
		final String parseErrorMessage = "Introduzca una fecha con formato 'dd/mm/aaaa'.";
		final String dateOutOfBoundsMessage;
		final String anyDate = "'cualquiera'";
		int attempts = 1;
		
		if (dateFormat == null)
			dateFormat = new SimpleDateFormat(); // Default format
		
		// Dynamic construction of error message
		StringBuilder _dateOutOfBoundsMessage = new StringBuilder("Introduzca una fecha entre el ");
		if (startLimitDate != null)
			_dateOutOfBoundsMessage.append(dateFormat.format(startLimitDate));
		else
			_dateOutOfBoundsMessage.append(anyDate);
		_dateOutOfBoundsMessage.append(" y el ");
		if (endLimitDate != null)
			_dateOutOfBoundsMessage.append(dateFormat.format(endLimitDate));
		else
			_dateOutOfBoundsMessage.append(anyDate);
		_dateOutOfBoundsMessage.append(String.format("%n"));
		dateOutOfBoundsMessage = new String(_dateOutOfBoundsMessage);
		
		while (attempts <= numberOfAttempts) {
			
			userInputString = Utils.getUserInput(prompt);
			
			try {
				userInputDate = dateFormat.parse(userInputString);
			} catch (ParseException e) {
				userInputDate = null;
				System.out.println(parseErrorMessage);
				++attempts;
				continue;
			}
			
			if (startLimitDate != null && userInputDate.before(startLimitDate)) {
				System.out.println(dateOutOfBoundsMessage);
				++attempts;
				continue;
			}
			
			if (endLimitDate != null && userInputDate.after(endLimitDate)) {
				System.out.println(dateOutOfBoundsMessage);
				++attempts;
				continue;
			}
			
			return userInputDate;
		}
		
		System.out.println(maxAttemptsReachedMessage);
		return null;
	}


}
