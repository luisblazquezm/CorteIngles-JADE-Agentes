package platform1;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.text.DateFormatter;

import utilities.JadeUtils;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import utilities.Cities;
import utilities.Debug;
import utilities.Hotels;

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
    		
    		
    		messageContentReservation = String.format("%s" + PlatformData.DELIMITER + 
    												  "%s" + PlatformData.DELIMITER + 
    												  "%s" + PlatformData.DELIMITER + 
    												  "%s", cityDestination, hotelDestination, date1, date2);
    		msgContentReservation = new MessageContent<>(PlatformData.HANDLE_RESERVATION_SER, messageContentReservation);
    		
    		Debug.message("UserAgentCyclicBehaviour: going to send REQUEST");
    		requestWait = JadeUtils.sendMessage(this.myAgent,
    				PlatformData.HANDLE_RESERVATION_SER,
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
        		
        		messageContentActivity = String.format("%s" + PlatformData.DELIMITER + 
        											   "%s" + PlatformData.DELIMITER +
        											   "%s", city, scheduleStartDate, scheduleEndDate);  
        		msgContentActivity = new MessageContent<>(PlatformData.HANDLE_ACTIVITY_SER, messageContentActivity);
        		// Insert the messageContent into the MessageContent object -> data and requestedService -> PlatformData.HANDLE_ACTIVITY_SER
        		requestWait = JadeUtils.sendMessage(this.myAgent,
	    				PlatformData.HANDLE_ACTIVITY_SER,
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
		 * 		1- Ask for reservations' requests
		 * 		2- Show information
		 * 			2.1- If reservation not available, repeat reservation option
		 * 		3- Ask for activities' requests
		 * 		4- Show information
		 * }
		 */
		
		processReservationRequest();
		processActivityRequest();
		
	}
	
	private void processReservationRequest() {
		
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
		String userWantsToTravel, destinationCity, destinationHotel;
		Date startDate, endDate;
		boolean dateIsCorrect;
		
		// ---------------------------------------------------------------------------------------
        
		userWantsToTravel = JadeUtils.getUserInput(
				"¿Desea realizar un viaje? [S/n]: ",
				true,
				MAX_ATTEMPTS,
				"s",
				"n");
		
		if ("sS".contains(userWantsToTravel)) { // Process reservation request
        	
        	/*
        	 * 1- Read input
        	 * 2- Send message
        	 */
			
			Cities.printCityNameList(Data.getArrayOfCityNames());

			destinationCity = JadeUtils.getUserInput(
					"Introduzca la ciudad de destino: ",
					true,
					MAX_ATTEMPTS,
					null,
					Data.getArrayOfCityNames()
			);
			
			Hotels.printHotelNameList(Data.getArrayOfHotelNames(destinationCity));
			
			destinationHotel = JadeUtils.getUserInput(
					"Introduzca el hotel de destino: ",
					true,
					MAX_ATTEMPTS,
					null,
					Data.getArrayOfHotelNames(destinationCity));
			
			startDate = JadeUtils.getDateFromUser(
					"Introduzca la fecha de entrada ("
							+ dateFormat.format(startLimitDate)
							+ " - "
							+ dateFormat.format(endLimitDate)
							+ "): ",
					startLimitDate,
					endLimitDate,
					dateFormat,
					MAX_ATTEMPTS);
			
			endDate = JadeUtils.getDateFromUser(
					"Introduzca la fecha de salida ("
							+ dateFormat.format(startLimitDate)
							+ " - "
							+ dateFormat.format(endLimitDate)
							+ "): ",
					startLimitDate,
					endLimitDate,
					dateFormat,
					MAX_ATTEMPTS);
			
			Debug.message("UserAgentCyclicBehaviour: going to send REQUEST");
			requestWait = JadeUtils.sendMessage(this.myAgent,
			PlatformData.HANDLE_RESERVATION_SER,
			ACLMessage.REQUEST,
			msgContentReservation);
			
			
		}
		
	}
	
	private void processActivityRequest() {
		
		// For stdin
		String answer;
        
		answer = JadeUtils.getUserInput(
				"¿Desea ver actividades de ocio? [S/n]: ",
				true,
				3,
				"s",
				"n");
		
		if (answer.contains("sS")) { // Process reservation request
			
		}
		
	}
	
	private void repeatedCodeReceiveInformMessage(int requestWait, String reserveInfo) {
		// Waiting for the INFORM message from AgentCorteIngles in a non-blocking state
		if (Debug.IS_ON)
			System.out.printf("UserAgent waiting for INFORM message from CorteInglesAgent\n");
        MessageTemplate template = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
		ACLMessage message = this.myAgent.receive(template);
		
        if (message == null) {
    		if (Debug.IS_ON)
    			System.out.printf("UserAgent blocked\n");
    		block();
        } else {
        	
        	if (Debug.IS_ON) {
	    		System.out.println("INFORM Message received in UserAgentCyclicBehaviour");
	    		System.out.printf("RequestWait: %d\n", requestWait);
        	}
    		
        	if (requestWait > 0) { 
				try
				{
					@SuppressWarnings("unchecked")
					MessageContent<String> content = (MessageContent<String>) message.getContentObject();
					String data = content.getData();
					reserveInfo = this.printInfoMessage(data); // reserveInfo as sBAppend if it returns a table
				}
				catch (UnreadableException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if (reserveInfo != null) {
					System.out.printf("%s", reserveInfo);
					(requestWait)--;
				} else {
					System.err.printf("%nUserAgentCyclicBehaviour:action: ERROR:Request Wait < 0%n");
				}
        	} else {
				System.err.printf("%nUserAgentCyclicBehaviour:action: ERROR:Received information is NULL%n");
        	}
    	}
	}

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
					    	sb.append(String.format("| %-13s | %-26d |\n", h.getName(), PlatformData.MAX_ROOMS_IN_HOTEL));
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
		String[] data = contentObject.split(Pattern.quote(PlatformData.DELIMITER));
		String type = data[PlatformData.RECEIVER_TYPE_INDEX];

		// RESERVATIONS
		if (type.equals(PlatformData.RESERVATION_MESSAGE)) {
			String availability = data[PlatformData.RECEIVER_AVAILABILITY_INDEX];
			String city = data[PlatformData.RECEIVER_CITY_INDEX];
			String hotel = data[PlatformData.RECEIVER_HOTEL_INDEX];
			String departureDate = data[PlatformData.RECEIVER_DEPARTURE_INDEX];
			String returnDate = data[PlatformData.RECEIVER_RETURN_INDEX];
			
			// RESERVATION AVAILABLE
			if (availability.equals(PlatformData.RESERVATION_AVAILABLE)){				
		    	sb.append(String.format("%s", "\n+-----+----------------+------+-----+\n"));
		    	sb.append(String.format("| %s | %s | %s | %s |\n", "City", "Hotel Name" , "DepartureDate", "ReturnDate"));
		    	sb.append(String.format("%s", "+-----+----------------+------+-----+\n"));
			    sb.append(String.format("| %s | %s | %s | %s | %s |\n", city, hotel , departureDate, returnDate));
		    	sb.append(String.format("%s", "+-----+----------------+------+-----+\n"));
		    	
			// RESERVATION NOT AVAILABLE
			} else if (availability.equals(PlatformData.RESERVATION_NOT_AVAILABLE)){
				return String.format("\nReservation not available in City: %s  Hotel: %s from %s  to  %s\n", city, hotel, departureDate, returnDate);
			}
			
		// ACTIVITIES
    	} else if (type.equals(PlatformData.ACTIVITY_MESSAGE)) {
			// ACTIVITY NOT FOUND
    		if (data[1].equals("None")) {
				return String.format("\nActivity not found");
				
			// ACTIVITY FOUND
    		} else {
    			for (int i = 0 ; i < data.length ; i++) {
    				String[] act = data[i].split(Pattern.quote(PlatformData.ACTIVITIES_DELIMITER));
    	    		String activity = act[PlatformData.RECEIVER_ACTIVITY_INDEX];
    				String city = act[PlatformData.RECEIVER_ACTIVITY_CITY_INDEX];
    				String startActivityDate = act[PlatformData.RECEIVER_START_OF_ACTIVITY_INDEX];
    				String endActivityDate = act[PlatformData.RECEIVER_END_OF_ACTIVITY_INDEX];
    			
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
}
