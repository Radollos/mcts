package cardgame.cards;

/**
 * @author Radek
 *
 */
public class Spell extends Card {

	public Spell(String name, int cost) {
		super(name, cost);
	}

	public Spell(Spell spell) {
		this(spell.name, spell.cost);
	}

	@Override
	public String toString() {
		return name + ", " + cost + " mana";
	}

	@Override
	public Card makeCopy() {
		return new Spell(this);
	}
}
