/**
 * 
 */
package platform1;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import utilities.JadeUtils;

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
	private static final List<Activity> listOfActivities = new ArrayList<>();

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
				answer = this.getInfoActivity((List<String>) msg.getContentObject());
				
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

	private String getInfoActivity(List<String> receivedData) {
		StringBuilder sb = new StringBuilder();
		String cityName = receivedData.get(JadeUtils.cityIndex);
		String scheduleDescriptionDates = receivedData.get(JadeUtils.scheduleDescriptionIndex);
		boolean found = false;		
		
    	sb.append(String.format("%s", "+-----------------------------------+"));
    	sb.append(String.format("| %s | %s | %s |\n","City", "Activity" ,"Calendar"));
    	sb.append(String.format("%s", "+-----------------------------------+"));
    	
		for (Activity a : this.listOfActivities) {
			if (a.getCity().equals(cityName) && a.getScheduleDescription().equals(scheduleDescriptionDates)){
		    	sb.append(String.format("| %s | %s | %s |\n", a.getCity(), a.getActivity() , a.getScheduleDescription()));
		    	found = true;
		    	break;
			}
		}

		// Activity non existant or not found
		if (!found) 
			sb.append(String.format("Activity required does not appear in the list of activities showed."));
		

		return sb.toString();
	}



}
