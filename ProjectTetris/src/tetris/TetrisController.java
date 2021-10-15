package tetris;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import block.BlockController;
import block.Block;
import utils.OutputView;
import static utils.Constant.*;

public class TetrisController {
	private Scanner scanner;
	private Random random;

	private Tetris tetris;
	private BlockController blockController;
	
	boolean isLoaded = false;

	public TetrisController (Scanner scanner, Random random) {
		tetris = new Tetris();
		blockController = new BlockController(random);
		this.scanner = scanner;
		this.random = random;
	}
	
	public boolean checkGameOver(ArrayList<Point> points) {
		for (int i = 0; i < points.size(); i++) {
			int y = points.get(i).getY();
			int x = points.get(i).getX();
			if (y < BLOCKSPACE_VERTICAL_LENGTH) {
				return true;
			}
		}
		return false;
	}
	
	public boolean playGame(Block b, ArrayList<Point> points) {
		while (true) {
			OutputView.showGamePlayMenu(points, blockController);
			int choose = scanner.nextInt();
			//이상한숫자를 입력할수도 있으니 예외처리 필요?
			if (choose == BUTTON_ONE) {
				b.moveBlock(choose);
			} else if (choose == BUTTON_TWO) {
				b.moveBlock(choose);
			} else if (choose == BUTTON_THREE) {
				b.rotateBlock();
			} else if (choose == BUTTON_FOUR) {
				b.dropBlock();
				if (checkGameOver(points)) {
					System.out.println(GAME_OVER_MESSAGE);
					System.out.println();
					return true;
				}
				tetris.getTotalScore();
				tetris.checkFilled();
				return false;
			} else if (choose == BUTTON_FIVE) {
				FileManager.instance.saveGameMap();
				FileManager.instance.saveGameBlock(b, points);
			} else if (choose == BUTTON_ZERO) {
				isLoaded = false;
				return true;
			}
		}
	}
	
	public void startNewGame() {
		Tetris.map = new int[MAX_VERTICAL_LENGTH][MAX_HORIZONTAL_LENGTH];
		while (true) {
			Block b = blockController.generateBlock();
			ArrayList<Point> points = b.getPoints();
			if (playGame(b,points)) {
				return;
			}
		}
	}
	
	public void startLoadGame() {
		if (FileManager.instance.loadGameMap()) {
			while (true) {
				Block b = null;
				if (isLoaded == false) {
					b = FileManager.instance.loadGameBlock(blockController);
					isLoaded = true;
				} else {
					b = blockController.generateBlock();
				}
				ArrayList<Point> points = b.getPoints();
				if (playGame(b,points)) {
					return;
				}
			}
		}
	}
	
	public void runGame() {
		while (true) {
			OutputView.showMainMenu();
			int choose = scanner.nextInt();
			if (choose == BUTTON_ONE) {
				startNewGame();
			} else if (choose == BUTTON_TWO) {
				startLoadGame();
			} else if (choose == BUTTON_ZERO) {
				System.out.println(GAME_END_MESSAGE);
				break;
			}
		}
	}
}
