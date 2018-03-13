package cardgame.simulation;

import java.util.Random;

import cardgame.Game;
import cardgame.player.Player;

public class RandomHeuristic extends Heuristic {

	public RandomHeuristic(Game game, Player actualPlayer) {
		super(game, actualPlayer);
	}

	@Override
	protected int calculateValue(Game actualGame) {
		Random random = new Random();
		return random.nextInt(1000);
	}

}
