package uu.mas.player;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import uu.mas.message.MessageType;
import uu.mas.message.TournamentMessage;



/**
 * Intermediary for Jade's <code>Agent</code> class.
 * @author Jurian
 *
 */
@SuppressWarnings("serial")
public abstract class Player extends Agent implements Comparable<Player> {
	
	private boolean isRegistered = false;
	
	@Override
	protected void setup() {
		
		final DFAgentDescription template = new DFAgentDescription();
		final ServiceDescription sd = new ServiceDescription();
		sd.setType(TournamentAdministrator.SERVICE_DESC_TYPE);
		template.addServices(sd);
		
		// Try and register with the tournament administrator
		addBehaviour(new SimpleBehaviour() {
			
			@Override
			public void action() {
				try {
					final DFAgentDescription[] result = DFService.search(Player.this, template);
					
					if(result.length > 0) {
						ACLMessage msg = TournamentMessage.create(MessageType.AGENT_REGISTER);
						msg.addReceiver(result[0].getName());
						msg.setContent("Agent "+ this.getAgent().getLocalName() +" reporting in!");
						
						Player.this.send(msg);
						isRegistered = true;
					}
				} catch (FIPAException e) {
					e.printStackTrace();
				}
			}

			@Override
			public boolean done() {
				return isRegistered;
			}
		});
	}
	
	@Override
	public int compareTo(Player p2) {
		return this.getAID().compareTo(p2.getAID());	
	}
}
