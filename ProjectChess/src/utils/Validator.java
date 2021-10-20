package utils;

import static utils.Constant.*;

import java.util.HashMap;
import java.util.Set;

import chess.ChessGame;
import chess.Unit;
import player.Player;

public class Validator {
	
	public static boolean isValidUnit(String unitCode, Player player) {
		checkUnitLength(unitCode);
		checkUnitForm(unitCode);
		checkUnitUpperCase(unitCode);
		checkUnitRange(unitCode);
		checkUnitAlive(unitCode, player);
		return true;
	}
	
	public static boolean isValidTakenUnit(String unitCode, Player player) {
		checkUnitLength(unitCode);
		checkUnitForm(unitCode);
		checkUnitUpperCase(unitCode);
		checkTakenUnitRange(unitCode);
		checkUnitTaken(unitCode, player);
		return true;
	}
	
	public static void checkUnitLength(String unitCode) {
		if (unitCode.length() != UNIT_CODE_LENGTH) {
			throw new IllegalArgumentException();
		}
	}
	
	public static void checkUnitForm(String unitCode) {
		//check for errors in this method*** checking if the first character is a character
		try {
			Character.isDigit(unitCode.charAt(0));
			Integer.parseInt(String.valueOf(unitCode.charAt(1)));
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException();
		}
	}
	
	public static void checkUnitUpperCase(String unitCode) {
		char inputLetter = unitCode.charAt(0);
		if (Character.isLowerCase(inputLetter) == true) {
			throw new IllegalArgumentException();
		}
	}

	public static void checkUnitRange(String unitCode) {
		String[] possibleUnitCodes = {"K0","Q0","P1","P2","P3","P4","P5","P6","P7","P8","N1","N2","B1","B2","R1","R2"};
		boolean isPossible = false;
		for (int i = 0; i < possibleUnitCodes.length; i++) {
			if (unitCode.equals(possibleUnitCodes[i])) {
				isPossible = true;
			}
		}
		if (isPossible == false) {
			throw new IllegalArgumentException();
		}
	}
	public static void checkTakenUnitRange(String unitCode) {
		String[] possibleUnitCodes = {"Q0","N1","N2","B1","B2","R1","R2"};
		boolean isPossible = false;
		for (int i = 0; i < possibleUnitCodes.length; i++) {
			if (unitCode.equals(possibleUnitCodes[i])) {
				isPossible = true;
			}
		}
		if (isPossible == false) {
			throw new IllegalArgumentException();
		}
	}
	
	public static void checkUnitAlive(String unitCode, Player player) {
		if (player.getAliveUnit(unitCode) == null) {
			throw new IllegalArgumentException();
		}
	}
	
	public static void checkUnitTaken(String unitCode, Player player) {
		if (player.getOpponentPlayer().getTakenUnit(unitCode) == null) {
			throw new IllegalArgumentException();
		}
	}
	
	//valid location check -----------------------------------------------------------------------------------------------
	public static boolean isValidLocation(String location) {
		checkLocationLength(location);
		checkLocationForm(location);
		checkLocationLowerCase(location);
		checkLocationRange(location);
		return true;
	}
	
	public static void checkLocationLength(String location) {
		if (location.length() != LOCATION_CODE_LENGTH) {
			throw new IllegalArgumentException();
		}
	}
	
	public static void checkLocationForm(String location) {
		//check for errors in this method*** checking if the first character is a character
		try {
			Character.isDigit(location.charAt(0));
			Integer.parseInt(String.valueOf(location.charAt(1)));
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException();
		}
	}
	
	public static void checkLocationLowerCase(String location) {
		char inputLetter = location.charAt(0);
		if (Character.isUpperCase(inputLetter) == true) {
			throw new IllegalArgumentException();
		}
	}
	
	public static void checkLocationRange(String location) {
		char inputLetter = location.charAt(0);
		char inputNumber = location.charAt(1);
		int checkLetterRange = inputLetter-'a';
		int checkNumberRange = Integer.parseInt(String.valueOf(inputNumber));
				
		if (checkLetterRange > 7) {
			throw new IllegalArgumentException();
		}
		if (checkNumberRange > 8 || checkNumberRange < 1) {
			throw new IllegalArgumentException();
		}
	}
	
	//valid move check -----------------------------------------------------------------------------------------------
	public static boolean isValidMove(int y, int x, Unit unit, Player player) {
		checkMoveRange(y, x, unit);
		checkMoveSameTeam(y, x, unit);
		checkUnitMove(y, x, unit, player);
		return true;
	}
	
	public static void checkMoveRange(int y, int x, Unit unit) {
		int currentY = unit.getUnitLocationPoint().getY();
		int currentX = unit.getUnitLocationPoint().getX();
		if (y < 0 || y >= BOARD_LENGTH || x < 0 || x >= BOARD_LENGTH) {
			throw new IllegalArgumentException();
		}
		if (currentY == y && currentX == x) {
			throw new IllegalArgumentException();
		}
	}
	
	public static void checkMoveSameTeam(int y, int x, Unit unit) {
		Unit checkUnit = ChessGame.chessBoard[y][x];
		if (checkUnit != null) {
			if (checkUnit.getUnitColor().equals(unit.getUnitColor())) {
				throw new IllegalArgumentException();
			}
		}
	}
	
	public static void checkUnitMove(int y, int x, Unit unit, Player player) {
		boolean checkMove = unit.checkUnitSpecificMove(y, x, player);
		if (checkMove == false) {
			throw new IllegalArgumentException();
		}
	}
	
}
