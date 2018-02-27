package cardgame.cards;

import java.util.List;

import cardgame.cards.effects.Effect;

/**
 * @author Radek
 *
 */
public class Spell extends Card {

	public Spell(String name, int cost, List<Effect> effects) {
		super(name, cost, effects);
	}

	public Spell(Spell spell) {
		this(spell.name, spell.cost, spell.effects);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(name + ", " + cost + " mana" + ", [");

		for (Effect effect : effects) {
			builder.append(effect + "|");
		}
		return builder.toString();
	}

	@Override
	public Card makeCopy() {
		// TODO Auto-generated method stub
		return null;
	}
}
