package uu.mas.message;

import jade.lang.acl.ACLMessage;

public class TournamentMessage {

	public static final String MSG_TYPE = "message_type";
	
	public static ACLMessage create(MessageType type) {
		
		ACLMessage msg = new ACLMessage(type.getPerformative());
		msg.addUserDefinedParameter(MSG_TYPE, type.toString());
		return msg;
		
	}
	
	public static void setType(ACLMessage msg, MessageType type) {
		msg.setPerformative(type.getPerformative());
		msg.addUserDefinedParameter(MSG_TYPE,type.toString());
	}
	
	public static MessageType getType(ACLMessage msg) {
		String msgType = msg.getUserDefinedParameter(MSG_TYPE);
		
		if(msgType == null) return MessageType.UNKNOWN;
		
		return MessageType.valueOf(msgType);
	}
}
