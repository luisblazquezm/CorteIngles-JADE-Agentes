/**
 * @author mrhyd
 * 
 * This class provides the user with a set of static methods
 * that implement different utilities for the JADE RTE
 */

package utilities;

import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.Envelope;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import platform1.Data;
import platform1.MessageContent;
import platform1.PlatformData;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

public class JadeUtils
{
	/**
	 * To read input
	 */
	private static Scanner scanner = new Scanner(System.in);
	
	/**
	 * @param destinationCity
	 * @param destinationHotel
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static MessageContent<String> createReservationRequestMessageContent(String destinationCity, String destinationHotel, String startDate, String endDate) {
		
		String reservationMessageContent = String.format(
				"%s" + PlatformData.DELIMITER + 
				"%s" + PlatformData.DELIMITER + 
				"%s" + PlatformData.DELIMITER + 
				"%s", destinationCity, destinationHotel, startDate, endDate);

		return new MessageContent<>(PlatformData.HANDLE_RESERVATION_SER, reservationMessageContent);
	}
	
	/**
	 * @param destinationCity
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static MessageContent<String> createActivityRequestMessageContent(String destinationCity, String startDate, String endDate) {
		
		String activityMessageContent = String.format(
				"%s" + PlatformData.DELIMITER +
				"%s" + PlatformData.DELIMITER +
				"%s", destinationCity, startDate, endDate);  
		
		return new MessageContent<>(PlatformData.HANDLE_ACTIVITY_SER, activityMessageContent);
	}
	
	public static MessageContent<String> createReservationInformMessageContent(String destinationCity, String startDate, String endDate) {
		
		
	}

	public static MessageContent<String> createActivityInformMessageContent(String destinationCity, String destinationHotel, String startDate, String endDate) {
	
	String reservationMessageContent = String.format(
			"%s" + PlatformData.DELIMITER + 
			"%s" + PlatformData.DELIMITER + 
			"%s" + PlatformData.DELIMITER + 
			"%s", destinationCity, destinationHotel, startDate, endDate);

		return new MessageContent<>(PlatformData.HANDLE_RESERVATION_SER, reservationMessageContent);
	}
	
	/**
	 * @param receivedData
	 * @return
	 */
	public static String reserveAccomodation(String receivedData)
	{
		String[] data = receivedData.split(Pattern.quote(PlatformData.DELIMITER));
		
		String city = data[PlatformData.SENDER_CITY_INDEX];
		String hotel = data[PlatformData.SENDER_HOTEL_INDEX];
		
		String departureDate = data[PlatformData.SENDER_DEPARTURE_INDEX];
		String[] partsOfDate = departureDate.split("/");
		int departureDay = Integer.parseInt(partsOfDate[0]); // Gets only the day dd from dd/MM/yyyy
		
		String returnDate = data[PlatformData.SENDER_RETURN_INDEX];
		partsOfDate = returnDate.split("/");
		int returnDay = Integer.parseInt(partsOfDate[0]);

		boolean available = Data.checkAvailability(city, hotel, departureDay, returnDay);
		
		return JadeUtils.delimitedStringFromReservation(available);
	}
	
	/**
	 * @param availability
	 * @return
	 */
	public static String delimitedStringFromReservation(boolean availability) {
			
		StringBuilder string = new StringBuilder();
		
		string.append(PlatformData.RESERVATION_MESSAGE);
		string.append(PlatformData.DELIMITER);
		
		if (availability){
			string.append(PlatformData.RESERVATION_AVAILABLE);
			string.append(PlatformData.DELIMITER);
		} else {
			string.append(PlatformData.RESERVATION_NOT_AVAILABLE);
			string.append(PlatformData.DELIMITER);
		}
		
		string.append(PlatformData.SENDER_CITY_INDEX);
		string.append(PlatformData.DELIMITER);
		string.append(PlatformData.SENDER_HOTEL_INDEX);
		string.append(PlatformData.DELIMITER);
		string.append(PlatformData.SENDER_DEPARTURE_INDEX);
		string.append(PlatformData.DELIMITER);
		string.append(PlatformData.SENDER_RETURN_INDEX); 
		
		return new String(string);
	}
	
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
	
	// Este método está bien, calcado del manual de JADE
	/**
	 * Look for all agents providing a service.
	 * @param clientAgent Agent that requests service.
	 * @param serviceType  Required service's type.
	 * @return DFAgentDescription array of agents which provide the service.
	 */
    protected static DFAgentDescription[] findAgentsForService(Agent clientAgent, String serviceType)
    {
    	DFAgentDescription template = new DFAgentDescription();
    	ServiceDescription serviceDescription = new ServiceDescription();
    	serviceDescription.setType(serviceType);
    	template.addServices(serviceDescription);
    	
    	try {
    		DFAgentDescription[] result = DFService.search(clientAgent, template);
    		if (Debug.IS_ON) {
	    		System.out.printf(
	    				"JadeUtils: findAgentsForService: REQUESTED SERVICE %s by %s -> N_MATCHES %d%n",
	    				serviceType,
	    				clientAgent.getLocalName(),
	    				result.length);
    		}
    		return result;
    	} catch(FIPAException e) {
    		
//			JOptionPane.showMessageDialog(null, 
//				  "findAgentsForService: Agent " + getLocalName() + ": " + e.getMessage(),
//			"Error",
//			JOptionPane.ERROR_MESSAGE);
			
			e.printStackTrace();
		}
        
        return null;
    }
    
