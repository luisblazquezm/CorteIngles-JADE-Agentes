package reservation;

//import java.util.regex.Pattern;

import data.Data;
import utilities.Debug;
import utilities.JadeUtils;
import utilities.PlatformUtils;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
//import messages.IdentifiedMessageContent;
import messages.MessageContent;
import messages.ResponseMessageContent;


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
		// Waiting for a REQUEST message from AgentCorteIngles to do the reservation
		MessageTemplate template = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
		ACLMessage msg = this.myAgent.receive(template);
		
		if (msg == null) {
			block();
		} else {

			Debug.message("ReservationAgent: REQUEST received from AgentCorteIngles\n");
			
			try
			{
				//@SuppressWarnings("unchecked")<---------------------------------------------------------------------------------------------------------------------------------
				//MessageContent<String> content = (MessageContent<String>) msg.getContentObject();<------------------------------------------------------------------------------
				MessageContent content = (MessageContent) msg.getContentObject();
				//String[] reservationData = JadeUtils.getReservationData(content.getData());<------------------------------------------------------------------------------------
				String[] reservationData = JadeUtils.getReservationData((String)content.getData());
				boolean availability = Data.reservationRequestIsAvailable(reservationData);
				ResponseMessageContent answerMessageContent =
						JadeUtils.createReservationInformMessageContent(reservationData, availability); // Requester and sender missing
				//IdentifiedMessageContent<String> answerMessageContent =
						//JadeUtils.createReservationInformMessageContent(reservationData, availability); <------------------------------------------------------------------------------

				JadeUtils.sendMessage(this.myAgent,
									  PlatformUtils.HANDLE_RESERVATION_SER,
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
}
