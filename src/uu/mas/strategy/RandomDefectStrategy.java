package uu.mas.strategy;

import java.util.Random;

import jade.lang.acl.ACLMessage;
import uu.mas.game.GameResult;
import uu.mas.tournament.TournamentResult;

/**
 * This strategy has a 50% chance of defecting
 * @author Jurian
 *
 */
@SuppressWarnings("serial")
public class RandomDefectStrategy extends CyclicStrategy {

	private static final Random RANDOM = new Random();
	
	public boolean strategyAction() {
		return RANDOM.nextBoolean();
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
