package chess;

import utils.OutputView;
import utils.Validator;

import static utils.Constant.*;

import java.util.Scanner;

import player.Player;

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
		while (true) {
			OutputView.showGameSeparator();
			OutputView.showChessBoardPlayer1();
			OutputView.showGameSeparator();
			playerMove(chessGame.getPlayerWhite());
			if (chessGame.checkMatePlayerBlack()) {
				OutputView.showVictoryMessage(PLAYER_WHITE_VICTORY);
				return;
			}
			OutputView.showGameSeparator();
			OutputView.showChessBoardPlayer2();
			OutputView.showGameSeparator();
			playerMove(chessGame.getPlayerBlack());
			if (chessGame.checkMatePlayerWhite()) {
				OutputView.showVictoryMessage(PLAYER_BLACK_VICTORY);
				return;
			}
		}
	}
	
	public void playerMove(Player player) {
		//check illegal argument exception
		while (true) {
			OutputView.showChooseUnitMessage();
			String chooseUnit = scanner.next();
			if (Validator.isValidUnit(chooseUnit, player)) {
				Unit unit = player.getAliveUnit(chooseUnit);
				OutputView.showChooseLocationMessage();
				String chooseNextMove = scanner.next();
				if (Validator.isValidLocation(chooseNextMove)) {
					int y = BOARD_LENGTH-(Integer.parseInt(String.valueOf(chooseNextMove.charAt(1))));
					int x = chooseNextMove.charAt(0)-'a';
					if (Validator.isValidMove(y, x, unit, player)) {
						int currentY = unit.getUnitLocationPoint().getY();
						int currentX = unit.getUnitLocationPoint().getX();
						ChessGame.chessBoard[currentY][currentX] = null;
						
						ChessGame.chessBoard[y][x] = unit;
						unit.getUnitLocationPoint().setY(y);
						unit.getUnitLocationPoint().setX(x);
						return;
					}
				}
			}
		}
	}
}
