package cardgame.infoaccessor;

import cardgame.board.Board;
import cardgame.player.Player;

public interface IInfoAccessor {

	public Player getOtherPlayer(Player askingPlayer);

	public Board getOtherPlayerBoard(Player askingPlayer);

}
