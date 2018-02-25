package cardgame;

import cardgame.board.Board;
import cardgame.board.IBoard;
import cardgame.moveresolver.IMoveResolver;
import cardgame.moveresolver.MoveResolver;
import cardgame.player.Player;

public class Game {

	private final IBoard board;

	private Player playerOne;
	private Player playerTwo;

	public Game(Player playerOne, Player playerTwo) {
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;

		board = new Board(playerOne, playerTwo);
		IMoveResolver moveResolver = new MoveResolver(playerOne, playerTwo, board);
		playerOne.connectToGame(moveResolver);
		playerTwo.connectToGame(moveResolver);
		moveResolver.endTurn();
	}
}