    // Este método está bien, calcado del manual de JADE
    /**
     * Look for all agents providing a service and get the first of them.
     * @param clientAgent Agent that requests service.
	 * @param serviceType  Required service's type.
	 * @return First agent which provides the service.
     */
    protected static DFAgentDescription findFirstAgentForService(Agent clientAgent, String serviceType)
    {
    	DFAgentDescription template = new DFAgentDescription();
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType(serviceType);
        template.addServices(serviceDescription);
        
        SearchConstraints constraints = new SearchConstraints();
        constraints.setMaxResults(new Long(1));
        
        try {
            DFAgentDescription [] results = DFService.search(clientAgent, template, constraints);
            if (results.length > 0) {
                //System.out.println("findFirstAgentForService: Agent " + agent.getLocalName() + " found following agents: ");
                for (int i = 0; i < results.length; ++i) {
                	
                    DFAgentDescription dfd = results[i];
                    
                    // Not used, wtf? This guys gives us this shitty code...
//                    AID provider = dfd.getName();
                    
                    @SuppressWarnings("unchecked")
					Iterator<ServiceDescription> iterator = dfd.getAllServices();
                    
                    while (iterator.hasNext()) {
                        ServiceDescription sd = (ServiceDescription) iterator.next();
                        if (sd.getType().equals(serviceType)) {
                            //System.out.println("findFirstAgentForService: Service \"" + sd.getName() + "\" is provided by agent " + provider.getName());
                            return dfd;
                        }
                    }
                }
            } else {
//                JOptionPane.showMessageDialog(null,
//                							  "Agent " + getLocalName() + " did not found any service",
//                							  "Error",
//                							  JOptionPane.INFORMATION_MESSAGE);
            }
        } catch(FIPAException e) {
//            JOptionPane.showMessageDialog(null,
//        	                              "Agente " + getLocalName() + ": " + e.getMessage(),
//        	                              "Error",
//        	                              JOptionPane.ERROR_MESSAGE);
        	e.printStackTrace();
        }
        
        return null;
    }
    
    // Este método está bien, calcado del manual de JADE
    /**
     * Send an object from agent clientAgent to an agent implementing specified service.
     * @param clientAgent Agent that requests service.
	 * @param serviceType  Required service's type.
	 * @param performative ACLMessage performative for the message
	 * @param object Sent message.
     */
    public static int sendMessage(Agent clientAgent, String serviceType, int performative , Object object)
    {
        DFAgentDescription[] dfd;
        dfd = findAgentsForService(clientAgent, serviceType);
        
        try {
            if(dfd != null) {
            	
            	ACLMessage aclMessage = new ACLMessage(performative);
            	
            	for(int i = 0; i < dfd.length; ++i) {
	        		aclMessage.addReceiver(dfd[i].getName());
            	}
            	
                aclMessage.setOntology("ontology");
                aclMessage.setLanguage(new SLCodec().getName());
                aclMessage.setEnvelope(new Envelope());
				aclMessage.getEnvelope().setPayloadEncoding("ISO8859_1");
                //aclMessage.getEnvelope().setAclRepresentation(FIPANames.ACLCodec.XML);
				aclMessage.setContentObject((Serializable) object);
        		clientAgent.send(aclMessage);       		
            }
            
        } catch(IOException e) {
//            JOptionPane.showMessageDialog(null,
//                                        "Agente " + getLocalName() + ": " + e.getMessage(),
//                                        "Error",
//                                        JOptionPane.ERROR_MESSAGE);
    		e.printStackTrace();
        }
        
        return dfd.length;
    }
    
    // Este método está bien, calcado del manual de JADE
    /**
     * Defines a service that will be implemented by one or more agents.
     * @param agent Agent that implements the service.
	 * @param services Array of Service's type - Service's name pairs
     */
    public static void registerServices(Agent agent, String[][] services)
    	throws NullPointerException
    {
    	
    	if (agent == null || services == null) {
    		throw new NullPointerException();
    	}
    	
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(agent.getAID());
		
		for (int i = 0; i < services.length; ++i) {
			ServiceDescription sd = new ServiceDescription();
			sd.setType(services[i][0]);
			sd.setName(services[i][1]);
//			sd.addOntologies("ontologia");
//			sd.addLanguages(new SLCodec().getName());
			dfd.addServices(sd);
		}
		
		try {
			DFService.register(agent, dfd);
		} catch (FIPAException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    public static void deregisterService (Agent agent) 
    		throws NullPointerException
    {
    	if (agent == null) {
    		throw new NullPointerException();
    	}
    	
    	try {
    		DFService.deregister(agent);
    	} catch (FIPAException e) {
    		e.printStackTrace();
    	}
    		
    }
    
	@SuppressWarnings("unchecked")
	public static <T extends MessageContent<?>> T extractMessageContent(ACLMessage message)
    		throws UnreadableException
    {
    	return (T) message.getContentObject();
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
			
			userInputString = JadeUtils.getUserInput(prompt);
			
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
