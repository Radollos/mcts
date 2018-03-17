package cardgame.cards.effects;

import java.util.List;
import java.util.Optional;

import cardgame.cards.Targetable;

public abstract class Effect {

	protected boolean isTargetRequired;
	protected String name;
	protected Optional<EffectArea> area;

	public Effect(String name, EffectArea area) {
		this.name = name;
		this.area = Optional.ofNullable(area);
	}

	public abstract void realizeEffect(Optional<List<Targetable>> targets);

	public boolean isTargetRequired() {
		return isTargetRequired;
	}

	public String toString() {
		String areaStr = area.isPresent() ? area.get().name() : "";
		return name + " " + areaStr;
	}

}
