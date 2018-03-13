package cardgame.cards.effects;

import java.util.List;
import java.util.Optional;

import cardgame.cards.Targetable;

public class DamageEffect extends Effect {

	private int damage;

	public DamageEffect(int damage) {
		super();
		isTargetRequired = true;
		setDamage(damage);
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getDamage() {
		return damage;
	}

	@Override
	public void realizeEffect(Optional<List<Targetable>> targets) {
		if (targets.isPresent()) {
			for (Targetable target : targets.get()) {
				target.attacked(damage);
			}
		}
	}

	@Override
	public String toString() {
		return "damage";
	}
}
