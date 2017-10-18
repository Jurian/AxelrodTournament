package uu.mas.tournament;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import uu.mas.game.Game;
import uu.mas.game.GameResult;
import uu.mas.game.PrisonersDilemmaIteratedGame;
import uu.mas.gui.ViewPort;
import uu.mas.message.MessageType;
import uu.mas.message.TournamentMessage;
import uu.mas.player.TournamentAdministrator;

/**
 * 
 * @author Jurian
 *
 */
public class Tournament {

	private final List<AID> players;
	private final TournamentOptions options;
	private final ViewPort viewPort;
	
	public Tournament() {
		this.players = new ArrayList<>();
		this.options = new TournamentOptions();
		this.viewPort = new ViewPort();
		this.viewPort.show();
		
		/**
		 * TODO: Somehow make games use this instead of hard-coded values
		 * TODO: Maybe make a GUI to set these values
		 */
		options.setIterations(200);
		options.setError(0.0);
		options.setScoreMatrix(new int[][][]{
			{{2,2}, {0,3}},
			{{3,0}, {1,1}}
		});
	}

	public List<AID> getPlayers() {
		return players;
	}
	
	public void addPlayer(AID player) {
		this.players.add(player);
	}
	
	public TournamentResult start(TournamentAdministrator admin) throws IOException {
		TournamentResult result = new TournamentResult();
		
		// Inform all the players that the tournament is about to start
		final ACLMessage msg_start = TournamentMessage.create(MessageType.TOURNAMENT_START);
		for(AID p : players) {
			msg_start.addReceiver(p);
		}
		admin.send(msg_start);
			
		for(int i = 0; i < players.size(); i++)
		{
			for(int j = i + 1; j < players.size(); j++)
			{
				Game game = new PrisonersDilemmaIteratedGame(options, admin, players.get(i), players.get(j));
				
				game.addObserver(viewPort.getChart1());
				game.addObserver(viewPort.getChart2());
				
				GameResult gm = game.play();
				System.out.println(gm.getScorePlayer1() + ", " + gm.getScorePlayer2());
				//viewPort.update(gm);
				result.addResult(gm);
			}
		}
		
		// Inform all the players that the tournament has ended
		final ACLMessage msg_end = TournamentMessage.create(MessageType.TOURNAMENT_END);
		for(AID p : players) {
			msg_end.addReceiver(p);
		}
		msg_end.setContentObject(result);
		admin.send(msg_end);
		
		return result;
	}

	public TournamentOptions getOptions() {
		return options;
	}
}
