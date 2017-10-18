package uu.mas.game;

import java.io.IOException;
import java.util.Random;

import jade.core.AID;
import jade.lang.acl.ACLMessage;
import uu.mas.message.MessageType;
import uu.mas.message.TournamentMessage;
import uu.mas.player.TournamentAdministrator;
import uu.mas.tournament.TournamentOptions;

public class PrisonersDilemmaIteratedGame extends IteratedGame {

	private static final Random RANDOM = new Random();
	
	public PrisonersDilemmaIteratedGame(TournamentOptions options, TournamentAdministrator admin, AID player1, AID player2) {
		super(options, admin, player1, player2);
	}

	@SuppressWarnings("unused")
	@Override
	public GameResult play() throws IOException {
		GameResult result = new GameResult(getPlayer1(), getPlayer2());
		
		final ACLMessage msg_start = TournamentMessage.create(MessageType.GAME_START);
		msg_start.addReceiver(getPlayer1());
		msg_start.addReceiver(getPlayer2());
		getAdmin().send(msg_start);
		
		for(int game : this) {
			
			// Send the players a message asking them for their cooperation
			final ACLMessage msg1 = TournamentMessage.create(MessageType.GAME_ROUND);
			msg1.addReceiver(getPlayer1());
			getAdmin().send(msg1);
			ACLMessage reply1 =  getAdmin().blockingReceive();
			MessageType errorReply1 = introduceError(TournamentMessage.getType(reply1));
			
			final ACLMessage msg2 = TournamentMessage.create(MessageType.GAME_ROUND);
			msg2.addReceiver(getPlayer2());
			getAdmin().send(msg2);
			ACLMessage reply2 =  getAdmin().blockingReceive();
			MessageType errorReply2 = introduceError(TournamentMessage.getType(reply2));
			
			// Calculate and log the result of this prisoners dilemma game
			
			int cooperate1 = errorReply1 == MessageType.AGENT_COOPERATE ? 0 : 1;
			int cooperate2 = errorReply2 == MessageType.AGENT_COOPERATE ? 0 : 1;
			
			result.addScorePlayer1(getScoreMatrix()[cooperate1][cooperate2][0]);
			result.addScorePlayer2(getScoreMatrix()[cooperate1][cooperate2][1]);
			
			// Inform the players of the result
			
			ACLMessage resultMsg1 = TournamentMessage.create(errorReply2);
			resultMsg1.addReceiver(getPlayer1());
			
			ACLMessage resultMsg2 = TournamentMessage.create(errorReply1);
			resultMsg2.addReceiver(getPlayer2());
			
			getAdmin().send(resultMsg1);
			getAdmin().send(resultMsg2);
			
			System.out.println(getPlayer1().getLocalName() + ": " + result.getScorePlayer1()
			+ " " + getPlayer2().getLocalName() + ": " + result.getScorePlayer2());
		}
		
		final ACLMessage msg_end = TournamentMessage.create(MessageType.GAME_END);
		msg_end.addReceiver(getPlayer1());
		msg_end.addReceiver(getPlayer2());
		msg_end.setContentObject(result);
		getAdmin().send(msg_end);
		
		setChanged();
		// Maybe change to RoundResult or something
		notifyObservers(result);
		
		return result;
	}

	private MessageType introduceError(MessageType reply) {
		// For an error rate of 0.6, i.e. 60%: we want true if nextDouble is in the range of 0 to 0.6
		final boolean error = RANDOM.nextDouble() <= getOptions().getError();

		switch (reply) {
		case AGENT_COOPERATE:
			if (error)
				return MessageType.AGENT_DEFECT;
			else
				return reply;
		case AGENT_DEFECT:
			if (error)
				return MessageType.AGENT_COOPERATE;
			else
				return reply;
		default:
			// Don't mess with messages that are not defect or cooperate
			return reply;
		}
	}
}
