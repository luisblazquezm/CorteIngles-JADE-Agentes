package platform1;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import utilities.JadeUtils;

public class CyclicBehaviourMakeTrip extends CyclicBehaviour
{
	public CyclicBehaviourMakeTrip(Agent agent)
	{
		super(agent);
	}

	@Override
	public void action()
	{
		// TODO Auto-generated method stub

		List<String> messageContentActivity = new ArrayList<String>();
		List<String> messageContentAccomodation = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
        String answer, behaviourAction, reserveInfo;
        int requestWait; // Number of agents that implement a determined service
        
		// Ask the user through the console about the information of the trip is planning to make
        do{
    		System.out.printf("¿Desea hacer un viaje?: ");
            answer = sc.nextLine();
        }while(!"sSnN".contains(answer));
        System.out.println();
        
        if ("sS".contains(answer)){
        	behaviourAction = "1";
        	
    		System.out.printf("%nIntroduce the destination: ");
    		String destination = sc.nextLine();
    		
    		System.out.printf("%nDeparture date (Available from 01/05/2018 until 30/05/2018): ");
    		String departureDate = sc.nextLine();
    		
    		/*
    		DateFormat formatDeparture = new SimpleDateFormat("dd/MM/yyyy");
    		try{
    			Date depDate = formatDeparture.parse(departureDate); 
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}
    		*/
    		
    		System.out.printf("%nReturn date (Available from 02/05/2018 until 31/05/2018): ");
    		String returnDate = sc.nextLine();
    		
    		/*
    		DateFormat formatReturn = new SimpleDateFormat("dd/MM/yyyy");
    		try{
    			Date retDate = formatReturn.parse(returnDate);
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}
    		*/
    		
    		messageContentAccomodation.add(destination);
    		messageContentAccomodation.add(departureDate);
    		messageContentAccomodation.add(returnDate);
    		

    		
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
        		
        		System.out.printf("%nDate (Available from 01/05/2018 until 31/05/2018): ");
        		String activityDate = sc.nextLine();
        		
        		/*
        		DateFormat formatDeparture = new SimpleDateFormat("dd/MM/yyyy");
        		try{
        			Date depDate = formatDeparture.parse(departureDate); 
        		} catch (ParseException e) {
        			e.printStackTrace();
        		}
        		*/
        		
        		messageContentActivity.add(city);
        		messageContentActivity.add(activityDate);        		

            } else if ("nN".contains(answer)) {
            	//exit();
            }
        }
        

        switch(behaviourAction){
	        case "1": // Send Message Accomodation
	    		JadeUtils.sendMessage(this.myAgent, "reserve", messageContentAccomodation);
	            break;
	        case "2": // Send Message Activity
	    		JadeUtils.sendMessage(this.myAgent, "reserve", messageContentActivity);
	            break;
	        default:
	            System.out.printf("%n%nOpcion incorrecta%n%n");
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
					(requesWait)--;
				} else {
					System.err.printf("%n(CyclicBehaviourMakeTrip)ERROR:Received information is NULL%n");
				}
        	} else {
				System.err.printf("%n(CyclicBehaviourMakeTrip)ERROR:Received information is NULL%n");
        	}
        } else {
    		block();
    	}

	}
}
