package cardgame.moveresolver;

import cardgame.cards.Card;
import cardgame.cards.Minion;
import cardgame.cards.Targetable;
import cardgame.player.Player;

public interface IMoveResolver {

	public void drawCard(Player player);

	public void playCard(Card card, Player player);

	public void attackWithMinion(Minion minion, Targetable target);

	public void endTurn();

	public Player getCurrentPlayer();
}
