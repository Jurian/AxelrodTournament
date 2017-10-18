package uu.mas.message;

import jade.lang.acl.ACLMessage;

public enum MessageType {
	AGENT_COOPERATE(ACLMessage.ACCEPT_PROPOSAL),
	AGENT_DEFECT(ACLMessage.ACCEPT_PROPOSAL),
	AGENT_REGISTER(ACLMessage.INFORM),
	TOURNAMENT_START(ACLMessage.INFORM),
	TOURNAMENT_END(ACLMessage.INFORM),
	GAME_START(ACLMessage.INFORM),
	GAME_ROUND(ACLMessage.REQUEST),
	GAME_END(ACLMessage.INFORM),
	UNKNOWN(ACLMessage.UNKNOWN);
	
	final int performative;
	
	private MessageType(int performative) {
		this.performative = performative;
	}
	
	public final int getPerformative() {
		return performative;
	}
}
