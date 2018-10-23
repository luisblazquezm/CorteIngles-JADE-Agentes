/**
 * 
 */
package platform1;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import jade.content.lang.sl.SLCodec;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.FIPAAgentManagement.Envelope;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

/**
 * @author mrhyd
 *
 */
public class ActivityAgentCyclicBehaviour extends CyclicBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Activity> listOfActivities = new ArrayList<>();

	/* (non-Javadoc)
	 * @see jade.core.behaviours.Behaviour#action()
	 */
	@Override
	public void action() 
	{
		
		String answer;

		// Waiting for a REQUEST message from AgentCorteIngles to do an activity
		MessageTemplate template = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
		ACLMessage msg = this.myAgent.receive(template);
				
		if (msg == null) {
			block();
		} else {
			try
			{
				answer = this.getInfoActivity(((MessageContent<String>) msg.getContentObject()));
				//answer = this.getInfoActivity((List<String>) msg.getContentObject());
				
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

	private String getInfoActivity(MessageContent<String> receivedData) {
		String content = receivedData.getData();
		String answer = new String();
		
		String[] data = content.split(Pattern.quote(PlatformData.DELIMITER));
		
		String activity = this.checkActivity(data);	
		
		answer = PlatformData.ACTIVITY_MESSAGE + PlatformData.DELIMITER + 
				 activity + PlatformData.DELIMITER + 
				 data[PlatformData.SENDER_CITY_INDEX] + PlatformData.DELIMITER +
				 data[PlatformData.SENDER_START_OF_ACTIVITY_INDEX] + PlatformData.DELIMITER +
				 data[PlatformData.SENDER_END_OF_ACTIVITY_INDEX]; 
			
		return answer;
	}

	private String checkActivity(String[] data) {
		
		String city = data[PlatformData.SENDER_CITY_INDEX];
		String startActivityDate = data[PlatformData.SENDER_START_OF_ACTIVITY_INDEX];
		String endActivityDate = data[PlatformData.SENDER_END_OF_ACTIVITY_INDEX];
		
		String activity = "None";

		String[] partsOfDate = startActivityDate.split("/");
		int startActivityDay = Integer.parseInt(partsOfDate[0]); // Gets only the day dd from dd/MM/yyyy
		
		partsOfDate = endActivityDate.split("/");
		int endActivityDay = Integer.parseInt(partsOfDate[0]);
		
		for (Activity a : this.listOfActivities) {
			if (a.getCity().equals(city) && (a.getStartActivityDay() >= startActivityDay) && (a.getEndActivityDay() <= endActivityDay)){
				return a.getActivity();
			}
		}

		
		return activity;
	}



}
