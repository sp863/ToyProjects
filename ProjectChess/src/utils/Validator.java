package utils;

import static utils.Constant.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Pattern;

import chess.ChessGame;
import chess.Unit;
import player.Player;
import player.PlayerMoveManager;

public class Validator {
	
	public static boolean isValidUnit(String unitCode, Player player) {
		try {
			checkUnitLength(unitCode);
			checkUnitForm(unitCode);
			checkUnitUpperCase(unitCode);
			checkUnitRange(unitCode);
			checkUnitAlive(unitCode, player);
			return true;
		} catch (ValidatorException e) {
			return false;
		}
	}
	
	public static void checkUnitLength(String unitCode) throws ValidatorException {
		if (unitCode.length() != UNIT_CODE_LENGTH) {
			throw new ValidatorException(ILLEGAL_ARGUMENT_EXCEPTION_UNITCODE_LENGTH);
		}
	}
	
	public static void checkUnitForm(String unitCode) throws ValidatorException {
		//check for errors in this method*** checking if the first character is a character
		try {
//		    String pattern = "^[A-Z][0-9]"; //숫자만
//            boolean regex = Pattern.matches(pattern, unitCode);
//			Character.isDigit(unitCode.charAt(0));
			Integer.parseInt(String.valueOf(unitCode.charAt(1)));
		} catch (IllegalArgumentException e) {
			throw new ValidatorException(ILLEGAL_ARGUMENT_EXCEPTION_UNITCODE_FORM);
		}
	}
	
	public static void checkUnitUpperCase(String unitCode) throws ValidatorException {
		char inputLetter = unitCode.charAt(0);
		if (Character.isLowerCase(inputLetter) == true) {
			throw new ValidatorException(ILLEGAL_ARGUMENT_EXCEPTION_UNITCODE_UPPERCASE);
		}
	}

	public static void checkUnitRange(String unitCode) throws ValidatorException {
		String[] possibleUnitCodes = {"K0","Q0","P1","P2","P3","P4","P5","P6","P7","P8","N1","N2","B1","B2","R1","R2"};
		boolean isPossible = false;
		for (int i = 0; i < possibleUnitCodes.length; i++) {
			if (unitCode.equals(possibleUnitCodes[i])) {
				isPossible = true;
			}
		}
		if (isPossible == false) {
			throw new ValidatorException(ILLEGAL_ARGUMENT_EXCEPTION_UNITCODE_INVALID_UNITCODE);
		}
	}
	
	public static void checkUnitAlive(String unitCode, Player player) throws ValidatorException {
		if (player.getAliveUnit(unitCode) == null) {
			throw new ValidatorException(ILLEGAL_ARGUMENT_EXCEPTION_UNITCODE_INVALID_UNITCODE);
		}
	}
	
	//valid promotion unit check -----------------------------------------------------------------------------------------------
	public static boolean isValidPromotionUnit(String unitCode, Player player) {
		try {
			checkPossiblePromotionUnit(unitCode);
			return true;
		} catch (ValidatorException e) {
			return false;
		}
	}
	
	public static void checkPossiblePromotionUnit(String unitCode) throws ValidatorException {
		String[] possibleUnitCodes = {"Q", "B", "N", "R"};
		boolean isPossible = false;
		for (int i = 0; i < possibleUnitCodes.length; i++) {
			if (unitCode.equals(possibleUnitCodes[i])) {
				isPossible = true;
			}
		}
		if (isPossible == false) {
			throw new ValidatorException(ILLEGAL_ARGUMENT_EXCEPTION_UNITCODE_INVALID_UNITCODE);
		}
	}
	
	//valid location check -----------------------------------------------------------------------------------------------
	public static boolean isValidLocation(String location) {
		try {
			checkLocationLength(location);
			checkLocationForm(location);
			checkLocationLowerCase(location);
			checkLocationRange(location);
			return true;
		} catch (ValidatorException e) {
			return false;
		}
	}
	
