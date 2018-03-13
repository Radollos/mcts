package cardgame.simulation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cardgame.Game;
import cardgame.cards.Card;
import cardgame.cards.Minion;
import cardgame.cards.Targetable;
import cardgame.cards.effects.Effect;
import cardgame.move.Action;
import cardgame.move.Move;
import cardgame.player.Player;
import logging.Messages;

public class Node {

	private Heuristic heuristic;
	private Game actualGame;
	private Set<Move> movesToThis;
	private Set<Move> allPosibleMoves;
	private int value;

	public Node(final Game game, final Heuristic heuristic, final Set<Move> movesToThis) {
		this.actualGame = game;
		this.heuristic = heuristic;
		this.movesToThis = movesToThis;
		allPosibleMoves = new HashSet<Move>();

		calculateValue();
		genereteAllPossibleMoves();
		createChildren();
	}

	private void calculateValue() {
		value = heuristic.calculateValue(actualGame);
		if (value > heuristic.getTheBestNode().getValue()) {
			heuristic.setTheBestNode(this);
		}
	}

	private void genereteAllPossibleMoves() {
		final Player actualPlayer = heuristic.getActualPlayer();

		List<Minion> myMinionsOnBoard = actualGame.getPlayerBoard(actualPlayer);
		List<Targetable> targets = actualGame.getPossibleTargetsToAttact();
		for (Minion myMinion : myMinionsOnBoard) {
			allPosibleMoves.addAll(generatePossibleMoves(myMinion, targets, Action.ATTACK_WITH_MINION));
		}

		List<Card> myCardsInHand = actualPlayer.getCardsInHand();
		for (Card card : myCardsInHand) {
			allPosibleMoves.addAll(generatePossiblePlayCard(card));
		}
	}

	private Set<Move> generatePossibleMoves(Card card, List<Targetable> targets, Action action) {
		Set<Move> possibleMoves = new HashSet<Move>();
		for (Targetable target : targets) {
			possibleMoves.add(new Move(card, action, target));
		}

		return possibleMoves;
	}

	private Set<Move> generatePossiblePlayCard(Card card) {
		Set<Move> possibleMoves = new HashSet<Move>();

		for (Effect effect : card.getEffects()) {

			if (effect.toString().equals(Messages.HEAL_EFFECT)) {
				List<Targetable> allFigure = actualGame.getAllFigure();
				possibleMoves.addAll(generatePossibleMoves(card, allFigure, Action.HEAL));
			} else if (effect.toString().equals(Messages.DAMAGE_EFFECT)) {
				List<Targetable> allFigure = actualGame.getAllFigure();
				possibleMoves.addAll(generatePossibleMoves(card, allFigure, Action.DAMAGE));
			} else if (effect.toString().equals(Messages.MINION_DESTROY_EFFECT)) {
				List<Targetable> allMinions = actualGame.getAllMinions();
				possibleMoves.addAll(generatePossibleMoves(card, allMinions, Action.DESTROY_MINION));
			}
		}

		return possibleMoves;
	}

	private void createChildren() {
		for (Move move : allPosibleMoves) {
			Game gameCopy = new Game(actualGame);
			Set<Move> movesToNode = realizeMove(gameCopy, move);
			new Node(gameCopy, heuristic, movesToNode);
		}
	}

	private Set<Move> realizeMove(Game game, Move move) {
		if (game.doMove(move, heuristic.getActualPlayer())) {
			Set<Move> movesToNode = new HashSet<Move>();
			movesToNode.addAll(movesToThis);
			movesToNode.add(move);

			return movesToNode;
		}
		return null;
	}

	public Set<Move> getMoves() {
		return movesToThis;
	}

	public int getValue() {
		return value;
	}

}
