package cardgame.move;

import static logging.Messages.TAUNT_ON_THE_BOARD;
import static logging.MyLogger.print;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import cardgame.board.IBoard;
import cardgame.boardmanager.BoardManager;
import cardgame.boardmanager.IBoardManager;
import cardgame.cards.Card;
import cardgame.cards.Minion;
import cardgame.cards.Targetable;
import cardgame.cards.effects.Effect;
import cardgame.player.Player;

/**
 * @author Radek
 *
 *         Responsible for resolving players' actions. First every action is
 *         validated for correctness with MoveValidator, if move is correct it's
 *         realized and board check is executed (remove dead minions, end game
 *         if necessary). Moreover, it monitors current player and his turn
 *         timer (60s). If player does not end turn before timer expires, his
 *         turn is ended forcefully.
 */
public class MoveResolver implements IMoveResolver {

	private final long timeForTurn = 60000;
	private final IBoard board;
	private final IBoardManager boardManager;

	private Player playerOne;
	private Player playerTwo;
	private Player currentPlayer;

	private Timer turnTimer = new Timer();

	public MoveResolver(Player playerOne, Player playerTwo, IBoard board) {
		this.playerOne = playerOne;
		this.currentPlayer = this.playerTwo = playerTwo;
		this.board = board;
		this.boardManager = new BoardManager(board, playerOne, playerTwo);
	}

	public boolean realizeAction(Player issuingPlayer, Action action, List<Object> parameters) {
		if (currentPlayer != issuingPlayer) {
			return false;
		}
		switch (action) {
		case PLAY_CARD:
			if (parameters.size() > 0 && parameters.get(0) instanceof Card) {
				return playCard((Card) parameters.get(0), issuingPlayer);
			} else {
				return false;
			}
		case ATTACK_WITH_MINION:

			break;
		}

		return true;
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
	public boolean playCard(Card card, Player player) {
		if (player.payManaCost(card.getCost()) && player.removeCardFromHand(card)) {
			if (card instanceof Minion) {
				board.playMinionOnBoard(player, (Minion) card);
				boardManager.runBoardCheck();
			}
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean playCard(Card card, Player player, Optional<List<Targetable>> targets) {
		if (isTargetsValid(card, targets)) {
			playCard(card, player);
			realizeCardEffects(card, targets);
			boardManager.runBoardCheck();
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void attackWithMinion(Minion minion, Targetable target) {
		if (isAttactCorrect(minion, target)) {
			int returnDamage = target.attacked(minion.getAttack());
			minion.attacked(returnDamage);
			boardManager.runBoardCheck();
		}
	}

	@Override
	public void endTurn() {
		// TODO: inform current player that his turn has ended/started?
		currentPlayer = (currentPlayer == playerOne) ? playerTwo : playerOne;
		turnTimer.cancel();
		turnTimer = new Timer();
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
	public Player getEnemyPlayer() {
		return currentPlayer == playerOne ? playerTwo : playerOne;
	}

	@Override
	public void realizeCardEffects(Card card, Optional<List<Targetable>> targets) {
		for (Effect effect : card.getEffects()) {
			effect.realizeEffect(targets);
			boardManager.runBoardCheck();
		}
	}

	private boolean checkCardPlay(Card card, Player player) {
		if (board.getPlayerBoard(player).size() >= 7) {
			return false;
		} else if (player.getCurrentMana() < card.getCost()) {
			return false;
		}
		return true;
	}

	private boolean isTargetsValid(Card card, Optional<List<Targetable>> targets) {
		for (Effect effect : card.getEffects()) {
			boolean targetRequired = effect.getIsTargetPresent();
			boolean isListEmpty = isListEmpty(targets);

			if ((targetRequired && !isListEmpty) || (!targetRequired && isListEmpty)) {
				return false;
			}
		}

		return true;
	}

	private boolean isListEmpty(Optional<List<Targetable>> list) {
		return list.isPresent() && !list.get().isEmpty();
	}

	private List<Minion> getEnemiesTauntOnTheBoard() {
		List<Minion> taunts = new ArrayList<>();

		Player enemyPlayer = getEnemyPlayer();
		List<Minion> enemiesMinions = board.getPlayerBoard(enemyPlayer);

		for (Minion minion : enemiesMinions) {
			if (minion.hasEffect("taunt")) {
				taunts.add(minion);
			}
		}

		return taunts;
	}

	private boolean ifEnemyHasTauntOnTheBoard() {
		return !getEnemiesTauntOnTheBoard().isEmpty();
	}

	private boolean isAttactCorrect(Minion minion, Targetable target) {
		if (ifEnemyHasTauntOnTheBoard()) {
			List<Minion> enemiesTaunts = getEnemiesTauntOnTheBoard();

			if (target instanceof Minion && enemiesTaunts.contains((Minion) target)) {
				return true;
			} else {
				print(TAUNT_ON_THE_BOARD);
				return false;
			}
		}

		return true;
	}

	public List<Minion> getPlayerBoard(Player player) {
		return board.getPlayerBoard(player);
	}
}
