package cardgame.cards;

/**
 * @author Radek
 *
 */
public abstract class Card {

	protected String name;
	protected int cost;

	public Card(String name, int cost) {
		setName(name);
		setCost(cost);
	}

	public Card(Card card) {
		this(card.name, card.cost);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost >= 0 ? cost : 0;
	}

	public abstract Card makeCopy();

	@Override
	public abstract String toString();
}