	public static void checkLocationLength(String location) throws ValidatorException {
		if (location.length() != LOCATION_CODE_LENGTH) {
			throw new ValidatorException(ILLEGAL_ARGUMENT_EXCEPTION_LOCATION_LENGTH);
		}
	}
	
	public static void checkLocationForm(String location) throws ValidatorException {
		//check for errors in this method*** checking if the first character is a character
		try {
			Character.isDigit(location.charAt(0));
			Integer.parseInt(String.valueOf(location.charAt(1)));
		} catch (IllegalArgumentException e) {
			throw new ValidatorException(ILLEGAL_ARGUMENT_EXCEPTION_LOCATION_FORM);
		}
	}
	
	public static void checkLocationLowerCase(String location) throws ValidatorException {
		char inputLetter = location.charAt(0);
		if (Character.isUpperCase(inputLetter) == true) {
			throw new ValidatorException(ILLEGAL_ARGUMENT_EXCEPTION_LOCATION_LOWERCASE);
		}
	}
	
	public static void checkLocationRange(String location) throws ValidatorException {
		char inputLetter = location.charAt(0);
		char inputNumber = location.charAt(1);
		int checkLetterRange = inputLetter-'a';
		int checkNumberRange = Integer.parseInt(String.valueOf(inputNumber));
				
		if (checkLetterRange > 7) {
			throw new ValidatorException(ILLEGAL_ARGUMENT_EXCEPTION_LOCATION_LETTER_RANGE);
		}
		if (checkNumberRange > 8 || checkNumberRange < 1) {
			throw new ValidatorException(ILLEGAL_ARGUMENT_EXCEPTION_LOCATION_NUMBER_RANGE);
		}
	}
	
	//valid move check -----------------------------------------------------------------------------------------------
	public static boolean isValidMove(int y, int x, Unit unit, Player player) {
		try {
			checkMoveRange(y, x, unit);
			checkMoveSameTeam(y, x, unit);
			checkUnitMove(y, x, unit, player);
			return true;
		} catch (ValidatorException e) {
			return false;
		}
	}
	
	public static void checkMoveRange(int y, int x, Unit unit) {
		int currentY = unit.getUnitLocationPoint().getY();
		int currentX = unit.getUnitLocationPoint().getX();
		if (y < 0 || y >= BOARD_LENGTH || x < 0 || x >= BOARD_LENGTH) {
			throw new ValidatorException(ILLEGAL_ARGUMENT_EXCPETION_MOVE_RANGE1);
		}
		if (currentY == y && currentX == x) {
			throw new ValidatorException(ILLEGAL_ARGUMENT_EXCPETION_MOVE_RANGE2);
		}
	}
	
	public static void checkMoveSameTeam(int y, int x, Unit unit) {
		Unit checkUnit = ChessGame.chessBoard[y][x];
		if (checkUnit != null) {
			if (checkUnit.getUnitColor().equals(unit.getUnitColor())) {
				throw new ValidatorException(ILLEGAL_ARGUMENT_EXCEPTION_MOVE_SAME_TEAM);
			}
		}
	}
	
	public static void checkUnitMove(int y, int x, Unit unit, Player player) {
		boolean checkMove = unit.checkUnitSpecificMove(y, x, player);
		if (checkMove == false) {
			throw new ValidatorException(ILLEGAL_ARGUMENT_EXCEPTION_MOVE_UNIT_SPECIFIC);
		}
	}
	//castling input check -----------------------------------------------------------------------------------------------
	public static boolean isValidAnswer (String answer) {
		try {
			checkYesOrNo (answer);
			return true;
		} catch (ValidatorException e) {
			return false;
		}
	}
	
	public static void checkYesOrNo (String answer) throws ValidatorException {
		if (!answer.equals(ANSWER_YES) && !answer.equals(ANSWER_NO)) {
			throw new ValidatorException(ILLEGAL_ARGUMENT_EXCEPTION_CASTLING_ANSWER);
		}
	}
	
