package uu.mas.strategy;

import java.util.Random;

import jade.lang.acl.ACLMessage;
import uu.mas.game.GameResult;
import uu.mas.message.TournamentMessage;
import uu.mas.tournament.TournamentResult;

@SuppressWarnings("serial")
public class TitForTatRandomDefectStrategy extends CyclicStrategy {

	private boolean previouslyDefected;
	private static final Random RANDOM = new Random();
	
	public boolean strategyAction() {
		if (RANDOM.nextDouble() > 0.1) {
			return !previouslyDefected;
		}
		return false;
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