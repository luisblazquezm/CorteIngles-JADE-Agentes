package platform1;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import utilities.JadeUtils;

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

	@Override
	public void action()
	{
		// TODO Auto-generated method stub

		String messageContentActivity = new String();
		String messageContentReservation = new String();
		Scanner sc = new Scanner(System.in);
        String answer, behaviourAction = "Default", reserveInfo = "Default";
        String date1, date2, scheduleDate;
        boolean departureDateIsCorrect = false, returnDateIsCorrect = false, scheduleDateIsCorrect = false;
        
        MessageContent<String> msgContentReservation = null;
        MessageContent<String> msgContentActivity = null;

        
        int requestWait = -1; // Number of agents that implement a determined service
        
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
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
        
        
		// Ask the user through the console about the information of the trip is planning to make
        do{
    		System.out.printf("¿Do you want to make a trip?: ");
            answer = sc.nextLine();
        } while(!"sSnN".contains(answer));
        System.out.println();
        
        if ("sS".contains(answer)){
        	behaviourAction = "1";
        	
    		System.out.printf("%nIntroduce the city of destination: ");
    		String cityDestination = sc.nextLine();
    		
    		System.out.printf("%nIntroduce the hotel of destination: ");
    		String hotelDestination = sc.nextLine();
    		
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
    		
    		
    		messageContentReservation = String.format("%s" + PlatformData.DELIMITER + "%s" + PlatformData.DELIMITER + "%s" + PlatformData.DELIMITER + "%s", cityDestination, hotelDestination, date1, date2);
    		msgContentReservation = new MessageContent<>(PlatformData.HANDLE_RESERVATION_SER, messageContentReservation);
    		// Insert the messageContent into the MessageContent object -> data and requestedService -> PlatformData.HANDLE_RESERVATION_SER
	
        } else if ("nN".contains(answer)) {
            do{
        		System.out.printf("¿Desea ver actividades de ocio?: ");
                answer = sc.nextLine();
            }while(!"sSnN".contains(answer));
            System.out.println();
            
            if ("sS".contains(answer)){
            	behaviourAction = "2";
            	
        		System.out.printf("%nIntroduce the city: ");
        		String city = sc.nextLine();
        		
        		do{
            		System.out.printf("%nDate (Available from 01/05/2018 until 31/05/2018): ");
            		scheduleDate = sc.nextLine();
            		
    	    		try{
    	    			activityDate = new SimpleDateFormat("dd/MM/yyyy").parse(scheduleDate);
    	    		} catch (Exception e) {
    	    			System.err.printf("%nUserAgentCyclicBehaviour:action:activity: %s%n", e.toString());
    	    		}
    	    		
    	    		if (!(activityDate.after(min1) && activityDate.before(max2))){
    	    			System.err.printf("%nDeparture date not included in the range of dates. Please introduce it again%n");
    	    		} else {
    	    			scheduleDateIsCorrect = true;
    	    		}
    	    		
        		} while (!scheduleDateIsCorrect);
        		
        		messageContentActivity = String.format("%s" + PlatformData.DELIMITER + "%s", city, scheduleDate);  
        		msgContentActivity = new MessageContent<>(PlatformData.HANDLE_ACTIVITY_SER, messageContentActivity);
        		// Insert the messageContent into the MessageContent object -> data and requestedService -> PlatformData.HANDLE_ACTIVITY_SER

            } else if ("nN".contains(answer)) {
            	//exit();
            }

        }
        
        
        switch(behaviourAction){
	        case "1": // Send Message Reservation
	    		requestWait = JadeUtils.sendMessage(this.myAgent, PlatformData.HANDLE_RESERVATION_SER, msgContentReservation);
	            break;
	        case "2": // Send Message Activity
	        	requestWait = JadeUtils.sendMessage(this.myAgent, PlatformData.HANDLE_ACTIVITY_SER, msgContentActivity);
	            break;
	        default:
	            System.out.printf("%n%nUserAgentCyclicBehaviour:action: NO AGENTS WORKING%n%n");
	            break;
        }	
        
        
		// Waiting for the INFORM message from AgentCorteIngles in a non-blocking state
		ACLMessage msg = this.myAgent.receive(MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM), MessageTemplate.MatchOntology("ontologia")));
		System.out.println("INFORM Message received in CBleer");
		
        if (msg != null) {
        	if (requestWait > 0) { /*IMPORTANT: modify return value of Utils.sendMessage so it can return the number of agents that implement a determined service*/
				try
				{
					reserveInfo = (String) msg.getContentObject(); // reserveInfo as sBAppend if it returns a table
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
        } else {
    		block();
    	}

	}
}
