package cardgame.player;

import static logging.Messages.LINE_WITH_TEXT;
import static logging.Messages.PLAYER_STATISTICS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cardgame.cards.Card;
import cardgame.cards.Minion;
import cardgame.cards.Targetable;
import cardgame.move.IMoveResolver;

/**
 * @author Radek
 *
 */
public abstract class Player implements Targetable {

	protected String name;
	protected int currentHealth;
	protected int maxHealth;
	protected int currentMana = 0;
	protected int maxMana = 0;
	protected int nextFatigueDamage = 1;

	protected List<Card> cardsInHand = new ArrayList<>();
	protected List<Card> startingDeck = new LinkedList<>();

	protected IMoveResolver moveResolver;

	public Player(String name, int maxHealth, List<Card> startingDeck) {
		this.name = name;
		this.currentHealth = this.maxHealth = maxHealth;
		this.startingDeck = startingDeck;
	}

	public void nextTurn() {
		manageManaForNewTurn();
		drawCard();
		startTurn();
	}

	protected void manageManaForNewTurn() {
		if (maxMana < 10) {
			maxMana++;
			currentMana = maxMana;
		} else {
			currentMana = maxMana;
		}
	}

	public void connectToGame(IMoveResolver moveResolver) {
		this.moveResolver = moveResolver;
	}

	public void addCardToHand(Card card) {
		if (card != null && cardsInHand.size() < 10) {
			cardsInHand.add(card);
		}
	}

	public boolean removeCardFromHand(Card card) {
		return cardsInHand.remove(card);
	}

	public boolean payManaCost(int manaCost) {
		if (currentMana >= manaCost) {
			currentMana -= manaCost;
			return true;
		}
		return false;
	}

	public void dealFatiqueDamage() {
		attacked(nextFatigueDamage);
		nextFatigueDamage++;
	}

	public abstract void startTurn();

	protected void drawCard() {
		moveResolver.drawCard(this);
	}

	protected void playCard(Card card) {
		moveResolver.playCard(card, this);
	}

	protected void attackWithMinion(Minion minion, Targetable target) {
		moveResolver.attackWithMinion(minion, target);
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

	public int getCurrentHealth() {
		return currentHealth;
	}

	public int getCurrentMana() {
		return currentMana;
	}

	public List<Card> getStartingDeck() {
		return startingDeck;
	}

	public List<Card> getCardsInHand() {
		return cardsInHand;
	}

	public String toStringTop() {
		StringBuilder builder = new StringBuilder();
		// builder.append(System.out.format(LINE_WITH_TEXT, name));
		builder.append(name + "\n");
		builder.append(String.format(PLAYER_STATISTICS, currentMana, currentHealth, maxHealth));
		builder.append(String.format(LINE_WITH_TEXT, "Hand:"));
		for (Card card : cardsInHand) {
			builder.append(card);
			builder.append(" | ");
		}
		builder.append("\n");
		return builder.toString();
	}

	public String toStringBottom() {
		StringBuilder builder = new StringBuilder();
		
		for (Card card : cardsInHand) {
			builder.append(card);
			builder.append(" | ");
		}
		builder.append("\n");
		builder.append(String.format(LINE_WITH_TEXT, "Hand:"));
	
		builder.append(String.format(PLAYER_STATISTICS, currentMana, currentHealth, maxHealth));
		builder.append(name + "\n");
		// builder.append(System.out.format(LINE_WITH_TEXT, name));
		return builder.toString();
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return getName();
	}
}
