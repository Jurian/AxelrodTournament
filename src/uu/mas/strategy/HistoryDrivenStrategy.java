package uu.mas.strategy;

import java.util.ArrayList;
import java.util.HashMap;

import jade.lang.acl.ACLMessage;
import uu.mas.message.MessageType;
import uu.mas.message.TournamentMessage;

/**
 * 
 * @author Kelvin
 *
 */
@SuppressWarnings("serial")
public class HistoryDrivenStrategy extends TitForTatStrategy {

	private boolean myAction;
	private HashMap<Boolean, ArrayList<Boolean>> map;
	
	private int turn;
	private int[][][] scoreMatrix;
	
	public HistoryDrivenStrategy()
	{
		map = new HashMap<>();
		turn = 0;
		
		scoreMatrix = new int[][][]{
			{{2,2}, {0,3}},
			{{3,0}, {1,1}}
		};
	}
	@Override
	public boolean strategyAction() {
		if(turn <= 10)
		{
			boolean result = super.strategyAction();
			
			myAction = result;
			return result;
		}
		else
		{
			boolean result = super.strategyAction();
			
			ArrayList<Boolean> lastChoicesOpponentWhenAction = new ArrayList<>();
			
			int history_size = map.get(result).size();
			int countTrues = 0;
			for(int i = map.get(result).size() - 1; i >= history_size - 10 && i >= 0; i--)
			{
				Boolean got = map.get(result).get(i);
				lastChoicesOpponentWhenAction.add(got);
				if(got)
					countTrues++;
				
			}
			
			int player = result ? 0 : 1;
			int opponent;
			//System.out.println("Changed by history");
			//System.out.println("Count trues: " + countTrues);
			
			if(countTrues >= 8)
			{
				opponent = 1;
			} 
			else if(countTrues <= 2)
			{
				opponent = 0;
			}
			else
				return super.strategyAction();
			
			int player_score1 = scoreMatrix[player][opponent][0];
			int player_score2 = scoreMatrix[!result ? 0 : 1][opponent][0];
			//int opponent_score = scoreMatrix[player][opponent][1];
			if((countTrues >= 8 || countTrues <= 2) && player_score1 < player_score2)
			{
				//System.out.println("Swapped result");
				myAction = !result;
				return !result;
			}
			myAction = result;
			return result;
		}
	}

	@Override
	public void informRoundResult(ACLMessage message) {
		

		MessageType type = TournamentMessage.getType(message);
		
		boolean opponentAction = false;

		switch (type) {
		case AGENT_COOPERATE:
			opponentAction = true;
			break;

		case AGENT_DEFECT:
			opponentAction = false;
			break;

		default:
			break;
		}

		if (!map.containsKey(myAction))
			map.put(myAction, new ArrayList<>());
		turn++;
		map.get(myAction).add(opponentAction);

		super.informRoundResult(message);
		
	}

	@Override
	public void informGameStart() {
		map.clear();
		turn = 0;
	}

}
