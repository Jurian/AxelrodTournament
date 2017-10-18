package uu.mas.game;

import java.io.IOException;
import java.util.Observable;

import jade.core.AID;
import uu.mas.player.TournamentAdministrator;
import uu.mas.tournament.TournamentOptions;

/**
 * A single game between two players, playing a game will 
 * result in a <code>GameResult</code> instance.
 * @author Jurian
 *
 */
public abstract class Game extends Observable {
	
	private final TournamentOptions options;
	private final TournamentAdministrator admin;
	private final AID player1, player2;
	

	public Game(TournamentOptions options, TournamentAdministrator admin, AID player1, AID player2) {
		this.options = options;
		this.admin = admin;
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public TournamentAdministrator getAdmin() {
		return admin;
	}

	public AID getPlayer1() {
		return player1;
	}

	public AID getPlayer2() {
		return player2;
	}
	
	public int[][][] getScoreMatrix(){
		return options.getScoreMatrix();
	};
	
	public abstract GameResult play() throws IOException;

	public TournamentOptions getOptions() {
		return options;
	}


}
