package cardgame.infoaccessor;

import cardgame.board.Board;
import cardgame.board.IBoard;
import cardgame.player.Player;

public class InfoAccessor implements IInfoAccessor {

	public static final IInfoAccessor infoAccessor = new InfoAccessor();

	Player playerOne;
	Player playerTwo;
	IBoard board;

	private InfoAccessor() {

	}

	@Override
	public Player getOtherPlayer(Player askingPlayer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Board getOtherPlayerBoard(Player askingPlayer) {
		// TODO Auto-generated method stub
		return null;
	}

	// public void getPlayerInfo
}
