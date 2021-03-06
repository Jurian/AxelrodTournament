package uu.mas.strategy;

import jade.lang.acl.ACLMessage;
import uu.mas.game.GameResult;
import uu.mas.tournament.TournamentResult;

/**
 * A strategy that always cooperates no matter what
 * @author Jurian
 *
 */
@SuppressWarnings("serial")
public class AlwaysCooperateStrategy extends CyclicStrategy {

	public boolean strategyAction() {
		return true;
	}

	@Override
	public void informRoundResult(ACLMessage message) {
		// Do nothing
	}

	@Override
	public void informGameEnd(GameResult result) {}

	@Override
	public void informGameStart() {}

	@Override
	public void informTournamentEnd(TournamentResult result) {}

	@Override
	public void informTournamentStart() {}

}
