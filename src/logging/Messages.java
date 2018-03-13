
package logging;

/**
 * @author Radek
 *
 */
public class Messages {

	public static final String LINE_WITH_TEXT = "----------------------{0}----------------------\n";
	public static final String LINE = "-----------------------------------------------\n"
			+ "-----------------------------------------------";

	public static final String PLAYER_INSTRUCTION = "Play card: 'play <number of card in hand>' \n"
			+ "Attack: 'attack <number of attacking minion> <number of target (-1 for hero)>' \n"
			+ "End turn: 'end' \n";

	public static final String CHOOSE_TARGET = "Choose number oftarget (-1 for hero): ";
	public static final String WRONG_COMMAND = "Wrong command. Please repeat.";
	public static final String CURRENT_PLAYER = "Current player: {0}";
	public static final String PLAYER_STATISTICS = "Mana:{0}, HP:{1}/{2} \n";
	public static final String MINION_STATISTICS = "{0}, M:{1}, HP:{2}/{3}, Atk:{4}";

	// Errors
	public static final String ERROR_MARKER = "[ERROR] {0}";
	public static final String LOG_FILE_CREATE_EXCEPTION = "Exception while creating log file.";
	public static final String TAUNT_ON_THE_BOARD = "You must accact a minion with Taunt";

	// Effects
	public static final String TAUNT_EFFECT = "taunt";
	public static final String MINION_DESTROY_EFFECT = "minion destroy";
	public static final String HEAL_EFFECT = "heal";
	public static final String DAMAGE_EFFECT = "damage";
}
