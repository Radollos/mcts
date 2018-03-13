package cardgame.simulation;

import cardgame.Game;
import cardgame.player.Player;

public class AggressiveHeuristic extends Heuristic {

	public AggressiveHeuristic(Game game, Player actualPlayer) {
		super(game, actualPlayer);
	}

	@Override
	protected int calculateValue(Game actualGame) {
		Player actualPlayer = getActualPlayer();
		Player oponent = actualGame.getOponent(actualPlayer);

		int myHealth = actualPlayer.getCurrentHealth();
		int oponentHealth = oponent.getCurrentHealth();
		int myMinionsStats = caclucalteMinionsStats(actualPlayer);
		int oponentMinionsStats = caclucalteMinionsStats(oponent);

		return myHealth + myMinionsStats - oponentMinionsStats - oponentHealth * 100;
	}

}
