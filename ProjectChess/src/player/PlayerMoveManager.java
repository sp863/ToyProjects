package player;

import static utils.Constant.*;
import java.util.Scanner;
import java.util.Set;

import chess.Bishop;
import chess.ChessGame;
import chess.Knight;
import chess.Queen;
import chess.Rook;
import chess.Unit;
import chess.UnitLocationPoint;
import utils.OutputView;
import utils.Validator;

public class PlayerMoveManager {
	private Scanner scanner;
	
	public PlayerMoveManager(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public void playerMove(Player player) {
		while (true) {
			OutputView.showChooseUnitMessage();
			String chooseUnit = scanner.next();
			if (Validator.isValidUnit(chooseUnit, player)) {
				if (checkCastling(chooseUnit, player)) {
					return;
				}
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
						
						Unit tempUnit = promotedPawn(player, unit, y, x);
						if (tempUnit != null) {
							unit = tempUnit;
							ChessGame.chessBoard[y][x] = null;
							ChessGame.chessBoard[y][x] = unit;
						}
						player.isOpponentKingOnCheck();
						if (player.getOpponentPlayer().isKingOnCheck) {
							OutputView.showCheckKingMessage();
						}
						return;
					}
				}
			}
		}
	}
	
	//pawn promotion -----------------------------------------------------------------------------------------------
	public Unit promotedPawn(Player player, Unit unit, int y, int x) {
		if (unit.getUnitCode().contains("P") && y == BOARD_LENGTH-1 || unit.getUnitCode().contains("P") && y == 0) {
			while (true) {
				OutputView.showChoosePawnPromotionMessage();
				String unitType = scanner.next();
				if (Validator.isValidPromotionUnit(unitType, player)) {
					Unit promotedUnit = initializedPromotionUnit(unitType, player, y, x);
					String promotedUnitCode = promotedUnit.getUnitCode();
					player.getAliveUnitList().remove(unit.getUnitCode());
					player.getAliveUnitList().put(promotedUnitCode, promotedUnit);
					return promotedUnit;
				}
			}
		}
		return null;
	}
	
	public Unit initializedPromotionUnit(String unitType, Player player, int y, int x) {
		Unit promotedUnit = null;
		Player opponentPlayer = player.getOpponentPlayer();
		String unitCode = promotionUnitCode(unitType, player);
		String unitColor = player.getPlayerColor();
		UnitLocationPoint unitLocation = new UnitLocationPoint(y,x);
		
		if (unitType.equals("Q")) {
			promotedUnit = new Queen(opponentPlayer, unitCode, unitColor, unitLocation);
		} else if (unitType.equals("B")) {
			promotedUnit = new Bishop(opponentPlayer, unitCode, unitColor, unitLocation);
		} else if (unitType.equals("N")) {
			promotedUnit = new Knight(opponentPlayer, unitCode, unitColor, unitLocation);
		} else if (unitType.equals("R")) {
			promotedUnit = new Rook(opponentPlayer, unitCode, unitColor, unitLocation);
			promotedUnit.setIsMoved(true);
		}
		return promotedUnit;
	}
	
	public String promotionUnitCode(String promotionUnitType, Player player) {
		String unitCode = "";
		int maxNum = 0;
		Set<String> keySet = player.getAliveUnitList().keySet();
		for (String key : keySet) {
			if (key.charAt(0) == promotionUnitType.charAt(0)) {
				int tempNum = Integer.parseInt(String.valueOf(key.charAt(1)));
				if (tempNum > maxNum) {
					maxNum = tempNum;
				}
			}
		}
		unitCode += promotionUnitType;
		unitCode += String.valueOf(maxNum+1);
		return unitCode;
	}
	
	//castling -----------------------------------------------------------------------------------------------
	public boolean checkCastling(String chooseUnit, Player player) {
		if (chooseUnit.contains("K") || chooseUnit.contains("R")) {
			if (checkKingSideCastling(player) || checkQueenSideCastling(player)) {
				while (true) {
					OutputView.showChooseCastlingMessage();
					String doCastling = scanner.next();
					if (Validator.isValidAnswer(doCastling) && doCastling.equals(ANSWER_YES)) {
						if (checkKingSideCastling(player) && checkQueenSideCastling(player)) {
							OutputView.showChooseCastlingSideMessage();
							String castlingSide = scanner.next();
							if (castlingSide.equals(KING_SIDE)) {
								executeKingSideCastling(player);
							} else if (castlingSide.equals(QUEEN_SIDE)) {
								executeQueenSideCastling(player);
							}
						} else if (checkKingSideCastling(player)) {
							executeKingSideCastling(player);
						} else if (checkQueenSideCastling(player)) {
							executeQueenSideCastling(player);
						}
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean checkKingSideCastling(Player player) {
		String playerColor = player.getPlayerColor();
		String rookSide = "";
		if (playerColor.equals(PLAYER_COLOR_WHITE)) {
			rookSide = ROOK_2_NAME;
		} else if (playerColor.equals(PLAYER_COLOR_BLACK)) {
			rookSide = ROOK_1_NAME;
		}
		return Validator.isCastlingPossible(player, rookSide);
	}
	
	public boolean checkQueenSideCastling(Player player) {
		String playerColor = player.getPlayerColor();
		String rookSide = "";
		if (playerColor.equals(PLAYER_COLOR_WHITE)) {
			rookSide = ROOK_1_NAME;
		} else if (playerColor.equals(PLAYER_COLOR_BLACK)) {
			rookSide = ROOK_2_NAME;
		}
		return Validator.isCastlingPossible(player, rookSide);
	}
	
	public void executeKingSideCastling(Player player) {
		String playerRookUnitCode = "";
		if (player.getPlayerColor().equals(PLAYER_COLOR_WHITE)) {
			playerRookUnitCode = ROOK_2_NAME;
		} else if (player.getPlayerColor().equals(PLAYER_COLOR_BLACK)) {
			playerRookUnitCode = ROOK_1_NAME;
		}
		Unit playerRook = player.getAliveUnit(playerRookUnitCode);
		Unit playerKing = player.getAliveUnit(KING_NAME);
		int fixedY = playerKing.getUnitLocationPoint().getY();
		int kingX = playerKing.getUnitLocationPoint().getX();
		int rookX = playerRook.getUnitLocationPoint().getX();
		
		int newKingX = kingX+2;
		int newRookX = kingX+1;
		adjustBoardAfterCastling (playerRook, playerKing, fixedY, kingX, rookX, newKingX, newRookX);
	}
	
	public void executeQueenSideCastling(Player player) {
		String playerRookUnitCode = "";
		if (player.getPlayerColor().equals(PLAYER_COLOR_WHITE)) {
			playerRookUnitCode = ROOK_1_NAME;
		} else if (player.getPlayerColor().equals(PLAYER_COLOR_BLACK)) {
			playerRookUnitCode = ROOK_2_NAME;
		}
		Unit playerRook = player.getAliveUnit(playerRookUnitCode);
		Unit playerKing = player.getAliveUnit(KING_NAME);
		int fixedY = playerKing.getUnitLocationPoint().getY();
		int kingX = playerKing.getUnitLocationPoint().getX();
		int rookX = playerRook.getUnitLocationPoint().getX();

		int newKingX = kingX-2;
		int newRookX = kingX-1;
		adjustBoardAfterCastling (playerRook, playerKing, fixedY, kingX, rookX, newKingX, newRookX);
	}
	
	public void adjustBoardAfterCastling(Unit playerRook, Unit playerKing, int fixedY, int kingX, int rookX, int newKingX, int newRookX) {
		ChessGame.chessBoard[fixedY][kingX] = null;
		ChessGame.chessBoard[fixedY][rookX] = null;
		
		ChessGame.chessBoard[fixedY][newKingX] = playerKing;
		ChessGame.chessBoard[fixedY][newRookX] = playerRook;
		
		playerKing.getUnitLocationPoint().setX(newKingX);
		playerRook.getUnitLocationPoint().setX(newRookX);
	}

}
