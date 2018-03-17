package cardgame.cards;

import static logging.Messages.SPELL_STATISTICS;

import java.util.Set;

import cardgame.cards.effects.Effect;
import logging.Messages;

/**
 * @author Radek
 *
 */
public class Spell extends Card {

	public Spell(String name, int cost, Set<Effect> effects) {
		super(name, cost, effects);
	}

	public Spell(Spell spell) {
		this(spell.name, spell.cost, spell.effects);
	}

	@Override
	public String toString() {
		return String.format(Messages.SPELL_STATISTICS, name, cost, effects.toString()).toString();
	}

	@Override
	public Card makeCopy() {
		return new Spell(this);
	}
}
