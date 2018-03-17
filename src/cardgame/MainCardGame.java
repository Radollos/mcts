package cardgame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import cardgame.cards.Card;
import cardgame.cards.Minion;
import cardgame.cards.Spell;
import cardgame.cards.effects.DamageEffect;
import cardgame.cards.effects.Effect;
import cardgame.cards.effects.EffectArea;
import cardgame.cards.effects.HealEffect;
import cardgame.cards.effects.MinionDestroyEffect;
import cardgame.cards.effects.Taunt;
import cardgame.player.ManualPlayer;
import cardgame.player.Player;

public class MainCardGame {

	public static void main(String[] args) {

		MainCardGame main = new MainCardGame();

		List<Card> cards = main.generateCards();
		Player playerOne = new ManualPlayer("playerOne", 20, cards);
		Player playerTwo = new ManualPlayer("playerTwo", 20, cards);
		Game game = new Game(playerOne, playerTwo);
		game.startGame();
	}

	public List<Card> generateCards() {
		List<Card> cards = new ArrayList<>();

		// normal minions
		cards.add(new Minion("Damaged Golem", 2, 1, 1, null));
		cards.add(new Minion("Duskboar", 4, 1, 2, null));
		cards.add(new Minion("Carrion Grub", 2, 5, 3, null));
		cards.add(new Minion("Devilsaur", 5, 5, 5, null));
		cards.add(new Minion("Core Hound", 9, 5, 7, null));

		// speccial minions
		cards.add(new Minion("Darkscale Healer Socialite", 4, 5, 5, new HashSet<Effect>() {
			{
				add(new HealEffect(2, EffectArea.ALL_FRIENDS));
			}
		}));

		cards.add(new Minion("Hot Spring Guardian", 2, 4, 3, new HashSet<Effect>() {
			{
				add(new HealEffect(3, EffectArea.ONE_CHARACTER));
				add(new Taunt());
			}
		}));

		// spells
		cards.add(new Spell("Drain Life", 3, new HashSet<Effect>() {
			{
				add(new DamageEffect(2, EffectArea.ONE_CHARACTER));
				add(new HealEffect(2, EffectArea.MY_HERO));
			}
		}));

		cards.add(new Spell("Consecration", 4, new HashSet<Effect>() {
			{
				add(new DamageEffect(2, EffectArea.ALL_ENEMIES));
			}
		}));

		cards.add(new Spell("Assassinate", 5, new HashSet<Effect>() {
			{
				add(new MinionDestroyEffect());
			}
		}));

		return cards;
	}

}
