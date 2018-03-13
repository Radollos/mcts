package cardgame.cards.effects;

import java.util.List;
import java.util.Optional;

import cardgame.cards.Targetable;

public abstract class Effect {

	protected boolean isTargetRequired;

	public abstract void realizeEffect(Optional<List<Targetable>> targets);

	public boolean isTargetRequired() {
		return isTargetRequired;
	}

}
