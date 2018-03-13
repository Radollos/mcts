package cardgame.simulation;

import java.util.Set;

import cardgame.Game;
import cardgame.cards.Minion;
import cardgame.move.Move;
import cardgame.player.Player;

public abstract class Heuristic {

	protected Game actualGame;
	protected Player actualPlayer;
	protected Node theBestNode;

	public Heuristic(final Game game, final Player actualPlayer) {
		this.actualPlayer = actualPlayer;
		actualGame = new Game(game);
	}

	public Player getActualPlayer() {
		return actualPlayer;
	}

	public Node getTheBestNode() {
		return theBestNode;
	}

	public void setTheBestNode(Node node) {
		theBestNode = node;
	}

	public Set<Move> getTheBestMoves() {
		return theBestNode.getMoves();
	}

	protected int caclucalteMinionsStats(Player player) {
		int stats = 0;
		for (Minion minion : actualGame.getPlayerBoard(player)) {
			stats += minion.getCurrentHealth();
			stats += minion.getAttack();
		}
		return stats;
	}

	protected abstract int calculateValue(Game actualGame);
}
