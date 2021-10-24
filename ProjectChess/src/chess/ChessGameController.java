package chess;

import utils.OutputView;
import utils.Validator;

import static utils.Constant.*;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import player.Player;
import player.PlayerMoveManager;

public class ChessGameController {
	private Scanner scanner;
	private ChessGame chessGame;
	private PlayerMoveManager playerMoveManager;
	public static int turn = TURN_INITIAL_COUNT;
	
	public ChessGameController(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public void runGame() {
		while (true) {
			OutputView.showMainMenu();
			int choose = scanner.nextInt();
			if (choose == BUTTON_ONE) {
				playGame();
			} else if (choose == BUTTON_ZERO) {
				OutputView.showExitMessage();
				return;
			}
		}
	}
	
	public void playGame() {
		chessGame = new ChessGame();
		playerMoveManager = new PlayerMoveManager(scanner);
		while (true) {
			Player playerWhite = chessGame.getPlayerWhite();
			Player playerBlack = chessGame.getPlayerBlack();
			OutputView.showWhitePlayGameMenu(playerWhite, playerBlack);
			playerMoveManager.playerMove(playerWhite);
			if (chessGame.isCheckMate(playerWhite)) {
				OutputView.showVictoryMessage(PLAYER_BLACK_VICTORY);
				return;
			}
			OutputView.showBlackPlayGameMenu(playerWhite, playerBlack);
			playerMoveManager.playerMove(playerBlack);
			if (chessGame.isCheckMate(playerBlack)) {
				OutputView.showVictoryMessage(PLAYER_WHITE_VICTORY);
				return;
			}
			turn++;
		}
	}
	

	

	

	
	

	

}