package platform1;

import java.util.regex.Pattern;
import utilities.Debug;
import utilities.JadeUtils;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;


public class ReservationAgentCyclicBehaviour extends CyclicBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ReservationAgentCyclicBehaviour(Agent agent) {
		super(agent);
	}
	
	public ReservationAgentCyclicBehaviour() {
		super();
	}
	
	@Override
	public void action() 
	{
		String answerMessageContentData;

		// Waiting for a REQUEST message from AgentCorteIngles to do the reservation
		MessageTemplate template = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
		ACLMessage msg = this.myAgent.receive(template);
		
		if (msg == null) {
			block();
		} else {
			try
			{
				@SuppressWarnings("unchecked")
				MessageContent<String> content = (MessageContent<String>) msg.getContentObject();
				String data = content.getData();
				answerMessageContentData = this.reserveAccomodation(data);
				IdentifiedMessageContent<String> answerMessageContent =
						new IdentifiedMessageContent<>(PlatformData.HANDLE_RESERVATION_SER,
						answerMessageContentData,
						this.myAgent);
				
				if (Debug.IS_ON) {
					System.out.println("ReservationAgent: REQUEST received from AgentCorteIngles\n");
				}

				JadeUtils.sendMessage(this.myAgent,
									  PlatformData.HANDLE_RESERVATION_SER,
									  ACLMessage.INFORM,
									  answerMessageContent);
				
				if (Debug.IS_ON) {
					System.out.println("ReservationAgent: INFORM message sent");
				}
			}
			catch (UnreadableException e)
			{
				System.err.println("ReservationAgentCyclicBehaviour: getContentObject failed");
				e.printStackTrace();
			}
			
		}

	}
	
	private String reserveAccomodation(String receivedData)
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
		
		return this.delimitedStringFromReservation(available);
	}
	
	private String delimitedStringFromReservation(boolean availability) {
			
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


}
