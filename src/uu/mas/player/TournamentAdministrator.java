package uu.mas.player;

import java.io.IOException;
import java.util.Map.Entry;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import uu.mas.message.MessageType;
import uu.mas.message.TournamentMessage;
import uu.mas.tournament.Tournament;
import uu.mas.tournament.TournamentResult;

@SuppressWarnings("serial")
public class TournamentAdministrator extends Agent {
	
	public static final String MESSAGE_START = "start";
	public static final	String MESSAGE_REGISTER = "register";
	
	public static final String SERVICE_DESC_TYPE = "tournament-admin";
	public static final String SERVICE_DESC_NAME = "JADE-tournament";
	
	private Tournament tournament;
	private TournamentResult result; 

	public Tournament getTournament(){
		return tournament;
	}
	
	@Override
	protected void setup() {
		tournament = new Tournament();

		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType(SERVICE_DESC_TYPE);
		sd.setName(SERVICE_DESC_NAME);
		
		dfd.addServices(sd);
		
		try {
			DFService.register(this, dfd);
		} catch (FIPAException e) {
			e.printStackTrace();
		}
		
		addBehaviour(new CyclicBehaviour() {

			@Override
			public void action() {
				ACLMessage message = myAgent.receive();
				if (message != null) {
					
					MessageType type = TournamentMessage.getType(message);
					
					if(type != null) {
						
						switch(type) {
						case TOURNAMENT_START:
							System.out.println("Starting the tournament");
							try {
								result = tournament.start(TournamentAdministrator.this);
								for(Entry<AID, Integer> gr : result.flatten().entrySet()) {
									System.out.println(gr.getKey().getLocalName() + ": " + gr.getValue());
								}
							} catch (IOException e) {
								e.printStackTrace();
							}

							break;
						case AGENT_REGISTER:
							tournament.addPlayer(message.getSender());
							break;
						default:
							break;
						}
					}
				} else {
					block();
				}
			}
		});
	}
	
	@Override
	protected void takeDown() {
		try {
			DFService.deregister(this);
		} catch (FIPAException e) {
			e.printStackTrace();
		}
	}
}
