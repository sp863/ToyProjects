package chess;

import utils.OutputView;
import utils.Validator;

import static utils.Constant.*;

import java.util.Scanner;

public class ChessGameController {
	private Scanner scanner;
	private ChessGame chessGame;
	
	public ChessGameController(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public void runGame() {
		OutputView.showMainMenu();
		int choose = scanner.nextInt();
		while (true) {
			if (choose == BUTTON_ONE) {
				playGame();
			} else if (choose == BUTTON_TWO) {
				
			} else if (choose == BUTTON_ZERO) {
				OutputView.showExitMessage();
				return;
			}
		}
	}
	
	public void playGame() {
		chessGame = new ChessGame(scanner);
		OutputView.showGameMenu();
		while (true) {
			chessGame.playerWhiteMove();
			if (chessGame.checkMatePlayerBlack()) {
				OutputView.showVictoryMessage(PLAYER_WHITE_VICTORY);
				return;
			}
			chessGame.playerBlackMove();
			if (chessGame.checkMatePlayerWhite()) {
				OutputView.showVictoryMessage(PLAYER_BLACK_VICTORY);
				return;
			}
		}
	}
}
