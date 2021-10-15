package utils;

import java.util.ArrayList;
import tetris.Point;
import tetris.Tetris;
import tetris.TetrisController;
import block.BlockController;
import static utils.Constant.*;

public class OutputView {
	
	public static void showGamePlayMenu(ArrayList<Point> p, BlockController blockController) {
		showSpace(p, blockController);
		showMap();
		showPlayerControl();
	}
	
	public static void showSpace(ArrayList<Point> points, BlockController blockController) {
		blockController.addBlockSpace(points);
		System.out.println("=======================================");
		for (int i = 0; i < BLOCKSPACE_VERTICAL_LENGTH; i++) {
			for (int j = 0; j < BLOCKSPACE_HORIZONTAL_LENGTH; j++) {
				System.out.printf("%d ", Tetris.map[i][j]);
			}
			System.out.println();
		}
		System.out.println("=======================================");
		blockController.removeBlockSpace(points);
	}
	public static void showMap() {
		for (int i = MAX_VERTICAL_LENGTH-10; i < MAX_VERTICAL_LENGTH; i++) {
			for (int j = 0; j < MAX_HORIZONTAL_LENGTH; j++) {
				System.out.printf("%d ", Tetris.map[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void showPlayerControl() {
		System.out.println(PLAYER_MENU_HEADER);
		System.out.printf(PLAYER_MENU_SCORE, Tetris.score);
		System.out.println(PLAYER_MENU_MOVE_LEFT);
		System.out.println(PLAYER_MENU_MOVE_RIGHT);
		System.out.println(PLAYER_MENU_ROTATE);
		System.out.println(PLAYER_MENU_DROP);
		System.out.println(PLAYER_MENU_SAVE_GAME);
		System.out.println(PLAYER_MENU_EXIT);
		System.out.println(PLAYER_MENU_SEPARATOR);
	}
	
	public static void showMainMenu() {
		System.out.println(MAIN_MENU_HEADER);
		System.out.println(MAIN_MENU_PLAY_NEW_GAME);
		System.out.println(MAIN_MENU_LOAD_GAME);
		System.out.println(MAIN_MENU_EXIT);
		System.out.println(MAIN_MENU_SEPARATOR);
	}
	
}
