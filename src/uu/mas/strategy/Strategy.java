package uu.mas.strategy;

import jade.lang.acl.ACLMessage;
import uu.mas.game.GameResult;
import uu.mas.tournament.TournamentResult;

/**
 * Basis for a strategy and related behavior
 * @author Jurian
 *
 */
public interface Strategy {
	
	/**
	 * The desired action for a given strategy
	 * @return <code>True</code> to cooperate, <code>False</code> to defect.
	 */
	boolean strategyAction();
	
	/**
	 * Create the message in which the agent conveys its strategy
	 * @param message
	 * @return
	 */
	ACLMessage reply(ACLMessage message);
	
	/**
	 * Inform the agent of the result of the game
	 */
	void informRoundResult(ACLMessage message);
	
	void informGameEnd(GameResult result);
	void informGameStart();
	void informTournamentEnd(TournamentResult result);
	void informTournamentStart();
}
