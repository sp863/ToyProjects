package utils;

import static utils.Constant.*;

public class OutputView {
	
	public static void showMainMenu() {
		System.out.println(MAIN_MENU_HEADER);
		System.out.println(MAIN_MENU_PLAYGAME);
		System.out.println(MAIN_MENU_LOADGAME);
		System.out.println(MAIN_MENU_EXIT);
		System.out.println(MAIN_MENU_SEPARATOR);
	}
	
	public static void showGameMenu() {
		System.out.println(GAME_MENU_HEADER);
		System.out.println(GAME_MENU_SEPARATOR);
	}
	
	public static void showExitMessage() {
		System.out.println(MAIN_MENU_EXIT_MESSAGE);
	}
	
	public static void showChessBoardPlayer1() {
		for (int i = 0; i < BOARD_LENGTH; i++) {
			for (int j = 0; j < BOARD_LENGTH; j++) {

			}
		}
	}
	public static void showChessBoardPlayer2() {
		for (int i = BOARD_LENGTH-1; i >= 0; i--) {
			for (int j = BOARD_LENGTH-1; j >= 0; j--) {
				
			}
		}
	}
	public static void showVictoryMessage(String message) {
		System.out.println(message);
	}
	public static void showChooseUnitMessage() {
		System.out.println(CHOOSE_UNIT_MESSAGE);
	}
	public static void showChooseLocationMessage() {
		System.out.println(CHOOSE_LOCATION_MESSAGE);
	}
}
