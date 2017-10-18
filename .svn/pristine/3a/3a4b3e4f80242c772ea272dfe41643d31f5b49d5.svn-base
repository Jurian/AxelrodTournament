package uu.mas.tournament;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jade.core.AID;
import jade.util.leap.Serializable;
import uu.mas.game.GameResult;

/**
 * Result of the tournament
 * @author Jurian
 *
 */
@SuppressWarnings("serial")
public class TournamentResult implements Iterable<GameResult>, Serializable {
	private final List<GameResult> results;
	
	public TournamentResult() {
		results = new ArrayList<>();
	}

	public void addResult(GameResult result) {
		results.add(result);
	}
	
	public List<GameResult> getResults() {
		return results;
	}

	public Map<AID, Integer> flatten() {
		Map<AID, Integer> test = new HashMap<>();
		
		for(GameResult gr : this) {
			if(test.containsKey(gr.getPlayer1())) {
				int score = test.get(gr.getPlayer1());
				test.put(gr.getPlayer1(), score + gr.getScorePlayer1());
			} else {
				test.put(gr.getPlayer1(), gr.getScorePlayer1());
			}
			
			if(test.containsKey(gr.getPlayer2())) {
				int score = test.get(gr.getPlayer2());
				test.put(gr.getPlayer2(), score + gr.getScorePlayer2());
			} else {
				test.put(gr.getPlayer2(), gr.getScorePlayer2());
			}
			
		}
		
		return test;
	}
	
	@Override
	public Iterator<GameResult> iterator() {
		return results.iterator();
	}
}
