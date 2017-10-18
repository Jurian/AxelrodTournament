package uu.mas.strategy;

import jade.lang.acl.ACLMessage;
import uu.mas.game.GameResult;
import uu.mas.message.TournamentMessage;
import uu.mas.tournament.TournamentResult;

/**
 * Basic implementation of Tit For Tat. Will cooperate at first 
 * and then copy the previous move of the other player.
 * @author Jurian
 *
 */
@SuppressWarnings("serial")
public class TitForTatStrategy extends CyclicStrategy {

	private boolean previouslyDefected;

	public boolean strategyAction() {
		return !previouslyDefected;
	}

	@Override
	public void informRoundResult(ACLMessage message) {
		switch(TournamentMessage.getType(message)) {
		case AGENT_COOPERATE:
			previouslyDefected = false;
			break;
		case AGENT_DEFECT:
			previouslyDefected = true;
			break;
		default:
			break;
		}
	}

	@Override
	public void informGameEnd(GameResult result) {
		previouslyDefected = false;
	}

	@Override
	public void informGameStart() {
		previouslyDefected = false;
	}

	@Override
	public void informTournamentEnd(TournamentResult result) {}

	@Override
	public void informTournamentStart() {}


}