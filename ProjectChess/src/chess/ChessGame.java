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
	
	public ChessGame() {
		chessBoard = new Unit[BOARD_LENGTH][BOARD_LENGTH];
		playerWhite = new PlayerWhite();
		playerBlack = new PlayerBlack();
		playerWhite.playerInit(playerBlack);
		playerBlack.playerInit(playerWhite);
		chessBoardInit(playerWhite);
		chessBoardInit(playerBlack);
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
	
	public boolean isCheckMate(Player player) {
		if (player.isKingOnCheck()) {
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
