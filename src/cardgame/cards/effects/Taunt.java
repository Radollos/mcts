package cardgame.cards.effects;

import java.util.List;
import java.util.Optional;

import cardgame.cards.Targetable;

public class Taunt extends Effect {

	public Taunt() {
		super("taunt", null);
		isTargetRequired = false;
	}

	@Override
	public void realizeEffect(Optional<List<Targetable>> targets) {
		return;
	}
}
