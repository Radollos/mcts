package cardgame.board;

import java.util.List;
import java.util.Optional;

import cardgame.cards.Card;
import cardgame.cards.Minion;
import cardgame.player.Player;

public interface IBoard {

	public void playMinionOnBoard(Player player, Minion minion);

	public void removeCardFromBoard(Player player, Minion minion);

	public Optional<Card> drawCard(Player player);

	public List<Minion> getPlayerBoard(Player player);
}
