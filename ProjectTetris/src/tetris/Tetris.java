package tetris;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Tetris {
	private static Tetris instance = new Tetris();
	
	public static Tetris getInstance() {
		return instance;
	}

	Scanner scan = new Scanner(System.in);
	Random ran = new Random();
	
	final static int V_MAX = 14;
	final static int H_MAX = 10;
	final static int V_SMAX = 4;
	final static int H_SMAX = 10;
	
	static int[][] map = new int[V_MAX][H_MAX];
	static int score = 0;
	
	private String currentBlock = "";
	
	public String getCurrentBlock() {
		return currentBlock;
	}

	public void showMainMenu() {
		System.out.println("=====TETRIS=====");
		System.out.println("1. Play New Game");
		System.out.println("2. Load Game");
		System.out.println("0. Exit");
		System.out.println("================");
	}
	
	public void showPlayerControl() {
		System.out.println("=======================================");
		System.out.printf("[SCORE - %d]\n", score);
		System.out.println("1. Move LEFT");
		System.out.println("2. Move RIGHT");
		System.out.println("3. Rotate");
		System.out.println("4. Drop");
		System.out.println("5. Save Game");
		System.out.println("0. Exit");
		System.out.println("=======================================");
	}
	
	public void addBlockSpace(ArrayList<Point> points) {
		for (Point p : points) {
			int y = p.getY();
			int x = p.getX();
			map[y][x] = 1;
		}
	}
	public void removeBlockSpace(ArrayList<Point> points) {
		for (Point p : points) {
			int y = p.getY();
			int x = p.getX();
			map[y][x] = 0;
		}
	}
	
	public void showSpace(ArrayList<Point> points) {
		addBlockSpace(points);
		System.out.println("=======================================");
		for (int i = 0; i < V_SMAX; i++) {
			for (int j = 0; j < H_SMAX; j++) {
				System.out.printf("%d ", map[i][j]);
			}
			System.out.println();
		}
		System.out.println("=======================================");
		removeBlockSpace(points);
	}
	public void showMap() {
		for (int i = V_MAX-10; i < V_MAX; i++) {
			for (int j = 0; j < H_MAX; j++) {
				System.out.printf("%d ", map[i][j]);
			}
			System.out.println();
		}
	}
	
	public void showGamePlayMenu(ArrayList<Point> p) {
		showSpace(p);
		showMap();
		showPlayerControl();
	}
	
	public void adjustMap(int x) {
		for (int i = x; i > V_MAX-10; i--) {
			for (int j = 0; j < H_MAX; j++) {
				map[i][j] = map[i-1][j];
			}
		}
		for (int i = 0; i < H_MAX; i++) {
			map[V_MAX-10][i] = 0;
		}
	}
	
	public void checkFilled() {
		for (int i = V_MAX-10; i < V_MAX; i++) {
			int cnt = 0;
			for (int j = 0; j < H_MAX; j++) {
				if (map[i][j] == 1) {
					cnt++;
				}
			}
			if (cnt == 10) {
				adjustMap(i);
			}
		}
	}
	
	public int getScore() {
		int score = 0;
		for (int i = V_MAX-10; i < V_MAX; i++) {
			int cnt = 0;
			for (int j = 0; j < H_MAX; j++) {
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
			System.out.println("*****!!COMBO!!***** X" + getScore());
		}
	}
	
	public HashMap<Integer,Block> createBlockList() {
		HashMap<Integer, Block> map = new HashMap<>();
		map.put(1001, new BlockI());
		map.put(1002, new BlockL());
		map.put(1003, new BlockSquare());
		map.put(1004, new BlockT());
		map.put(1005, new BlockZ());
		
		return map;
	}
	
	public Block generateBlock() {
		HashMap<Integer, Block> temp = createBlockList();
		int num = ran.nextInt(5)+1001;
		Set<Integer> keys = temp.keySet();
		
		for (int key : keys) {
			if (key == num) {
				return temp.get(num);
			}
		}
		return null;
	}
	
	public boolean playGame(Block b, ArrayList<Point> points) {
		while (true) {
			showGamePlayMenu(points);
			int choose = scan.nextInt();
			//이상한숫자를 입력할수도 있으니 예외처리 필요?
			if (choose == Button.BUTTON_ONE.ordinal()) {
				b.moveBlock(choose);
			} else if (choose == Button.BUTTON_TWO.ordinal()) {
				b.moveBlock(choose);
			} else if (choose == Button.BUTTON_THREE.ordinal()) {
				b.rotateBlock();
			} else if (choose == Button.BUTTON_FOUR.ordinal()) {
				b.dropBlock();
				getTotalScore();
				checkFilled();
				return false;
			} else if (choose == Button.BUTTON_FIVE.ordinal()) {
				FileManager.instance.saveGame();
			} else if (choose == Button.BUTTON_ZERO.ordinal()) {
				return true;
			}
		}
	}
	
	public void startGame() {
		while (true) {
			Block b = generateBlock();
			currentBlock = b.blockName;
			ArrayList<Point> points = b.getPoints();
			if (playGame(b,points)) {
				return;
			}
		}
	}
	
	public void runGame() {
		while (true) {
			showMainMenu();
			int choose = scan.nextInt();
			if (choose == Button.BUTTON_ONE.ordinal()) {
				startGame();
			} else if (choose == Button.BUTTON_TWO.ordinal()) {
				FileManager.instance.loadGame();
			}
			else if (choose == Button.BUTTON_ZERO.ordinal()) {
				System.out.println("Thank you for Playing!");
				break;
			}
		}
	}
}
