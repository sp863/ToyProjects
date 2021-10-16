package utils;

import static utils.Constant.*;

import java.util.HashMap;
import java.util.Set;

import chess.Unit;
import player.Player;

public class Validator {
	
	public static boolean isValidUnit(String unit, Player player) {
		checkUnitLength(unit);
		checkUnitForm(unit);
		checkUnitUpperCase(unit);
		checkUnitRange(unit);
		checkUnitAlive(unit, player);
		return true;
	}
	
	public static void checkUnitLength(String unit) {
		if (unit.length() != UNIT_CODE_LENGTH) {
			throw new IllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION_INPUT_LENGTH);
		}
	}
	
	public static void checkUnitForm(String unit) {
		//check for errors in this method*** checking if the first character is a character
		try {
			Character.isDigit(unit.charAt(0));
			Integer.parseInt(String.valueOf(unit.charAt(1)));
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION_INPUT_FORM);
		}
	}
	
	public static void checkUnitUpperCase(String unit) {
		char inputLetter = unit.charAt(0);
		if (Character.isLowerCase(inputLetter) == true) {
			throw new IllegalArgumentException();
		}
	}

	public static void checkUnitRange(String unit) {
		String[] possibleUnitCodes = {"K0","Q0","P1","P2","P3","P4","P5","P6","P7","P8","N1","N2","B1","B2","R1","R2"};
		boolean isPossible = false;
		for (int i = 0; i < possibleUnitCodes.length; i++) {
			if (unit.equals(possibleUnitCodes[i])) {
				isPossible = true;
			}
		}
		if (isPossible == false) {
			throw new IllegalArgumentException();
		}
	}
	
	public static void checkUnitAlive(String unit, Player player) {
		boolean isAlive = false;
		HashMap<String,Unit> aliveList = player.getAliveUnitList();
		Set<String> aliveListKeys = aliveList.keySet();
		
		for (String aliveUnit : aliveListKeys) {
			if (aliveUnit.equals(unit)) {
				isAlive = true;
			}
		}
		if (isAlive == false) {
			throw new IllegalArgumentException();
		}
	}

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
}
