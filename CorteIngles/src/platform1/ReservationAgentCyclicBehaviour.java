package platform1;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
	private List<Reservation> listOfReservations = new ArrayList<>();
	private boolean dataNotFound = false;
	
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
				answer = this.reserveAccomodation((MessageContent<String>) msg.getContentObject());
				// answer = this.reserveAccomodation(JadeUtils.extractMessageContent(msg));
				
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
	
	private String reserveAccomodation(MessageContent<String> receivedData)
	{
		String answer = new String();
		String content = receivedData.getData();
		
		String[] data = content.split(Pattern.quote(PlatformData.DELIMITER));

		boolean available = this.checkAvailability(data);
		
		if (available){
			answer = PlatformData.RESERVATION_MESSAGE + PlatformData.DELIMITER + 
					 PlatformData.RESERVATION_AVAILABLE + PlatformData.DELIMITER + 
					 data[PlatformData.SENDER_CITY_INDEX] + PlatformData.DELIMITER +
					 data[PlatformData.SENDER_HOTEL_INDEX] + PlatformData.DELIMITER +
					 data[PlatformData.SENDER_DEPARTURE_INDEX] + PlatformData.DELIMITER +
					 data[PlatformData.SENDER_RETURN_INDEX] + PlatformData.DELIMITER; 
		} else {
			if (this.dataNotFound){
				answer = PlatformData.RESERVATION_MESSAGE + PlatformData.DELIMITER + 
						 PlatformData.RESERVATION_NOT_AVAILABLE + PlatformData.DELIMITER + 
						 data[PlatformData.SENDER_CITY_INDEX] + PlatformData.DELIMITER +
						 data[PlatformData.SENDER_HOTEL_INDEX] + PlatformData.DELIMITER +
						 data[PlatformData.SENDER_DEPARTURE_INDEX] + PlatformData.DELIMITER +
						 data[PlatformData.SENDER_RETURN_INDEX] + PlatformData.DELIMITER; 
			} else {
				answer = "DATA NOT FOUND.";
			}
		}
				
		return answer;
	}
	
	private boolean checkAvailability(String[] data){
		
		String city = data[PlatformData.SENDER_CITY_INDEX];
		String hotel = data[PlatformData.SENDER_HOTEL_INDEX];
		String departureDate = data[PlatformData.SENDER_DEPARTURE_INDEX];
		String[] partsOfDate = departureDate.split("/");
		int departureDay = Integer.parseInt(partsOfDate[0]); // Gets only the day dd from dd/MM/yyyy
		String returnDate = data[PlatformData.SENDER_RETURN_INDEX];
		partsOfDate = returnDate.split("/");
		int returnDay = Integer.parseInt(partsOfDate[0]);
		int[] calendar;
	    int index = 0;
		
		for (Reservation r : this.listOfReservations) {

			if (r.getCity().equals(city) && r.getHotelName().equals(hotel)) {
				calendar = r.getOccupationCalendar();
				for (int i = departureDay; i <= returnDay; i++) {
					if ((calendar[i]-1) < 0) 
						return false;
				}
				
				for (int i = departureDay; i <= returnDay; i++) {
					(calendar[i])--;
				}
				
				r.setOccupationCalendar(calendar);
			    this.listOfReservations.add(index, r);
				
				return true;
			}
			index++;
		}
		
		// Reservation not found with those parameters 
		System.out.printf("%nReservationAgentCyclicBehaviour:checkAvailability: ERROR:Reservation not found or incorrect %n");
		this.dataNotFound = true;
		return false;
	}

}
