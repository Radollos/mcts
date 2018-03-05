package cardgame.move;

import java.util.List;
import java.util.Optional;

import cardgame.cards.Card;
import cardgame.cards.Minion;
import cardgame.cards.Targetable;
import cardgame.player.Player;

public interface IMoveResolver {

	public void drawCard(Player player);

	public boolean playCard(Card card, Player player);

	public boolean playCard(Card card, Player player, Optional<List<Targetable>> targets);

	public void attackWithMinion(Minion minion, Targetable target);

	public void realizeCardEffects(Card card, Optional<List<Targetable>> targets);

	public void endTurn();

	public Player getCurrentPlayer();

	public Player getEnemyPlayer();

	public List<Minion> getPlayerBoard(Player player);
}
