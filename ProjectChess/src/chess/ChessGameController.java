package chess;

import utils.OutputView;
import utils.Validator;

import static utils.Constant.*;

import java.util.Scanner;

import player.Player;

public class ChessGameController {
	private Scanner scanner;
	private ChessGame chessGame;
	public static int turn = TURN_INITIAL_COUNT;
	
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
			Player playerWhite = chessGame.getPlayerWhite();
			Player playerBlack = chessGame.getPlayerBlack();
			
			OutputView.showTurnCount();

			OutputView.showWhiteTurnHeader();
			
			OutputView.showTakenList(playerWhite);
			OutputView.showTakenList(playerBlack);
			
			OutputView.showGameSeparator();
			OutputView.showChessBoardPlayer1();
			OutputView.showGameSeparator();
			playerMove(playerWhite);
			if (chessGame.checkMatePlayerBlack()) {
				OutputView.showVictoryMessage(PLAYER_WHITE_VICTORY);
				return;
			}
			OutputView.showBlackTurnHeader();

			OutputView.showTakenList(playerWhite);
			OutputView.showTakenList(playerBlack);
			
			OutputView.showGameSeparator();
			OutputView.showChessBoardPlayer2();
			OutputView.showGameSeparator();
			playerMove(playerBlack);
			if (chessGame.checkMatePlayerWhite()) {
				OutputView.showVictoryMessage(PLAYER_BLACK_VICTORY);
				return;
			}
			turn++;
		}
	}
	
	public void playerMove(Player player) {
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
						
						Unit tempUnit = promotePawn(player, unit, y, x);
						if (tempUnit != null) {
							unit = tempUnit;
							ChessGame.chessBoard[y][x] = null;
							ChessGame.chessBoard[y][x] = unit;
						}
						checkKing(unit, y, x);
						return;
					}
				}
			}
		}
	}
	
	public Unit promotePawn(Player player, Unit unit, int y, int x) {
		if (unit.getUnitCode().contains("P") && y == BOARD_LENGTH-1 || unit.getUnitCode().contains("P") && y == 0) {
			Player opponentPlayer = player.getOpponentPlayer();
			if (unit.myOpponent.getTakenUnitList().size() > 0) {
				OutputView.showChoosePawnPromotionMessage();
				String promotionUnitCode = scanner.next();
				if (Validator.isValidTakenUnit(promotionUnitCode, player)) {
					player.getAliveUnitList().remove(unit.getUnitCode());

					Unit unitToReplace = opponentPlayer.getTakenUnit(promotionUnitCode);
					player.getAliveUnitList().put(promotionUnitCode, unitToReplace);
					opponentPlayer.getTakenUnitList().remove(promotionUnitCode);
					
					unitToReplace.getUnitLocationPoint().setY(y);
					unitToReplace.getUnitLocationPoint().setX(x);
					return unitToReplace;
				}
			}
		}
		return null;
	}
	
	public void checkKing(Unit unit, int y, int x) {
		if (unit.unitCheckKing(y, x) ) {
			OutputView.showCheckKingMessage();
		}
		return;
	}
}
