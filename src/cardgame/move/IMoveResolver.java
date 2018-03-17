package cardgame.move;

import java.util.List;
import java.util.Optional;

import cardgame.board.IBoard;
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

	public List<Targetable> getPossibleTargets(Card card);

	public boolean realizeAction(Player issuingPlayer, Action action, List<Object> parameters);

	public List<Targetable> getPossibleTargetsToAttact();

	public List<Targetable> getAllFigure();

	public List<Targetable> getAllMinions();

	public void startGame();

	public IBoard getBoard();
}
