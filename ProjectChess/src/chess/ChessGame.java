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
	public static String[][] chessBoard;
	private PlayerWhite playerWhite;
	private PlayerBlack playerBlack;
	private Scanner scanner;
	

	public ChessGame(Scanner scanner) {
		chessBoard = new String[BOARD_LENGTH][BOARD_LENGTH];
		playerWhite = new PlayerWhite();
		playerBlack = new PlayerBlack();
		this.scanner = scanner;
	}
	
	
	public boolean checkMatePlayerBlack() {
		return false;
	}
	
	public boolean checkMatePlayerWhite() {
		return false;
	}
	
	
	public void playerWhiteMove() {
		//check illegal argument exception
		OutputView.showChessBoardPlayer1();
		while (true) {
			String chooseUnit = scanner.next();
			OutputView.showChooseUnitMessage();
			if (Validator.isValidUnit(chooseUnit, playerWhite)) {
				OutputView.showChooseLocationMessage();
				String chooseNextMove = scanner.next();
				if (Validator.isValidLocation(chooseNextMove)) {
					Unit unit = getAliveUnit(chooseUnit, playerWhite);
					
					int y = BOARD_LENGTH-(Integer.parseInt(String.valueOf(chooseNextMove.charAt(1))));
					int x = chooseNextMove.charAt(0)-'a';
					if (unit.checkMove(y, x)) {
						
					}
				}
			}
		}
	}
	
	public void playerBlackMove() {
		//check illegal argument exception
		OutputView.showChessBoardPlayer2();
		String chooseUnit = scanner.next();
		String chooseNextMove = scanner.next();
		
	}
	
	public Unit getAliveUnit(String unitCode, Player player) {
		HashMap<String, Unit> aliveList = player.getAliveUnitList();
		return aliveList.get(unitCode);
	}
	
	public PlayerWhite getPlayerWhite() {
		return playerWhite;
	}
	
	public PlayerBlack getPlayerBlack() {
		return playerBlack;
	}
}
