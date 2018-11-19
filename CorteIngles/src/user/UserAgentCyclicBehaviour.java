package user;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import data.Activity;
import data.ActivityInformData;
import data.ActivityRequestData;
import data.Cities;
import data.Data;
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
			if (messageContent == null)
				System.err.println("UserAgentCyclicBehaviour: messageContent is null");
			
			Debug.message("UserAgentCyclicBehaviour: going to send reservation REQUEST");
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
		} else if ("nN".contains(userWantsToTravel)) {
			return true;
		}
		
		return true;
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
			if (messageContent == null)
				System.err.println("UserAgentCyclicBehaviour: messageContent is null");
			
			Debug.message("UserAgentCyclicBehaviour: going to send activity REQUEST");
			numberOfServers = JadeUtils.sendMessage(
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
		} else if ("nN".contains(userWantsToSeeActivities)) {
			return true;
		}
		
		return true; 
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
        
        return true; 
	}

	private void processReservationData(ReservationInformData data) {
		// TODO Auto-generated method stub
		
		// Basically, print data
		StringBuilder sb = new StringBuilder();
		final String dateFormatString = "dd/MM/yyyy";
		final DateFormat dateFormat = new SimpleDateFormat(dateFormatString);			
		final String NEW_LINE = "\n";
        final String TABLE_JOINT_SYMBOL = "+";
        final String TABLE_V_SPLIT_SYMBOL = "|";
        final String TABLE_H_SPLIT_SYMBOL = "-";
        final String TABLE_H_SPACE_SYMBOL = " ";
        int width = 20;
        String title1 = "CITY";
        String title2 = "HOTEL";
        String title3 = "START DATE";
        String title4 = "END DATE";
        int NUM_PARAMS = 4;
        
        sb.append(NEW_LINE + TABLE_JOINT_SYMBOL);
        for (int i = 0 ; i < NUM_PARAMS ; i++){
            for (int j = 0 ; j < width ; j++)
                sb.append(TABLE_H_SPLIT_SYMBOL);
            sb.append(TABLE_JOINT_SYMBOL);
        }
        sb.append(NEW_LINE);
        

        sb.append(TABLE_V_SPLIT_SYMBOL + title1);
        for (int i = title1.length() ; i < width ; i++)
            sb.append(TABLE_H_SPACE_SYMBOL);
        
        sb.append(TABLE_V_SPLIT_SYMBOL + title2);
        for (int i = title2.length() ; i < width ; i++)
            sb.append(TABLE_H_SPACE_SYMBOL);
        
        sb.append(TABLE_V_SPLIT_SYMBOL + title3);
        for (int i = title3.length() ; i < width ; i++)
            sb.append(TABLE_H_SPACE_SYMBOL);
        sb.append(TABLE_V_SPLIT_SYMBOL);
        
        sb.append(TABLE_V_SPLIT_SYMBOL + title4);
        for (int i = title4.length() ; i < width ; i++)
            sb.append(TABLE_H_SPACE_SYMBOL);
        sb.append(TABLE_V_SPLIT_SYMBOL);

        sb.append(NEW_LINE + TABLE_JOINT_SYMBOL);
        for (int i = 0 ; i < NUM_PARAMS ; i++){
            for (int j = 0 ; j < width ; j++)
                sb.append(TABLE_H_SPLIT_SYMBOL);
            sb.append(TABLE_JOINT_SYMBOL);
        }
        sb.append(NEW_LINE);
        
		String city = data.getDestinationCity();
		String hotel = data.getDestinationHotel();
		String departureDate = dateFormat.format(data.getStartDate());
		String returnDate = dateFormat.format(data.getEndDate());
		
        sb.append(TABLE_V_SPLIT_SYMBOL + city);
        for (int i = city.length() ; i < width ; i++)
        sb.append(TABLE_H_SPACE_SYMBOL);
        
        sb.append(TABLE_V_SPLIT_SYMBOL + hotel);
        for (int i = hotel.length() ; i < width ; i++)
        sb.append(TABLE_H_SPACE_SYMBOL);
        
        sb.append(TABLE_V_SPLIT_SYMBOL + departureDate);
        for (int i = departureDate.length() ; i < width ; i++)
                sb.append(TABLE_H_SPACE_SYMBOL);
        sb.append(TABLE_V_SPLIT_SYMBOL);
        
        sb.append(TABLE_V_SPLIT_SYMBOL + returnDate);
        for (int i = returnDate.length() ; i < width ; i++)
                sb.append(TABLE_H_SPACE_SYMBOL);
        sb.append(TABLE_V_SPLIT_SYMBOL);
    	
        
        sb.append(NEW_LINE + TABLE_JOINT_SYMBOL);
        for (int i = 0 ; i < NUM_PARAMS ; i++){
            for (int j = 0 ; j < width ; j++)
                sb.append(TABLE_H_SPLIT_SYMBOL);
            sb.append(TABLE_JOINT_SYMBOL);
        }
        sb.append(NEW_LINE);
            
        System.out.printf(sb.toString());
		
	}
	
	private void processActivityData(ActivityInformData data) {
		// TODO Auto-generated method stub
		
		// Basically, print data
    	final String NEW_LINE = "\n";
        final String TABLE_JOINT_SYMBOL = "+";
        final String TABLE_V_SPLIT_SYMBOL = "|";
        final String TABLE_H_SPLIT_SYMBOL = "-";
        final String TABLE_H_SPACE_SYMBOL = " ";
        StringBuilder sb = new StringBuilder();
        int width = 20;
        String title1 = "ACTIVITY";
        String title2 = "START DATE";
        String title3 = "END DATE";
        int NUM_PARAMS = 3;
        
        sb.append(NEW_LINE + TABLE_JOINT_SYMBOL);
        for (int i = 0 ; i < NUM_PARAMS ; i++){
            for (int j = 0 ; j < width ; j++)
                sb.append(TABLE_H_SPLIT_SYMBOL);
            sb.append(TABLE_JOINT_SYMBOL);
        }
        sb.append(NEW_LINE);
        

        sb.append(TABLE_V_SPLIT_SYMBOL + title1);
        for (int i = title1.length() ; i < width ; i++)
            sb.append(TABLE_H_SPACE_SYMBOL);
        
        sb.append(TABLE_V_SPLIT_SYMBOL + title2);
        for (int i = title2.length() ; i < width ; i++)
            sb.append(TABLE_H_SPACE_SYMBOL);
        
        sb.append(TABLE_V_SPLIT_SYMBOL + title3);
        for (int i = title3.length() ; i < width ; i++)
            sb.append(TABLE_H_SPACE_SYMBOL);
        sb.append(TABLE_V_SPLIT_SYMBOL);

        sb.append(NEW_LINE + TABLE_JOINT_SYMBOL);
        for (int i = 0 ; i < NUM_PARAMS ; i++){
            for (int j = 0 ; j < width ; j++)
                sb.append(TABLE_H_SPLIT_SYMBOL);
            sb.append(TABLE_JOINT_SYMBOL);
        }
        sb.append(NEW_LINE);
        
    	for (Activity a : data.getArrayOfActivities()) {
    		String activity = a.getName();
    		int []scheduleDescription = a.getScheduleDescription();
    		
            sb.append(TABLE_V_SPLIT_SYMBOL + activity);
            for (int i = activity.length() ; i < width ; i++)
            sb.append(TABLE_H_SPACE_SYMBOL);
            
            sb.append(TABLE_V_SPLIT_SYMBOL + scheduleDescription[PlatformUtils.SENDER_START_OF_ACTIVITY_INDEX]);
            for (int i = 2 ; i < width ; i++)
            sb.append(TABLE_H_SPACE_SYMBOL);
            
            sb.append(TABLE_V_SPLIT_SYMBOL + scheduleDescription[PlatformUtils.SENDER_START_OF_ACTIVITY_INDEX]);
            for (int i = 2 ; i < width ; i++)
                    sb.append(TABLE_H_SPACE_SYMBOL);
            sb.append(TABLE_V_SPLIT_SYMBOL);
    	}
        
        sb.append(NEW_LINE + TABLE_JOINT_SYMBOL);
        for (int i = 0 ; i < NUM_PARAMS ; i++){
            for (int j = 0 ; j < width ; j++)
                sb.append(TABLE_H_SPLIT_SYMBOL);
            sb.append(TABLE_JOINT_SYMBOL);
        }
        sb.append(NEW_LINE);
            
        System.out.printf(sb.toString());

	}
	 
}
