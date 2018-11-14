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
				answerMessageContentData = JadeUtils.reserveAccomodation(data);
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
}