	public static boolean isValidCastlingSide (String side) {
		try {
			checkKingOrQueen(side);
			return true;
		} catch (ValidatorException e) {
			return false;
		}
	}
	
	public static void checkKingOrQueen (String side) throws ValidatorException {
		if (!side.equals(KING_SIDE) && !side.equals(QUEEN_SIDE)) {
			throw new ValidatorException(ILLEGAL_ARGUMENT_EXCEPTION_CASTLING_SIDE);
		}
	}
	
	//castling move check -----------------------------------------------------------------------------------------------
	public static boolean isCastlingPossible(Player player, String rookUnitCode) {
		ArrayList<Integer> castlingCheckRangeY = new ArrayList<>();
		ArrayList<Integer> castlingCheckRangeX = new ArrayList<>();
		if (checkCastlingUnitAlive(rookUnitCode, player) && checkIsMoved(player, rookUnitCode) &&
			checkKingOnCheck(player) && confirmTilesToCheck(player, rookUnitCode, castlingCheckRangeY, castlingCheckRangeX) &&
			checkRookKingObstacle(player, castlingCheckRangeY, castlingCheckRangeX) && 
			isCastlingTileOnCheck(player, castlingCheckRangeY, castlingCheckRangeX)) {
				return true;
		}
		return false;
	}
	
	public static boolean checkCastlingUnitAlive(String unitCode, Player player) {
		if (player.getAliveUnit(unitCode) == null) {
			return false;
		}
		return true;
	}
	
	public static boolean checkIsMoved(Player player, String rookUnitCode) {
		Unit king = player.getAliveUnit(KING_NAME);
		Unit rook = player.getAliveUnit(rookUnitCode);
		if (king.isMoved() || rook.isMoved()) {
			return false;
		}
		return true;
	}
	
	public static boolean checkKingOnCheck(Player player) {
		if (player.isKingOnCheck()== true) {
			return false;
		}
		return true;
	}
	
	public static boolean confirmTilesToCheck(Player player, String rookUnitCode, ArrayList<Integer> rangeY, ArrayList<Integer> rangeX) {
		if (player.getPlayerColor().equals(PLAYER_COLOR_WHITE)) {
			if (rookUnitCode.equals(ROOK_1_NAME)) {
				rangeY.add(7);
				rangeY.add(7);
				rangeY.add(7);
				rangeX.add(3);
				rangeX.add(2);
				rangeX.add(1);
			} else if (rookUnitCode.equals(ROOK_2_NAME)) {
				rangeY.add(7);
				rangeY.add(7);
				rangeX.add(5);
				rangeX.add(6);
			}
		} else if (player.getPlayerColor().equals(PLAYER_COLOR_BLACK)) {
			if (rookUnitCode.equals(ROOK_1_NAME)) {
				rangeY.add(0);
				rangeY.add(0);
				rangeX.add(5);
				rangeX.add(6);
			} else if (rookUnitCode.equals(ROOK_2_NAME)) {
				rangeY.add(0);
				rangeY.add(0);
				rangeY.add(0);
				rangeX.add(3);
				rangeX.add(2);
				rangeX.add(1);
			}
		}
		return true;
	}
	
	public static boolean checkRookKingObstacle(Player player, ArrayList<Integer> rangeY, ArrayList<Integer> rangeX) {
		for (int i = 0; i < rangeY.size(); i++) {
			if (ChessGame.chessBoard[rangeY.get(i)][rangeX.get(i)] != null) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isCastlingTileOnCheck(Player player, ArrayList<Integer> rangeY, ArrayList<Integer> rangeX) {
		int checkCount = 0;
		for (int i = 0; i < 2; i++) {
			if (!PlayerMoveManager.isTileOnCheck(player, rangeY.get(i), rangeX.get(i))) {
				checkCount++;
			}
		}
		if (checkCount == 2) {
			return true;
		}
		return false;
	}
}
