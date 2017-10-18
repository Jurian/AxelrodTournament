package uu.mas.strategy;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import uu.mas.game.GameResult;
import uu.mas.message.MessageType;
import uu.mas.message.TournamentMessage;
import uu.mas.tournament.TournamentResult;

/**
 * Takes care of basic message handling in a tournament
 * 
 * @author Jurian
 *
 */
@SuppressWarnings("serial")
public abstract class CyclicStrategy extends CyclicBehaviour implements Strategy {
	
	@Override
	public void action() {
		ACLMessage message = myAgent.receive();
		if (message != null) {
			switch (TournamentMessage.getType(message)) {
			case GAME_ROUND:
				ACLMessage reply = reply(message);
				myAgent.send(reply);
				break;
			case AGENT_COOPERATE:
			case AGENT_DEFECT:
				informRoundResult(message);
				break;
			case GAME_END:
				try {
					informGameEnd((GameResult) message.getContentObject());
				} catch (UnreadableException e) {
					e.printStackTrace();
				}
				break;
			case GAME_START:
				informGameStart();
				break;
			case TOURNAMENT_END:
				try {
					informTournamentEnd((TournamentResult) message.getContentObject());
				} catch (UnreadableException e) {
					e.printStackTrace();
				}
				break;
			case TOURNAMENT_START:
				informTournamentStart();
				break;
			default:
				break;
			}

		} else {
			block();
		}
	}

	@Override
	public ACLMessage reply(ACLMessage message) {
		boolean action = strategyAction();

		ACLMessage reply = message.createReply();
		TournamentMessage.setType(reply, action ? MessageType.AGENT_COOPERATE : MessageType.AGENT_DEFECT);
		
		return reply;
	}
}
