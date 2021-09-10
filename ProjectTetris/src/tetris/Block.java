package tetris;

import java.util.ArrayList;

public abstract class Block {
	final static int BLOCK_LEN = 4;
	protected int blockNum;
	protected String blockName;
	protected ArrayList<Point> points;
	
	public abstract void setPoints();
	public abstract void rotateBlock();
	
	public ArrayList<Point> getPoints() {
		return points;
	}
	// MOVE -------------------------------------------------------
	public boolean checkMove(int x) {
		if (x < 0 || x > Tetris.H_MAX-1) {
			return false;
		}
		return true;
	}
	
	public void moveBlock(int move) {
		if (move == 1) {
			moveDir1();
		} else if (move == 2) {
			moveDir2();
		}
	}
	
	public void moveDir1() {
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			int x = points.get(i).getX()-1;
			if (checkMove(x)) {
				cnt++;
			}
		}
		if (cnt == 4) {
			for (int i = 0; i < 4; i++) {
				int x = points.get(i).getX();
				points.get(i).setX(x-1);
			}
		}
	}
	public void moveDir2() {
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			int x = points.get(i).getX()+1;
			if (checkMove(x)) {
				cnt++;
			}
		}
		if (cnt == 4) {
			for (int i = 0; i < 4; i++) {
				int x = points.get(i).getX();
				points.get(i).setX(x+1);
			}
		}
	}
	
	// DROP -------------------------------------------------------
	public boolean checkDrop() {
		int count = 0;
		for (int i = 0; i < BLOCK_LEN; i++) {
			int y = points.get(i).getY()+1;
			int x = points.get(i).getX();
			if (y >= 0 && y < Tetris.V_MAX && Tetris.map[y][x] == 0) {
				count++;
			}
		}
		if (count == BLOCK_LEN) {
			return false;
		}
		return true;
	}
	
	public void dropOneLine() {
		for (int i = 0; i < BLOCK_LEN; i++) {
			int y = points.get(i).getY();
			points.get(i).setY(y+1);
		}
	}
	
	public void dropBlock() {
		while (true) {
			if (checkDrop()) {
				break;
			}
			dropOneLine();
		}
		for (int i = 0; i < BLOCK_LEN; i++) {
			int y = points.get(i).getY();
			int x = points.get(i).getX();
			Tetris.map[y][x] = 1;
		}
	}
}
