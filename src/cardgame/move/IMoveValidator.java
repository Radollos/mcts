package cardgame.move;

import cardgame.cards.Card;
import cardgame.player.Player;

public interface IMoveValidator {

	public boolean checkCardPlay(Card card, Player player);

}
