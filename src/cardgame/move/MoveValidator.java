package cardgame.move;

import cardgame.board.IBoard;
import cardgame.cards.Card;
import cardgame.player.Player;

/**
 * @author Radek
 *
 *         Validates player action for correctness and availability with current
 *         game state. Validation does not influence game state.
 */
public class MoveValidator implements IMoveValidator {

	Player playerOne;
	Player playerTwo;
	private IBoard board;

	public void setCheckingGame(Player playerOne, Player playerTwo, IBoard board) {
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		this.board = board;
	}

	@Override
	public boolean checkCardPlay(Card card, Player player) {
		if (board.getPlayerBoard(player).size() >= 7) {
			return false;
		} else if (player.getCurrentMana() < card.getCost()) {
			return false;
		}
		return true;
	}

}
