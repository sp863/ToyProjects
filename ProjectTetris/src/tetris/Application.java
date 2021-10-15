package tetris;

import java.util.Random;
import java.util.Scanner;

public class Application {
	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		final Random random = new Random();
		
		TetrisController tetrisController = new TetrisController(scanner, random);
		tetrisController.runGame();
	}
}
