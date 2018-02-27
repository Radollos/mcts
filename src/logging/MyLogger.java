package logging;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.logging.Logger;

import cardgame.board.IBoard;
import cardgame.player.Player;

/**
 * @author Radek
 *
 */
public class MyLogger extends Logger {

	private static Scanner scanner;
	private static PrintWriter fileWriter;

	static {
		scanner = new Scanner(System.in);
		try {
			fileWriter = new PrintWriter(LocalDateTime.now().toString() + ".txt", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			printError(Messages.LOG_FILE_CREATE_EXCEPTION, e);
			e.printStackTrace();
		}
	}

	protected MyLogger(String name, String resourceBundleName) {
		super(name, resourceBundleName);
	}

	public static void print(String message) {
		printInternal(message);
	}

	public static void printMenu() {

	}

	public static void printBoard(IBoard board, Player currentPlayer) {
		printAndSaveToLogFile(board.toString());
	}

	public static void printError(String message, Throwable throwable) {
		printInternal(System.out.format(Messages.ERROR_MARKER, message).toString());
	}

	public static String getInputFromUser(String inputDescription) {
		printAndSaveToLogFile(inputDescription);
		return scanner.nextLine();
	}

	private static void printAndSaveToLogFile(String message) {
		printInternal(message);
		fileWriter.println(message);
	}

	private static void printInternal(String message) {
		System.out.println(message);
	}
}
