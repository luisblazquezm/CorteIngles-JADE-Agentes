package reservation;

import data.Data;
import data.ReservationInformData;
import data.ReservationRequestData;
import utilities.JadeUtils;
import utilities.PlatformUtils;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import packets.Packets;
import packets.ServiceDataPacket;


/**
 * @author Luis Blázquez Miñambres y Samuel Gómez Sánchez
 *
 */
public class ReservationAgentCyclicBehaviour extends CyclicBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param agent This object's associated agent
	 */
	public ReservationAgentCyclicBehaviour(Agent agent) {
		super(agent);
	}
	
	/**
	 * 
	 */
	public ReservationAgentCyclicBehaviour() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see jade.core.behaviours.Behaviour#action()
	 */
	@Override
	public void action() 
	{
		// Waiting for a REQUEST message from AgentCorteIngles to do the reservation
		MessageTemplate template = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
		ACLMessage msg = this.myAgent.receive(template);
		
		if (msg == null) {
			block();
		} else {			
			try {
				ServiceDataPacket requestPacket = (ServiceDataPacket) msg.getContentObject();
				if (requestPacket == null)
					System.err.println("UserAgentCyclicBehaviour: messageContent is null");
				
				ReservationRequestData reservationData = (ReservationRequestData) requestPacket.getData();
				if (reservationData == null)
					System.err.println("ReservationAgent: reservationData is null");
				
				boolean availability = Data.reservationRequestIsAvailable(reservationData);
				ReservationInformData informData = new ReservationInformData(PlatformUtils.RESERVATION_AGENT,
																			 reservationData.getDestinationCity(),
																			 reservationData.getDestinationHotel(),
																			 reservationData.getStartDate(),
																			 reservationData.getEndDate(),
																			 availability);
			
				ServiceDataPacket answerMessageContent = Packets.createReservationInformServiceDataPacket(requestPacket, informData);
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
				
			} catch (UnreadableException e) {
				System.err.println("ReservationAgentCyclicBehaviour: getContentObject failed");
				e.printStackTrace();
			}
		}
	}
}
