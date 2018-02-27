package cardgame.infoaccessor;

import cardgame.board.IBoard;
import cardgame.player.Player;

public class InfoAccessor {

	Player playerOne;
	Player playerTwo;
	IBoard board;

	public InfoAccessor(Player playerOne, Player playerTwo, IBoard board) {
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		this.board = board;
	}

	// public void getPlayerInfo
}
