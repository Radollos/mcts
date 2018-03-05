package cardgame.cards;

import java.util.List;

import cardgame.cards.effects.Effect;

/**
 * @author Radek
 *
 */
public abstract class Card {

	protected String name;
	protected int cost;
	protected List<Effect> effects;

	public Card(String name, int cost, List<Effect> effects) {
		setName(name);
		setCost(cost);
		setEffects(effects);
	}

	public Card(Card card) {
		this(card.name, card.cost, card.effects);
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

	public List<Effect> getEffects() {
		return effects;
	}

	public void setEffects(List<Effect> effects) {
		this.effects = effects;
	}

	public boolean hasEffect(String effectName) {
		return effects.stream().anyMatch(e -> e.toString().equals("effectName"));
	}

	public abstract Card makeCopy();

	@Override
	public abstract String toString();
}
