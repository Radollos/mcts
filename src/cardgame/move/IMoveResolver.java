package cardgame.move;

import java.util.List;

import cardgame.cards.Card;
import cardgame.cards.Minion;
import cardgame.cards.Targetable;
import cardgame.player.Player;

public interface IMoveResolver {

	public void drawCard(Player player);

	public boolean playCard(Card card, Player player);

	public void attackWithMinion(Minion minion, Targetable target);

	public void endTurn();

	public Player getCurrentPlayer();

	public List<Minion> getPlayerBoard(Player player);
}
