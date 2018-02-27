package cardgame.board;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static logging.Messages.LINE;
import static logging.Messages.LINE_WITH_TEXT;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import cardgame.cards.Card;
import cardgame.cards.Minion;
import cardgame.player.Player;

public class Board implements IBoard {

	private Map<Player, List<Minion>> playersBoard = new HashMap<>();
	private Map<Player, List<Card>> playersDeck = new HashMap<>();

	public Board(Player playerOne, Player playerTwo) {
		if (playerOne == playerTwo || playerOne == null || playerTwo == null) {
			throw new IllegalArgumentException();
		}
		prepareBoard(playerOne, playerTwo);
		prepareDecks(playerOne, playerTwo);
	}

	private void prepareBoard(Player playerOne, Player playerTwo) {
		playersBoard.put(playerOne, new LinkedList<>());
		playersBoard.put(playerTwo, new LinkedList<>());
	}

	@SuppressWarnings("unchecked")
	private void prepareDecks(Player playerOne, Player playerTwo) {
		List<Card> deckOne = (List<Card>) copyCardList(playerOne.getStartingDeck());
		Collections.shuffle(deckOne);
		playersDeck.put(playerOne, deckOne);

		List<Card> deckTwo = (List<Card>) copyCardList(playerTwo.getStartingDeck());
		Collections.shuffle(deckTwo);
		playersDeck.put(playerTwo, deckTwo);
	}

	private List<? extends Card> copyCardList(List<? extends Card> list) {
		List<Card> copy = new LinkedList<>(list);
		// list.stream().forEach(e -> copy.add(e.makeCopy()));
		return copy;
	}

	@Override
	public void playCardOnBoard(Player player, Minion minion) {
		playersBoard.get(player).add(minion);
	}

	@Override
	public void removeCardFromBoard(Player player, Minion minion) {
		playersBoard.get(player).remove(minion);
	}

	@Override
	public Optional<Card> drawCard(Player player) {
		return playersDeck.get(player).size() > 0 ? of(playersDeck.get(player).remove(0)) : empty();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Minion> getPlayerBoard(Player player) {
		return (List<Minion>) copyCardList(playersBoard.get(player));
	}

	@Override
	public String toString() {
		Iterator<Player> it = playersBoard.keySet().iterator();
		return toStringTop(it.next()) + toStringBottom(it.next());
	}

	public String toStringTop(Player player) {
		StringBuilder builder = new StringBuilder();
		builder.append(player.toStringTop());
		builder.append(System.out.format(LINE_WITH_TEXT, "Hand:"));
		Iterator<Minion> iterator = playersBoard.get(player).iterator();
		for (int i = 0; iterator.hasNext(); i++) {
			Minion minion = iterator.next();
			builder.append("|" + i + ". ");
			builder.append(minion);

		}
		builder.append("|");
		builder.append(LINE);
		return builder.toString();
	}

	public String toStringBottom(Player player) {
		StringBuilder builder = new StringBuilder();
		builder.append(LINE);
		Iterator<Minion> iterator = playersBoard.get(player).iterator();
		for (int i = 0; iterator.hasNext(); i++) {
			Minion minion = iterator.next();
			builder.append("|" + i + ". ");
			builder.append(minion);
		}
		builder.append("|");
		builder.append(System.out.format(LINE_WITH_TEXT, "Hand:"));
		builder.append(player.toStringBottom());
		return builder.toString();
	}
}
