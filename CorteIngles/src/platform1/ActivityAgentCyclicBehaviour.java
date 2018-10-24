/**
 * @author mrhyd
 * 
 * This class is the behaviour of the agent named 'Ocio' in the task's PDF file.
 * Its cyclic functioning is:
 * 		1- Wait for REQUEST message (must be sent from CorteInglesAgent)
 * 		2- Look for coincidences in the activities list of city in Data class
 * 		3- Send INFORM message (to CorteInglesAgent)
 * 		4- Go back to point #1
 * 
 */

package platform1;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.FIPAAgentManagement.Envelope;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import utilities.Debug;

/**
 * @author mrhyd
 *
 */
public class ActivityAgentCyclicBehaviour extends CyclicBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ActivityAgentCyclicBehaviour(Agent agent) {
		super(agent);
	}
	
	public ActivityAgentCyclicBehaviour() {
		super();
	}

	/* (non-Javadoc)
	 * @see jade.core.behaviours.Behaviour#action()
	 */
	@Override
	public void action() 
	{
		
		String answerMessageContentData;

		// Waiting for a REQUEST message from AgentCorteIngles to do an activity
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
				answerMessageContentData = this.getActivitiesString(data);
				
		    	//INFORM MESSAGE ELABORATION
				if (Debug.IS_ON) {
					System.out.println("ActivityAgent: REQUEST received from AgentCorteIngles\n");
				}
				ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);    	
		   		aclMessage.addReceiver(msg.getSender());
		        aclMessage.setOntology("ontologia");
		        aclMessage.setLanguage(new SLCodec().getName()); 	       
		        aclMessage.setEnvelope(new Envelope());                     
				aclMessage.getEnvelope().setPayloadEncoding("ISO8859_1");   
		        //aclMessage.getEnvelope().setAclRepresentation(FIPANames.ACLCodec.XML); 
				try {
					aclMessage.setContentObject((Serializable) answerMessageContentData); 
				} catch (IOException e) {
					System.err.println("ActivityAgentCyclicBehaviour: setContentObject failed");
					e.printStackTrace();
				}
				
		    	//INFORM MESSAGE SENDING 
				this.myAgent.send(aclMessage); 
				if (Debug.IS_ON) {
					System.out.println("ActivityAgent: INFORM message sent");
				}
			}
			catch (UnreadableException e)
			{
				System.err.println("ActivityAgentCyclicBehaviour: getContentObject failed");
				e.printStackTrace();
			}
			
		}
	}

	private String getActivitiesString(String dataString) {
		
		// 'dataString' should be "City#Date"
		String[] data = dataString.split(Pattern.quote(PlatformData.DELIMITER));
		
		List<Activity> activities = Data.getActivities(data[0], Integer.parseInt(data[1]));
		if (activities == null) {
			return PlatformData.ACTIVITY_MESSAGE + PlatformData.ACTIVITIES_DELIMITER + "None";
		}
		
		StringBuilder answer = new StringBuilder();
		
		answer.append(PlatformData.ACTIVITY_MESSAGE);
		for (Activity a : activities) {
			answer.append(PlatformData.ACTIVITIES_DELIMITER);
			answer.append(delimitedStringFromActivity(a));
		}
		
		return new String(answer);
	}
	
	private String delimitedStringFromActivity(Activity activity) {
		
		int[] schedule = activity.getScheduleDescription();
		
		StringBuilder string = new StringBuilder();
		
		string.append(activity.getName());
		string.append(PlatformData.DELIMITER);
		string.append(String.valueOf(schedule[0]));
		string.append(PlatformData.DELIMITER);
		string.append(String.valueOf(schedule[1]));
		
		return new String(string);
	}

}
