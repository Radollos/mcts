package cardgame.cards;

import java.util.Set;

import cardgame.cards.effects.Effect;

/**
 * @author Radek
 *
 */
public abstract class Card {

	protected String name;
	protected int cost;
	protected Set<Effect> effects;

	public Card(String name, int cost, Set<Effect> effects) {
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

	public Set<Effect> getEffects() {
		return effects;
	}

	public void setEffects(Set<Effect> effects) {
		this.effects = effects;
	}

	public boolean hasEffect(String effectName) {
		for (Effect effect : effects) {
			if (effect.toString().equals(effectName)) {
				return true;
			}
		}
		return false;
	}

	public abstract Card makeCopy();

	@Override
	public abstract String toString();
}
