package cardgame;

import java.util.ArrayList;
import java.util.List;

import cardgame.cards.Card;
import cardgame.cards.Minion;
import cardgame.player.ManualPlayer;
import cardgame.player.Player;

public class MainCardGame {

	public static void main(String[] args) {

		List<Card> cards = new ArrayList<>();
		cards.add(new Minion("M1", 1, 1, 1));
		cards.add(new Minion("M2", 3, 2, 2));
		cards.add(new Minion("M3", 2, 3, 2));
		cards.add(new Minion("M4", 3, 4, 3));
		cards.add(new Minion("M5", 5, 2, 3));
		cards.add(new Minion("M6", 4, 4, 4));
		cards.add(new Minion("M7", 4, 5, 5));
		cards.add(new Minion("M8", 5, 4, 5));
		cards.add(new Minion("M9", 6, 6, 6));
		cards.add(new Minion("M10", 2, 8, 4));

		Player playerOne = new ManualPlayer("playerOne", 20, cards);
		Player playerTwo = new ManualPlayer("playerTwo", 20, cards);
		Game game = new Game(playerOne, playerTwo);
	}

}
