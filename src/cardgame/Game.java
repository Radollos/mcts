package cardgame;

import cardgame.board.Board;
import cardgame.board.IBoard;
import cardgame.move.IMoveResolver;
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

	public void startGame() {
		moveResolver.endTurn();
	}
}
