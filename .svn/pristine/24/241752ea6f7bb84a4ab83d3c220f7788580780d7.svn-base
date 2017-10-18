package uu.mas.game;

import java.util.Iterator;
import java.util.stream.IntStream;

import com.google.common.collect.Iterators;

import jade.core.AID;
import uu.mas.player.TournamentAdministrator;
import uu.mas.tournament.TournamentOptions;

/**
 * Multiple games represented as a single game
 * @author Jurian
 *
 */
public abstract class IteratedGame extends Game implements Iterable<Integer> {

	private final int n;

	public IteratedGame(TournamentOptions options, TournamentAdministrator admin, AID player1, AID player2) {
		super(options, admin, player1, player2);
		this.n = options.getIterations();
	}

	@Override
	public Iterator<Integer> iterator() {
		Integer[] values = new Integer[n];
		IntStream.range(1,n+1).forEach(val -> values[val-1] = val);
		return Iterators.forArray(values);
	}
}
