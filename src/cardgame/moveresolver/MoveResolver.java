package cardgame.moveresolver;

import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import cardgame.board.IBoard;
import cardgame.boardmanager.BoardManager;
import cardgame.boardmanager.IBoardManager;
import cardgame.cards.Card;
import cardgame.cards.Minion;
import cardgame.cards.Spell;
import cardgame.cards.Targetable;
import cardgame.player.Player;

public class MoveResolver implements IMoveResolver {

	private Player playerOne;
	private Player playerTwo;
	private Player currentPlayer;

	private final IBoard board;
	private final IBoardManager boardManager;
	private final Timer turnTimer = new Timer();
	private final long timeForTurn = 60000;

	public MoveResolver(Player playerOne, Player playerTwo, IBoard board) {
		this.playerOne = playerOne;
		this.currentPlayer = this.playerTwo = playerTwo;
		this.board = board;
		this.boardManager = new BoardManager(board, playerOne, playerTwo);
	}

	@Override
	public void drawCard(Player player) {
		Optional<Card> card = board.drawCard(player);
		if (card.isPresent()) {
			player.addCardToHand(card.get());
		} else {
			player.dealFatiqueDamage();
		}
		boardManager.runBoardCheck();
	}

	@Override
	public void playCard(Card card, Player player) {
		if (player.payManaCost(card.getCost())) {
			player.removeCardFromHand(card);
			if (card instanceof Minion) {
				board.playCardOnBoard(player, (Minion) card);
			} else if (card instanceof Spell) {
				// TODO: add spell
			}
		}
		boardManager.runBoardCheck();
	}

	@Override
	public boolean checkCardPlay(Card card, Player player) {
		if (board.getPlayerBoard(player).size() >= 7) {
			return false;
		} else if (player.getCurrentMana() < card.getCost()) {
			return false;
		}
		return true;
	}

	@Override
	public void attackWithMinion(Minion minion, Targetable target) {
		int returnDamage = target.attacked(minion.getAttack());
		minion.attacked(returnDamage);
		boardManager.runBoardCheck();
	}

	@Override
	public void endTurn() {
		// TODO: inform current player that his turn has ended/started?
		currentPlayer = (currentPlayer == playerOne) ? playerTwo : playerOne;
		turnTimer.cancel();
		turnTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				endTurn();
			}
		}, timeForTurn);
		currentPlayer.startTurn();
	}

	@Override
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	@Override
	public List<Minion> getPlayerBoard(Player player) {
		return board.getPlayerBoard(player);
	}
}
