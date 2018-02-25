package logging;

import java.util.Scanner;
import java.util.logging.Logger;

import cardgame.board.IBoard;
import cardgame.player.Player;

/**
 * @author Radek
 *
 */
public class MyLogger extends Logger {

	private static Scanner scanner = new Scanner(System.in);

	protected MyLogger(String name, String resourceBundleName) {
		super(name, resourceBundleName);
	}

	public static void printBoard(IBoard board) {
		print(board.toString());
	}

	public static void printPlayer(Player player) {
		print(player.toString());
	}

	public static String getInputFromUser(String inputDescription) {
		print(inputDescription);
		return scanner.nextLine();
	}

	private static void print(String toPrint) {
		System.out.println(toPrint);
	}
}
