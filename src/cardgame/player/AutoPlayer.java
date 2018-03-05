package cardgame.player;

import java.util.List;

import cardgame.cards.Card;

/**
 * @author Radek
 *
 */
public class AutoPlayer extends Player {

	public AutoPlayer(String name, int maxHealth, List<Card> startingDeck) {
		super(name, maxHealth, startingDeck);
	}

	@Override
	public void startTurn() {
		// TODO Auto-generated method stub

	}

}
