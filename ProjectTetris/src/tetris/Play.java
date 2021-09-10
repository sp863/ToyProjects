package tetris;

import java.util.Scanner;

public class Play {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Tetris t = Tetris.getInstance();
		t.runGame();
	}
}
