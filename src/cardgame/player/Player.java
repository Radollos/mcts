package cardgame.player;

import static logging.Messages.LINE_WITH_TEXT;
import static logging.Messages.PLAYER_STATISTICS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cardgame.cards.Card;
import cardgame.cards.Targetable;
import cardgame.moveresolver.IMoveResolver;

/**
 * @author Radek
 *
 */
public abstract class Player implements Targetable {

	private String name;
	private int currentHealth;
	private int maxHealth;
	private int currentMana = 0;
	private int maxMana = 0;
	private int nextFatigueDamage = 1;

	private List<Card> cardsInHand = new ArrayList<>();
	private List<Card> startingDeck = new LinkedList<>();

	private IMoveResolver moveResolver;

	public Player(String name, int maxHealth, List<Card> startingDeck, IMoveResolver moveResolver) {
		this.name = name;
		this.currentHealth = this.maxHealth = maxHealth;
		this.startingDeck = startingDeck;
		this.moveResolver = moveResolver;
	}

	public void nextTurn() {
		manageManaForNewTurn();
		drawCard();
		startTurn();
	}

	private void manageManaForNewTurn() {
		currentMana = maxMana = maxMana < 10 ? maxMana++ : maxMana;
	}

	public void connectToGame(IMoveResolver moveResolver) {
		this.moveResolver = moveResolver;
	}

	private void drawCard() {
		moveResolver.drawCard(this);
	}

	public void addCardToHand(Card card) {
		if (card != null && cardsInHand.size() <= 10) {
			cardsInHand.add(card);
		}
	}

	public void dealFatiqueDamage() {
		attacked(nextFatigueDamage);
		nextFatigueDamage++;
	}

	@Override
	public int attacked(int damage) {
		currentHealth -= damage;
		return 0;
	}

	@Override
	public void healed(int healValue) {
		currentHealth = currentHealth + healValue > maxHealth ? maxHealth : currentHealth + healValue;
	}

	public abstract void startTurn();

	public int getCurrentHealth() {
		return currentHealth;
	}

	public int getCurrentMana() {
		return currentMana;
	}

	public List<Card> getStartingDeck() {
		return startingDeck;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(System.out.format(LINE_WITH_TEXT, name));
		builder.append(System.out.format(PLAYER_STATISTICS, currentMana, currentHealth, maxHealth));
		for (Card card : cardsInHand) {
			builder.append("|");
			builder.append(card);
			builder.append("|");
		}
		return builder.toString();
	}
}
