package chess;

import java.util.Scanner;

public class Application {
	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		ChessGameController chessGameController = new ChessGameController(scanner);
		chessGameController.runGame();
	}
}
