package reservation;

import data.Data;
import data.ReservationInformData;
import data.ReservationRequestData;
import utilities.Debug;
import utilities.JadeUtils;
import utilities.PlatformUtils;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import messages.MessageContent;
import messages.Messages;


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
	
	@SuppressWarnings("unused")
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
				MessageContent content = (MessageContent) msg.getContentObject();
				if (content == null)
					System.err.println("UserAgentCyclicBehaviour: messageContent is null");
				
				//String[] reservationData = JadeUtils.getReservationData((String)content.getData());
				//boolean availability = Data.reservationRequestIsAvailable(reservationData);
				ReservationRequestData reservationData = (ReservationRequestData) content.getData();
				if (reservationData == null)
					System.err.println("ReservationAgent: reservationData is null");
				
				boolean availability = Data.reservationRequestIsAvailable(reservationData);
				ReservationInformData informData = new ReservationInformData(
						this.myAgent,
						reservationData.getDestinationCity(),
						reservationData.getDestinationHotel(),
						reservationData.getStartDate(),
						reservationData.getEndDate(),
						availability);
				
				if (informData == null)
					System.err.println("ReservationAgent: informData is null");
				
				//<--------------------------------------------- Identify Sender
				
				MessageContent answerMessageContent = Messages.createReservationInformMessageContent(informData, this.myAgent);
				if (answerMessageContent == null)
					System.err.println("UserAgentCyclicBehaviour: messageContent is null");
				
				int numberOfRecipients = JadeUtils.sendMessage(this.myAgent,
															  PlatformUtils.HANDLE_RESERVATION_SER,
															  ACLMessage.INFORM,
															  answerMessageContent);
				
				if (numberOfRecipients <= 0) {
					System.err.println("ServeUserBehaviour: no agents implementing requested service");
	        		return ;
				} 
				
				Debug.message("ReservationAgent: INFORM message sent");
				
			}
			catch (UnreadableException e)
			{
				System.err.println("ReservationAgentCyclicBehaviour: getContentObject failed");
				e.printStackTrace();
			}
			
		}

	}
}
