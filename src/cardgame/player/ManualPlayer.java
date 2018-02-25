/**
 * 
 */
package cardgame.player;

import java.util.List;

import cardgame.cards.Card;
import cardgame.moveresolver.IMoveResolver;

/**
 * @author Radek
 *
 */
public class ManualPlayer extends Player {

	public ManualPlayer(String name, int maxHealth, List<Card> startingDeck, IMoveResolver moveResolver) {
		super(name, maxHealth, startingDeck, moveResolver);
	}

	@Override
	public void startTurn() {
		// TODO Auto-generated method stub

	}

}
