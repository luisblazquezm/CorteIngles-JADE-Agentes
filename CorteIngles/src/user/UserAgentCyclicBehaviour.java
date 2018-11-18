package user;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.regex.Pattern;

//import data.Activity;
import data.ActivityInformData;
import data.ActivityRequestData;
import data.Cities;
//import data.City;
import data.Data;
//import data.Hotel;
import data.Hotels;
import data.ReservationInformData;
import data.ReservationRequestData;
import utilities.JadeUtils;
import utilities.PlatformUtils;
import utilities.Utils;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import messages.MessageContent;
import messages.Messages;
import utilities.Debug;

public class UserAgentCyclicBehaviour extends CyclicBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAgentCyclicBehaviour(Agent agent)
	{
		super(agent);
	}
	
	/* Some code
	 * (non-Javadoc)
	 * @see jade.core.behaviours.Behaviour#action()
	 * 
	 * 
		// Messages for Activity And Reservation to be sent
		String messageContentActivity = new String();
		String messageContentReservation = new String();
        MessageContent<String> msgContentReservation = null;
        MessageContent<String> msgContentActivity = null;
		
		// Input variables for RESERVATION
		String cityDestination, hotelDestination;
		
		// Input variables for ACTIVITY
		String city;
		
		//
        String answer, behaviourAction = "Default", reserveInfo = "Default";
        
        // Variables used to convert the dates from string to date and to evaluate if they are correct
        String date1, date2, scheduleStartDate, scheduleEndDate;
        boolean departureDateIsCorrect = false, returnDateIsCorrect = false, scheduleStartDateIsCorrect = false, scheduleEndDateIsCorrect = false;
        
		SimpleDateFormat fmt = d
		Date max1 = new Date();
		Date min1 = new Date();
		Date max2 = new Date();
		Date min2 = new Date();
		
		try {
			min1 = fmt.parse("01/05/2018");
			min2 = fmt.parse("02/05/2018");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			max1 = fmt.parse("30/05/2018");
			max2 = fmt.parse("31/05/2018");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Date departureDate = new Date();
		Date returnDate = new Date();
		Date activityDate = new Date();
		
		// Flags that indicate if the parameters the user introduced are valid or not
        boolean hotelIsCorrect = false, cityIsCorrect = false;
        
        // Number of agents that implement a determined service
        int requestWait = -1; 
		
		
		// Other Variables
		Scanner sc = new Scanner(System.in);
        

		
		// Ask the user through the console about the information of the trip is planning to make
        do{
    		System.out.printf("\n¿Do you want to make a trip?[s/N]: ");
            answer = sc.nextLine();
        } while(!"sSnN".contains(answer));
        System.out.println();
        
    	// *************************************** RESERVATIONS ************************************************************
        
        if ("sS".contains(answer)){
        	
        	behaviourAction = "1";
        	
        	this.printInfoAbout("City", null);
        	
        	do {
	    		System.out.printf("%nIntroduce the city of destination: ");
	    		cityDestination = sc.nextLine();
	    		
	        	cityIsCorrect = this.printInfoAbout("Hotel", cityDestination);
        	} while (cityIsCorrect == false);
    		
        	do {
	    		System.out.printf("%nIntroduce the hotel of destination: ");
	    		hotelDestination = sc.nextLine();
	    		
	    		hotelIsCorrect = this.checkHotel(cityDestination, hotelDestination);
        	} while (hotelIsCorrect == false);
    		
    		do{
	    		System.out.printf("%nDeparture date (Available from 01/05/2018 until 30/05/2018): ");
	    		date1 = sc.nextLine();
	    		
	    		try{
	    			departureDate = new SimpleDateFormat("dd/MM/yyyy").parse(date1);
	    		} catch (Exception e) {
	    			System.err.printf("%nUserAgentCyclicBehaviour:action:reservation: %s%n", e.toString());
	    		}
	    		
	    		if (!(departureDate.after(min1) && departureDate.before(max1))){
	    			System.err.printf("%nDeparture date not included in the range of dates. Please introduce it again%n");
	    		} else {
	    			departureDateIsCorrect = true;
	    		}
	    		
    		} while (!departureDateIsCorrect);
	    		
    		do {
	    		System.out.printf("%nReturn date (Available from 02/05/2018 until 31/05/2018): ");
	    		date2 = sc.nextLine();
	    		
	    		try {
	    			returnDate = new SimpleDateFormat("dd/MM/yyyy").parse(date2);

	    		} catch (Exception e) {
	    			System.err.printf("%nUserAgentCyclicBehaviour:action:reservation: %s%n", e.toString());
	    		}
	    		
	    		if (!(returnDate.after(min2) && returnDate.before(max2))){
	    			System.err.printf("%nReturn date not included in the range of dates. Please introduce it again%n");
	    		} else {
	    			returnDateIsCorrect = true;
	    		}

    		} while(!returnDateIsCorrect);
    		
    		if(Debug.IS_ON) {
    			System.out.println("UserAgentCyclicBehaviour: input data tested");
    		}
    		
    		
    		messageContentReservation = String.format("%s" + PlatformUtils.DELIMITER + 
    												  "%s" + PlatformUtils.DELIMITER + 
    												  "%s" + PlatformUtils.DELIMITER + 
    												  "%s", cityDestination, hotelDestination, date1, date2);
    		msgContentReservation = new MessageContent<>(PlatformUtils.HANDLE_RESERVATION_SER, messageContentReservation);
    		
    		Debug.message("UserAgentCyclicBehaviour: going to send REQUEST");
    		requestWait = JadeUtils.sendMessage(this.myAgent,
    				PlatformUtils.HANDLE_RESERVATION_SER,
    				ACLMessage.REQUEST,
    				msgContentReservation);
    		Debug.message("UserAgentCyclicBehaviour: REQUEST sent");
			
			repeatedCodeReceiveInformMessage(requestWait, reserveInfo);
	
    	// *************************************** ACTIVITIES ************************************************************
        
            do{
        		System.out.printf("¿Desea ver actividades de ocio?: ");
                answer = sc.nextLine();
            }while(!"sSnN".contains(answer));
            System.out.println();
            
            if ("sS".contains(answer)){
            	behaviourAction = "2";
             	
            	this.printInfoAbout("City", null);
            	
        		System.out.printf("%nIntroduce the city: ");
        		city = sc.nextLine();
        		
            	this.printInfoAbout("Activity", city);
        		
        		do {
            		System.out.printf("%nDate Start of activity (Available from 01/05/2018 until 31/05/2018): ");
            		scheduleStartDate = sc.nextLine();
            		      		
    	    		try{
    	    			activityDate = new SimpleDateFormat("dd/MM/yyyy").parse(scheduleStartDate);
    	    		} catch (Exception e) {
    	    			System.err.printf("%nUserAgentCyclicBehaviour:action:activity: %s%n", e.toString());
    	    		}
    	    		
    	    		if (!(activityDate.after(min1) && activityDate.before(max2))){
    	    			System.err.printf("%nDeparture date not included in the range of dates. Please introduce it again%n");
    	    		} else {
    	    			scheduleStartDateIsCorrect = true;
    	    		}
    	    		
        		} while (!scheduleStartDateIsCorrect);
        		
        		do {
            		System.out.printf("%nDate End of activity (Available from 01/05/2018 until 31/05/2018): ");
            		scheduleEndDate = sc.nextLine();
            		
    	    		try{
    	    			activityDate = new SimpleDateFormat("dd/MM/yyyy").parse(scheduleEndDate);
    	    		} catch (Exception e) {
    	    			System.err.printf("%nUserAgentCyclicBehaviour:action:activity: %s%n", e.toString());
    	    		}
    	    		
    	    		if (!(activityDate.after(min1) && activityDate.before(max2))){
    	    			System.err.printf("%nDeparture date not included in the range of dates. Please introduce it again%n");
    	    		} else {
    	    			scheduleEndDateIsCorrect = true;
    	    		}
    	    		
        		} while (!scheduleEndDateIsCorrect);
        		
        		messageContentActivity = String.format("%s" + PlatformUtils.DELIMITER + 
        											   "%s" + PlatformUtils.DELIMITER +
        											   "%s", city, scheduleStartDate, scheduleEndDate);  
        		msgContentActivity = new MessageContent<>(PlatformUtils.HANDLE_ACTIVITY_SER, messageContentActivity);
        		// Insert the messageContent into the MessageContent object -> data and requestedService -> PlatformUtils.HANDLE_ACTIVITY_SER
        		requestWait = JadeUtils.sendMessage(this.myAgent,
	    				PlatformUtils.HANDLE_ACTIVITY_SER,
	    				ACLMessage.REQUEST,
	    				msgContentActivity);
        		System.out.println("REQUEST message related to activity sent to CorteInglesAgent");
        		
        		repeatedCodeReceiveInformMessage(requestWait, reserveInfo);

            } else if ("nN".contains(answer)) {
            	//exit();
            }

        }
	 */

	@Override
	public void action()
	{
		/*
		 * Do (adInfinitum) {
		 * 		1- Make reservations' requests
		 * 		2- Show information
		 * 			2.1- If reservation not available, repeat reservation option
		 * 		3- Make activities' requests
		 * 		4- Show information
		 * 			4.1- If no activities were found, repeat reservation option
		 * }
		 */
		
		boolean reservationIsAvailable, activitiesWereFound;
		
		do {
			reservationIsAvailable = processReservationRequest();
		} while (!reservationIsAvailable);
		
		do {
			activitiesWereFound = processActivityRequest();
		} while (!activitiesWereFound);
	}
	
	private boolean processReservationRequest() {
		
		// Constants
		final int MAX_ATTEMPTS = 5;
		final String dateFormatString = "dd/MM/yyyy";
		final DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		Date startLimitDate;
		Date endLimitDate;
		try {
			startLimitDate = dateFormat.parse("01/05/2019");
			endLimitDate = dateFormat.parse("31/05/2019");
		} catch (ParseException e) {
			Debug.message("UserAgentCycliBehaviour: wrong date format");
			startLimitDate = null;
			endLimitDate = null;
		}
		
		// For input
		String userWantsToTravel;
		
		// For message communication
		int numberOfServers = 0;
		ReservationRequestData reservationData = new ReservationRequestData();
		MessageContent messageContent;
		
		// ---------------------------------------------------------------------------------------
        
		userWantsToTravel = Utils.getUserInput(
				"¿Desea realizar un viaje? [S/n]: ",
				true,
				MAX_ATTEMPTS,
				"s",
				"n");
		
		if ("sS".contains(userWantsToTravel)) { // Process reservation request
        	
        	/*
        	 * 1- Read input
        	 * 2- Send message
        	 * 3- Wait for answer
        	 */
			
			Cities.printCityNameList(Data.getArrayOfCityNames());

			reservationData.setDestinationCity(
					Utils.getUserInput(
						"Introduzca la ciudad de destino: ",
						true,
						MAX_ATTEMPTS,
						null,
						Data.getArrayOfCityNames()
					)
			);
			
			Hotels.printHotelNameList(Data.getArrayOfHotelNames(reservationData.getDestinationCity()));
			
			reservationData.setDestinationHotel(
					Utils.getUserInput(
						"Introduzca el hotel de destino: ",
						true,
						MAX_ATTEMPTS,
						null,
						Data.getArrayOfHotelNames(reservationData.getDestinationCity())
					)
			);
			
			reservationData.setStartDate(
					Utils.getDateFromUser(
						"Introduzca la fecha de entrada ("
								+ dateFormat.format(startLimitDate)
								+ " - "
								+ dateFormat.format(endLimitDate)
								+ "): ",
						startLimitDate,
						endLimitDate,
						dateFormat,
						MAX_ATTEMPTS
					)
			);
			
			reservationData.setEndDate(
					Utils.getDateFromUser(
						"Introduzca la fecha de salida ("
								+ dateFormat.format(startLimitDate)
								+ " - "
								+ dateFormat.format(endLimitDate)
								+ "): ",
						startLimitDate,
						endLimitDate,
						dateFormat,
						MAX_ATTEMPTS
					)
			);
			
			messageContent = Messages.createReservationRequestMessageContent(reservationData);
			Debug.message("UserAgentCyclicBehaviour: going to send reservation REQUEST");
			
			// I don´t know if you didn´t add this variable for any reason but, anyway, I will comment it XD. <-------------------------------------------------------------------
			/* numberOfServers = JadeUtils.sendMessage(
					this.myAgent,
					PlatformUtils.HANDLE_RESERVATION_SER,
					ACLMessage.REQUEST,
					messageContent
			);
			*/
			
			numberOfServers = JadeUtils.sendMessage(
					this.myAgent,
					PlatformUtils.HANDLE_RESERVATION_SER,
					ACLMessage.REQUEST,
					messageContent
			);
			
			if (numberOfServers <= 0) {
        		Debug.message("UserAgentCyclicBehaviour: no agents implementing requested service");
        		return false;
			} else {
        		return waitAndProcessResponse(); // Should return boolean, depending on whether found or not
			}
		}// End of process reservation request
		
		return true; // <-------------------------------------------------------------------------------------------------------------------------- Just to calm down the compiler
	}

	private boolean processActivityRequest() {
		
		// Constants
		final int MAX_ATTEMPTS = 5;
		final String dateFormatString = "dd/MM/yyyy";
		final DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		Date startLimitDate;
		Date endLimitDate;
		try {
			startLimitDate = dateFormat.parse("01/05/2019");
			endLimitDate = dateFormat.parse("31/05/2019");
		} catch (ParseException e) {
			Debug.message("UserAgentCycliBehaviour: wrong date format");
			startLimitDate = null;
			endLimitDate = null;
		}
		
		// For input
		String userWantsToSeeActivities;
		
		// For message communication
		int numberOfServers = 0;
		ActivityRequestData activityData = new ActivityRequestData();
		MessageContent messageContent;
		
		
		// ---------------------------------------------------------------------------------------
        
		userWantsToSeeActivities = Utils.getUserInput(
				"¿Desea ver información sobre actividades de ocio? [S/n]: ",
				true,
				MAX_ATTEMPTS,
				"s",
				"n");
		
		if ("sS".contains(userWantsToSeeActivities)) { // Process reservation request
        	
        	/*
        	 * 1- Read input
        	 * 2- Send message
        	 * 3- Wait for answer
        	 */
			
			Cities.printCityNameList(Data.getArrayOfCityNames());

			activityData.setCity(
					Utils.getUserInput(
						"Introduzca la ciudad donde desea buscar actividades: ",
						true,
						MAX_ATTEMPTS,
						null,
						Data.getArrayOfCityNames()
					)
			);
						
			activityData.setStartDate(
					Utils.getDateFromUser(
						"Introduzca la fecha de inicio de la actividad ("
								+ dateFormat.format(startLimitDate)
								+ " - "
								+ dateFormat.format(endLimitDate)
								+ "): ",
						startLimitDate,
						endLimitDate,
						dateFormat,
						MAX_ATTEMPTS
					)
			);
						
			messageContent = Messages.createActivityRequestMessageContent(activityData);
			Debug.message("UserAgentCyclicBehaviour: going to send activity REQUEST");
			
			// I don´t know if you didn´t add this variable for any reason but, anyway, I will comment it XD. <-------------------------------------------------------------------
			/* numberOfServers = JadeUtils.sendMessage(
					this.myAgent,
					PlatformUtils.HANDLE_ACTIVITY_SER,
					ACLMessage.REQUEST,
					messageContent
			);
			*/
			
			JadeUtils.sendMessage(
					this.myAgent,
					PlatformUtils.HANDLE_ACTIVITY_SER,
					ACLMessage.REQUEST,
					messageContent
			);
			
			if (numberOfServers <= 0) {
        		Debug.message("UserAgentCyclicBehaviour: no agents implementing requested service");
        		return false;
			} else {
        		return waitAndProcessResponse(); // Should return boolean, depending on whether found or not
			}
		}// End of Process Reservation Request
		
		return true; // <-------------------------------------------------------------------------------------------------------------------------- Just to calm down the compiler
	}
	
	private boolean waitAndProcessResponse() {
		
		// TODO Parse data and return true or false depending on conditions
		
		Debug.formattedMessage("UserAgentCyclicBehaviour: waiting for INFORM message from %s%n", PlatformUtils.CORTE_INGLES_ALIAS);
		
        MessageTemplate template = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
		ACLMessage message = this.myAgent.receive(template);
		
        if (message == null) {
    		Debug.formattedMessage("UserAgentCyclicBehaviour: %s blocked%n", PlatformUtils.USER_ALIAS);
    		block();
        } else {

    		Debug.message("UserAgentCyclicBehaviour: INFORM Message received");
    		
			try {
				MessageContent content = (MessageContent) message.getContentObject();
				if (PlatformUtils.identifyAid(content.getRequester()).equals(PlatformUtils.RESERVATION_ALIAS)) {
					processReservationData((ReservationInformData) content.getData());
				} else if (PlatformUtils.identifyAid(content.getRequester()).equals(PlatformUtils.ACTIVITY_ALIAS)) {
					processActivityData((ActivityInformData) content.getData());
				} else {
					Debug.message("UserAgentCyclicBehaviour: where does this message come from?");
				}
			} catch (UnreadableException e) {
				Debug.message("UserAgentCyclicBehaviour: error converting message's content");
				e.printStackTrace();
			}
    	}
        
        return true; // <-------------------------------------------------------------------------------------------------------------------------- Just to calm down the compiler
	}

	private void processReservationData(ReservationInformData data) {
		// TODO Auto-generated method stub
		
		// Basically, print data
		
	}
	
	private void processActivityData(ActivityInformData data) {
		// TODO Auto-generated method stub
		
		// Basically, print data
		
	}
	 
	/*
	private boolean checkHotel(String city, String hotelDestination) {
		for (City c : Data.getListOfCities()) {
			if (c.getName().equals(city)){
				for(Hotel h : c.getListOfHotels()) {
					if (h.getName().equals(hotelDestination))
						return true;
				}
			} 
		}
		return false;
	}

	private boolean printInfoAbout(String info, String additionalInfo) {
		
		StringBuilder sb = new StringBuilder();
		boolean cityFound = false;
		
		if (Data.getListOfCities() == null)
			System.out.printf("printInfoAbout: ERROR list of cities is null.");
		
		if (info == null)
			System.out.printf("printInfoAbout: ERROR info passed is null.");
		
		// Prints all the cities available in our application
		if (info.equals("City")) {
	    	sb.append(String.format("%s", "\n+--------------+\n"));
	    	sb.append(String.format("| %-12s |\n", "----City----"));
	    	sb.append(String.format("%s", "+--------------+\n"));
	    	for (City c : Data.getListOfCities()){
		    	sb.append(String.format("| %-12s |\n", c.getName()));
		    	sb.append(String.format("%s", "+--------------+\n"));
	    	}
	    	
		// Prints all the hotels availables in our application related to the city the user chose
		} else if (info.equals("Hotel")) {
			if (additionalInfo != null) {
				for (City c : Data.getListOfCities()) {
					if (c.getName().equals(additionalInfo)){
						cityFound = true;
				    	sb.append(String.format("%s", "\n+---------------------------------------------+\n"));
				    	sb.append(String.format("| %-12s | %-12s |\n", "----Hotel----", "----Number Max Of Rooms----"));
				    	sb.append(String.format("%s", "+---------------+-----------------------------+\n"));
				    	for (Hotel h : c.getListOfHotels()){
					    	sb.append(String.format("| %-13s | %-26d |\n", h.getName(), PlatformUtils.MAX_ROOMS_IN_HOTEL));
					    	sb.append(String.format("%s", "+---------------+----------------------------+\n"));
				    	}
				    	
				    	break;
					} 
				}
				
				if (!cityFound) {
					System.out.printf("\nprintInfoAbout: Non existant city. Please, try again\n");
					return false;
				}
				
			} else {
				System.out.printf("printInfoAbout: No city received for searching hotel");
				return false;
			}

		// Prints all the activities availables in our application related to the city the user chose
		} else if (info.equals("Activity")){
			if (additionalInfo != null) {
				for (City c : Data.getListOfCities()) {
					if (c.getName().equals(additionalInfo)){
						cityFound = true;
				    	sb.append(String.format("%s", "\n+-------------+---------------------+\n"));
				    	sb.append(String.format("| %-12s | %-12s |\n", "----Activity----", "----Calendar of Activity----"));
				    	sb.append(String.format("%s", "+-------------+---------------------+\n"));
				    	for (Activity a : c.getListOfActivities()){
					    	sb.append(String.format("| %-12s | %-12d - %-12d |\n", a.getName(), a.getScheduleDescription()[0], a.getScheduleDescription()[1]));
					    	sb.append(String.format("%s", "+-------------+---------------------+\n"));
				    	}
				    	
				    	break;
					} 
				}
				
				if (!cityFound) {
					System.out.printf("\nprintInfoAbout: Non existant city. Please, try again\n");
					return false;
				}
				
			} else {
				System.out.printf("printInfoAbout: No city received for searching hotel");
				return false;
			}
		}
		
		System.out.printf("%s\n", sb.toString());
		
		return true;
	}

	// Receives the message and prints it to the user
	private String printInfoMessage(String contentObject) {
		StringBuilder sb = new StringBuilder();
		String[] data = contentObject.split(Pattern.quote(PlatformUtils.DELIMITER));
		String type = data[PlatformUtils.RECEIVER_TYPE_INDEX];

		// RESERVATIONS
		if (type.equals(PlatformUtils.RESERVATION_MESSAGE)) {
			String availability = data[PlatformUtils.RECEIVER_AVAILABILITY_INDEX];
			String city = data[PlatformUtils.RECEIVER_CITY_INDEX];
			String hotel = data[PlatformUtils.RECEIVER_HOTEL_INDEX];
			String departureDate = data[PlatformUtils.RECEIVER_DEPARTURE_INDEX];
			String returnDate = data[PlatformUtils.RECEIVER_RETURN_INDEX];
			
			// RESERVATION AVAILABLE
			if (availability.equals(PlatformUtils.RESERVATION_AVAILABLE)){				
		    	sb.append(String.format("%s", "\n+-----+----------------+------+-----+\n"));
		    	sb.append(String.format("| %s | %s | %s | %s |\n", "City", "Hotel Name" , "DepartureDate", "ReturnDate"));
		    	sb.append(String.format("%s", "+-----+----------------+------+-----+\n"));
			    sb.append(String.format("| %s | %s | %s | %s | %s |\n", city, hotel , departureDate, returnDate));
		    	sb.append(String.format("%s", "+-----+----------------+------+-----+\n"));
		    	
			// RESERVATION NOT AVAILABLE
			} else if (availability.equals(PlatformUtils.RESERVATION_NOT_AVAILABLE)){
				return String.format("\nReservation not available in City: %s  Hotel: %s from %s  to  %s\n", city, hotel, departureDate, returnDate);
			}
			
		// ACTIVITIES
    	} else if (type.equals(PlatformUtils.ACTIVITY_MESSAGE)) {
			// ACTIVITY NOT FOUND
    		if (data[1].equals("None")) {
				return String.format("\nActivity not found");
				
			// ACTIVITY FOUND
    		} else {
    			for (int i = 0 ; i < data.length ; i++) {
    				String[] act = data[i].split(Pattern.quote(PlatformUtils.ACTIVITIES_DELIMITER));
    	    		String activity = act[PlatformUtils.RECEIVER_ACTIVITY_INDEX];
    				String city = act[PlatformUtils.RECEIVER_ACTIVITY_CITY_INDEX];
    				String startActivityDate = act[PlatformUtils.RECEIVER_START_OF_ACTIVITY_INDEX];
    				String endActivityDate = act[PlatformUtils.RECEIVER_END_OF_ACTIVITY_INDEX];
    			
    		    	sb.append(String.format("%s", "\n+-----+----------------+------+-----+\n"));
			    	sb.append(String.format("| %s | %s | %s | %s |\n", "City", "Activity" , "Date Start of Activity", "Date End of Activity"));
			    	sb.append(String.format("%s", "+-----+----------------+------+-----+\n"));
				    sb.append(String.format("| %s | %s | %s | %s | %s |\n", city, activity , startActivityDate, endActivityDate));
			    	sb.append(String.format("%s", "+-----+----------------+------+-----+\n"));
    			}
    		}
    	}
		
		return sb.toString();
	}
	*/
}
