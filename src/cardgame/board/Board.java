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
	private Player currentPlayer;
	private Player enemyPlayer;

	public Board(Player playerOne, Player playerTwo) {
		if (playerOne == playerTwo || playerOne == null || playerTwo == null) {
			throw new IllegalArgumentException();
		}
		currentPlayer = playerOne;
		enemyPlayer = playerTwo;
		prepareBoard(playerOne, playerTwo);
		prepareDecks(playerOne, playerTwo);
		addCardsToHand(playerOne, 3);
		addCardsToHand(playerTwo, 4);
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
	
	private void addCardsToHand(Player player, int numberOfCards) {
		List<Card> startingDeck = player.getStartingDeck();
		Collections.shuffle(startingDeck);
		
		for (int i = 0; i < numberOfCards; i++) {
			Optional<Card> card = drawCard(player);
			if (card.isPresent()) {
				player.addCardToHand(card.get());
			}
		}
	}

	private List<? extends Card> copyCardList(List<? extends Card> list) {
		List<Card> copy = new LinkedList<>();
		list.stream().forEach(e -> copy.add(e.makeCopy()));
		return copy;
	}

	@Override
	public void playMinionOnBoard(Player player, Minion minion) {
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
//		Iterator<Player> it = playersBoard.keySet().iterator();
		return toStringTop(enemyPlayer) + toStringBottom(currentPlayer);
	}

	private String toStringTop(Player player) {
		StringBuilder builder = new StringBuilder();
		builder.append(player.toStringTop());
		Iterator<Minion> iterator = playersBoard.get(player).iterator();
		builder.append("\n");
		builder.append(String.format(LINE_WITH_TEXT, "Board:"));
		for (int i = 0; iterator.hasNext(); i++) {
			Minion minion = iterator.next();
			builder.append(" | " + i + ". ");
			builder.append(minion);
		}
		
		builder.append(LINE);
		return builder.toString();
	}

	private String toStringBottom(Player player) {
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		Iterator<Minion> iterator = playersBoard.get(player).iterator();
		
		for (int i = 0; iterator.hasNext(); i++) {
			Minion minion = iterator.next();
			builder.append(" | " + i + ". ");
			builder.append(minion);
		}
		builder.append("\n");
		builder.append(String.format(LINE_WITH_TEXT, "Board:"));
		builder.append("\n");
		builder.append(player.toStringBottom());
		return builder.toString();
	}
	
	public void setCurrentPlayer(Player player) {
		currentPlayer = player;
	}
	
	public void setEnemyPlayer(Player player) {
		enemyPlayer = player;
	}
}
