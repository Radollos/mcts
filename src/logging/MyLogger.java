package logging;

import static logging.Messages.ERROR_MARKER;
import static logging.Messages.LOG_FILE_CREATE_EXCEPTION;
import static logging.Messages.PLAYER_INSTRUCTION;

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
			printError(LOG_FILE_CREATE_EXCEPTION, e);
			e.printStackTrace();
		}
	}

	protected MyLogger(String name, String resourceBundleName) {
		super(name, resourceBundleName);
	}

	public static void print(String message) {
		printAndSaveToLogFile(message);
	}

	public static void printMenu() {
		printInternal(PLAYER_INSTRUCTION);
	}

	public static void printBoard(IBoard board, Player currentPlayer) {
		printAndSaveToLogFile(board.toString());
	}

	public static void printError(String message, Throwable throwable) {
		printInternal(System.out.format(ERROR_MARKER, message).toString());
	}

	public static String getInputFromUser(String inputDescription) {
		printAndSaveToLogFile(inputDescription);
		String userInput = scanner.nextLine();
		printAndSaveToLogFile(userInput);
		return userInput;
	}

	private static void printAndSaveToLogFile(String message) {
		printInternal(message);
		fileWriter.println(message);
	}

	private static void printInternal(String message) {
		System.out.println(message);
	}
}
