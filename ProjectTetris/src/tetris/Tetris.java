package tetris;

import static utils.Constant.*;

public class Tetris {
	
	public static int[][] map;
	public static int score = INITIAL_SCORE;
	
	public int getScore() {
		int score = 0;
		for (int i = MAX_VERTICAL_LENGTH-10; i < MAX_VERTICAL_LENGTH; i++) {
			int cnt = 0;
			for (int j = 0; j < MAX_HORIZONTAL_LENGTH; j++) {
				if (map[i][j] == 1) {
					cnt++;
				}
			}
			if (cnt == 10) {
				score++;
			}
		}
		return score;
	}
	
	public void getTotalScore() {
		if (getScore() == 1) {
			score += getScore();
		} else if (getScore() > 1) {
			score = score+(getScore()*getScore());
			System.out.println(GAME_COMBO_MESSAGE + getScore());
		}
	}
	
	public void checkFilled() {
		for (int i = MAX_VERTICAL_LENGTH-10; i < MAX_VERTICAL_LENGTH; i++) {
			int cnt = 0;
			for (int j = 0; j < MAX_HORIZONTAL_LENGTH; j++) {
				if (map[i][j] == 1) {
					cnt++;
				}
			}
			if (cnt == 10) {
				adjustMap(i);
			}
		}
	}
	
	public void adjustMap(int x) {
		for (int i = x; i > MAX_VERTICAL_LENGTH-10; i--) {
			for (int j = 0; j < MAX_HORIZONTAL_LENGTH; j++) {
				map[i][j] = Tetris.map[i-1][j];
			}
		}
		for (int i = 0; i < MAX_HORIZONTAL_LENGTH; i++) {
			map[MAX_VERTICAL_LENGTH-10][i] = 0;
		}
	}
}
