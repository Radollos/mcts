package cardgame.boardmanager;

import java.util.List;

import cardgame.board.IBoard;
import cardgame.cards.Minion;
import cardgame.player.Player;

/**
 * @author Radek
 *
 *         Responsible for 'cleaning' game board from dead minions and checking
 *         for game end (any player dead)
 */
public class BoardManager implements IBoardManager {

	private final IBoard board;
	private final Player playerOne;
	private final Player playerTwo;

	public BoardManager(IBoard board, Player playerOne, Player playerTwo) {
		this.board = board;
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
	}

	@Override
	public void runBoardCheck() {
		checkEndOfGame();
		checkMinionsDead();
	}

	private boolean checkEndOfGame() {
		if (playerOne.getCurrentHealth() <= 0) {
			// TODO: react on end game
			return true;
		} else if (playerTwo.getCurrentHealth() <= 0) {

			return true;
		}
		return false;
	}

	private void checkMinionsDead() {
		List<Minion> cardsOnBoard = board.getPlayerBoard(playerOne);
		cardsOnBoard.stream().filter(e -> e.getCurrentHealth() <= 0)
				.forEach(e -> board.removeCardFromBoard(playerOne, e));

		cardsOnBoard = board.getPlayerBoard(playerTwo);
		cardsOnBoard.stream().filter(e -> e.getCurrentHealth() <= 0)
				.forEach(e -> board.removeCardFromBoard(playerTwo, e));
	}
}
