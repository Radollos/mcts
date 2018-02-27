package cardgame.player;

import java.util.List;

import cardgame.cards.Card;
import cardgame.cards.Minion;
import cardgame.cards.Targetable;
import cardgame.moveresolver.IMoveResolver;
import logging.Messages;
import logging.MyLogger;

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
		// MyLogger.print(message);

	}

	private boolean handleUserInput(String input) {
		String[] trimmed = input.split(" ");
		switch (trimmed[0]) {
		case "play":
			// format: play <number of card from hand>
			int numberOfCard = Integer.valueOf(trimmed[1]);
			Card card = cardsInHand.get(numberOfCard);
			moveResolver.playCard(card, this);
			break;
		case "attack":
			// format: attack <number of attacking minion> <number of target (-1 for hero)>
			int numberOfMinion = Integer.valueOf(trimmed[1]);
			int numberOfTarget = Integer.valueOf(trimmed[2]);
			Minion minion = moveResolver.getPlayerBoard(this).get(numberOfMinion);
			Targetable target;
			if (numberOfTarget == -1) {
				target = this;
			} else {
				target = moveResolver.getPlayerBoard(this).get(numberOfTarget);
			}

			moveResolver.attackWithMinion(minion, target);
			break;
		case "end":
			moveResolver.endTurn();
			break;
		default:
			MyLogger.print(Messages.WRONG_COMMAND);
			return false;
		}
		return true;
	}
}
