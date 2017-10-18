package uu.mas.game;

import jade.core.AID;
import jade.util.leap.Serializable;

/**
 * Result of a game between two players
 * 
 * @author Jurian
 *
 */
@SuppressWarnings("serial")
public class GameResult implements Serializable {

	private final AID player1, player2;
	private int scorePlayer1, scorePlayer2;

	public GameResult(AID player1, AID player2) {
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public GameResult(AID player1, AID player2, int score1, int score2) {
		this.player1 = player1;
		this.player2 = player2;
		this.scorePlayer1 = score1;
		this.scorePlayer2 = score2;
	}

	public int getScorePlayer1() {
		return scorePlayer1;
	}

	public int getScorePlayer2() {
		return scorePlayer2;
	}

	public void addScorePlayer1(int score) {
		this.scorePlayer1 += score;
	}

	public void addScorePlayer2(int score) {
		this.scorePlayer2 += score;
	}

	public AID getPlayer1() {
		return player1;
	}

	public AID getPlayer2() {
		return player2;
	}

	public String toString() {
		return player1.getLocalName() + ": " + scorePlayer1 + ", " + player2.getLocalName() + " " + scorePlayer2;
	}
}
