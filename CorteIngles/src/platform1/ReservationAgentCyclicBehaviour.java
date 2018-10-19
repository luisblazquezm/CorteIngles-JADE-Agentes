package platform1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import utilities.Accomodations;
import utilities.JadeUtils;

import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.FIPAAgentManagement.Envelope;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;


public class ReservationAgentCyclicBehaviour extends CyclicBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final List<Reservation> listOfReservations = new ArrayList<>();
	
	public ReservationAgentCyclicBehaviour(Agent agent)
	{
		super(agent);
	}
	
	@Override
	public void action() 
	{
		String answer;

		// Waiting for a REQUEST message from AgentCorteIngles to do the reservation
		MessageTemplate template = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
		ACLMessage msg = this.myAgent.receive(template);
		
		if (msg == null) {
			block();
		} else {
			try
			{
				answer = this.reserveAccomodation((List<String>) msg.getContentObject());
				
		    	//INFORM MESSAGE ELABORATION 
				System.out.println("(ReservationAgent)REQUEST received from AgentCorteIngles\n");
				ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);    	
		   		aclMessage.addReceiver(msg.getSender());
		   		
		        aclMessage.setOntology("ontologia");
		        aclMessage.setLanguage(new SLCodec().getName()); 	       
		        aclMessage.setEnvelope(new Envelope());                     
				aclMessage.getEnvelope().setPayloadEncoding("ISO8859_1");   
		        //aclMessage.getEnvelope().setAclRepresentation(FIPANames.ACLCodec.XML); 
				try {
					aclMessage.setContentObject((Serializable)answer); 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		    	//INFORM MESSAGE ELABORATION 
				this.myAgent.send(aclMessage); 
				System.out.println("INFORM message sent");
			}
			catch (UnreadableException e)
			{
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}
	

	private String reserveAccomodation(List<String> receivedData)
	{
		StringBuilder sb = new StringBuilder();

		boolean available = this.checkAvailability(receivedData);
		
		if (available){
	    	sb.append(String.format("%s", "+-----------------------------------+"));
	    	sb.append(String.format("| %s | %s | %s | %s|\n","City", "Hotel Name" ,"Number of Rooms", "Calendar"));
	    	sb.append(String.format("%s", "+-----------------------------------+"));
	    	for(int i = 0; i < 10 ; i++){
	    		
	    	}
		} else {
			sb.append(String.format("Sorry for the inconvenience. The reservation could not be done "));
			sb.append(String.format("as there were no more rooms available in the hotel %s on the period of days you asked: %s to %s", receivedData.get(0), receivedData.get(1), receivedData.get(2)));
		}
				
		
		return sb.toString();
	}
	
	private boolean checkAvailability(List<String> data){
		
		String city = data.get(JadeUtils.cityIndex);
		String departureD = data.get(JadeUtils.departureIndex);
		String returnD = data.get(JadeUtils.returnIndex);
		/*
		Reservation newReservation = Accomodations.instanceWithRandomAttributes();
		
		if (m.setListAccomodations(accomodation)){
			return true;
		} else {
			return false;
		}
		*/
		return false;
	}
	
	
}
