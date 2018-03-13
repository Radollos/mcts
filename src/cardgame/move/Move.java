package cardgame.move;

import java.util.ArrayList;
import java.util.List;

import cardgame.cards.Card;
import cardgame.cards.Targetable;

public class Move {

	private Card moveIssuer;
	private Action action;
	private List<Targetable> targets;

	public Move(Card moveIssuer, Action action, List<Targetable> targets) {
		this.moveIssuer = moveIssuer;
		this.action = action;
		this.targets = targets;
	}

	public Move(Card moveIssuer, Action action, Targetable target) {
		this.moveIssuer = moveIssuer;
		this.action = action;
		targets = new ArrayList<Targetable>();
		targets.add(target);
	}

	public Action getAction() {
		return action;
	}

	public List<Object> getParameters() {
		List<Object> params = new ArrayList<Object>();
		if (moveIssuer != null) {
			params.add(moveIssuer);
		}
		if (targets != null && !targets.isEmpty()) {
			params.addAll(targets);
		}

		return params;
	}
}
