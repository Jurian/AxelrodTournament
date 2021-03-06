package uu.mas.strategy;

import jade.lang.acl.ACLMessage;
import uu.mas.game.GameResult;
import uu.mas.message.MessageType;
import uu.mas.message.TournamentMessage;
import uu.mas.tournament.TournamentResult;

@SuppressWarnings("serial")
public class MajorityStrategy extends CyclicStrategy {

	private int amountCooperate = 0;
	private int amountGames = 0;
	private boolean doCooperate = true;
	
	public boolean strategyAction() {
		return doCooperate;
	}

	@Override
	public void informRoundResult(ACLMessage message) {
		if (TournamentMessage.getType(message) == MessageType.AGENT_COOPERATE){
			amountCooperate++;
		}
		amountGames++;
		//System.out.println(amountCooperate);
		if(amountCooperate >= amountGames - amountCooperate)
			doCooperate = true;
		else
			doCooperate = false;
	}

	@Override
	public void informGameEnd(GameResult result) {
		//amountCooperate = 0;
		//amountGames = 0;
		//doCooperate = true;
	}

	@Override
	public void informGameStart() {
		amountCooperate = 0;
		amountGames = 0;
		doCooperate = true;
	}

	@Override
	public void informTournamentEnd(TournamentResult result) {}

	@Override
	public void informTournamentStart() {}


}