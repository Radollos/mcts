package cardgame.cards;

import static logging.Messages.MINION_STATISTICS;

import java.util.List;

import cardgame.cards.effects.Effect;

/**
 * @author Radek
 *
 */
public class Minion extends Card implements Targetable {

	private int maxHealth;
	private int currentHealth;
	private int attack;

	public Minion(String name, int attack, int maxHealth, int cost, List<Effect> effects) {
		super(name, cost, effects);
		setMaxHealth(maxHealth);
		setCurrentHealth(maxHealth);
	}

	public Minion(Minion minion) {
		this(minion.name, minion.attack, minion.maxHealth, minion.cost, minion.effects);
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth > 0 ? maxHealth : 1;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth > maxHealth ? maxHealth : currentHealth;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack >= 0 ? attack : 0;
	}

	@Override
	public int attacked(int damage) {
		currentHealth -= damage;
		return attack;
	}

	@Override
	public void healed(int healValue) {
		currentHealth = currentHealth + healValue > maxHealth ? maxHealth : currentHealth + healValue;
	}

	public void setHealth(int hp) {
		currentHealth = hp;
	}

	@Override
	public String toString() {
		return System.out.format(MINION_STATISTICS, name, cost, currentHealth, maxHealth, attack).toString();
	}

	@Override
	public Card makeCopy() {
		return new Minion(this);
	}

}
