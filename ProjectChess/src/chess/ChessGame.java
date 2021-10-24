package chess;

import static utils.Constant.*;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import player.Player;
import player.PlayerBlack;
import player.PlayerWhite;
import utils.OutputView;
import utils.Validator;


public class ChessGame {
	public static Unit[][] chessBoard;
	private static PlayerWhite playerWhite;
	private static PlayerBlack playerBlack;
	private Scanner scanner;
	

	public ChessGame(Scanner scanner) {
		chessBoard = new Unit[BOARD_LENGTH][BOARD_LENGTH];
		playerWhite = new PlayerWhite();
		playerBlack = new PlayerBlack();
		playerWhite.playerInit(playerBlack);
		playerBlack.playerInit(playerWhite);
		chessBoardInit(playerWhite);
		chessBoardInit(playerBlack);
		this.scanner = scanner;
	}
	
	public void chessBoardInit(Player player) {
		HashMap<String, Unit> unitAliveList = player.getAliveUnitList();
		Set<String> unitKeyList = unitAliveList.keySet();
		
		for (String unitKey : unitKeyList) {
			int y = unitAliveList.get(unitKey).unitLocationPoint.getY();
			int x = unitAliveList.get(unitKey).unitLocationPoint.getX();
			chessBoard[y][x] = unitAliveList.get(unitKey);
		}
	}
	
	public boolean isCheckMate(Player opponentPlayer) {
		Unit opponentKing = opponentPlayer.getAliveUnit(KING_NAME);
		int currentKingY = opponentKing.getUnitLocationPoint().getY();
		int currentKingX = opponentKing.getUnitLocationPoint().getX();
		
		int checkCount = 0;
		int availableTiles = 0;
		for (int i = 0; i < KING_DIRECTION_MAX; i++) {
			int y = currentKingY + KING_MOVE_RANGE_DY[i];
			int x = currentKingX + KING_MOVE_RANGE_DX[i];
			if (opponentKing.checkBoardRange(y, x)) {
				availableTiles++;
				if (opponentPlayer.isPlayerTileOnCheck(y, x)) {
					checkCount++;
				}
			}
		}
		if (checkCount == availableTiles) {
			return true;
		}
		return false;
	}
	
	public boolean isOpponentKingDead (Player opponentPlayer) {
		Unit opponentKing = opponentPlayer.getAliveUnit(KING_NAME);
		if (opponentKing == null) {
			return true;
		}
		return false;
	}

	public PlayerWhite getPlayerWhite() {
		return playerWhite;
	}
	
	public PlayerBlack getPlayerBlack() {
		return playerBlack;
	}
}
