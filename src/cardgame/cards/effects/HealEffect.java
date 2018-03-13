package cardgame.cards.effects;

import java.util.List;
import java.util.Optional;

import cardgame.cards.Targetable;

public class HealEffect extends Effect {

	private int healValue;

	public HealEffect(int healValue) {
		super();
		isTargetRequired = true;
		setHealValue(healValue);
	}

	public void setHealValue(int healValue) {
		this.healValue = healValue;
	}

	public int gethealValue() {
		return healValue;
	}

	@Override
	public void realizeEffect(Optional<List<Targetable>> targets) {
		if (targets.isPresent()) {
			for (Targetable target : targets.get()) {
				target.healed(healValue);
			}
		}
	}

	@Override
	public String toString() {
		return "heal";
	}

}
