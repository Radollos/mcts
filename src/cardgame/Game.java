package cardgame;

import java.util.List;

import cardgame.board.Board;
import cardgame.board.IBoard;
import cardgame.cards.Minion;
import cardgame.cards.Targetable;
import cardgame.move.IMoveResolver;
import cardgame.move.Move;
import cardgame.move.MoveResolver;
import cardgame.player.Player;

public class Game {

	private final IBoard board;
	private final IMoveResolver moveResolver;

	private Player playerOne;
	private Player playerTwo;

	public Game(Player playerOne, Player playerTwo) {
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		board = new Board(playerOne, playerTwo);
		moveResolver = new MoveResolver(playerOne, playerTwo, board);
		playerOne.connectToGame(moveResolver);
		playerTwo.connectToGame(moveResolver);
	}

	public Game(Game game) {
		this.playerOne = game.playerOne;
		this.playerTwo = game.playerTwo;
		this.board = game.board;
		this.moveResolver = game.moveResolver;
	}

	public Player getPlayerOne() {
		return playerOne;
	}

	public Player getPlayerTwo() {
		return playerTwo;
	}

	public Player getOponent(Player actualPlayer) {
		return playerOne == actualPlayer ? playerTwo : playerOne;
	}

	public List<Minion> getPlayerBoard(Player player) {
		return board.getPlayerBoard(player);
	}

	public boolean doMove(Move move, Player player) {
		return moveResolver.realizeAction(player, move.getAction(), move.getParameters());
	}

	public List<Targetable> getPossibleTargetsToAttact() {
		return moveResolver.getPossibleTargetsToAttact();
	}

	public List<Targetable> getAllFigure() {
		return moveResolver.getAllFigure();
	}

	public List<Targetable> getAllMinions() {
		return moveResolver.getAllMinions();
	}

	public void startGame() {
		moveResolver.endTurn();
	}
}
