package tetris;

import java.util.ArrayList;
import java.util.Random;

public class BlockT extends Block {
	private int dir = 1;
	
	public BlockT () {
		super.blockNum = 1004;
		super.blockName = "T";
		setPoints();
	}
	
	public int randomStart() {
		Random ran = new Random();
		int r = ran.nextInt(8)+1;
		return r;
	}
	
	@Override
	public void setPoints() {
		super.points = new ArrayList<>();
		int y = 0;
		int start = randomStart();
		
		Point p = new Point(y, start);
		super.points.add(p);
		for (int i = start-1; i < start-1+(BLOCK_LEN-1); i++) {
			Point a = new Point(y+1, i);
			super.points.add(a);
		}
	}
	
	public boolean checkRotate(int y, int x) {
		if (y < 0 || y > Tetris.V_MAX || x < 0 || x > Tetris.H_MAX) {
			return false;
		}
		return true;
	}

	@Override
	public void rotateBlock() {
		this.dir++;
		if (this.dir > 4) {
			this.dir = 1;
		}
		int y = points.get(0).getY();
		int x = points.get(0).getX();
		
		if (dir == 1) {
			rotateDir1(y,x);
		} else if (dir == 2) {
			rotateDir2(y,x);
		} else if (dir == 3) {
			rotateDir3(y,x);
		} else if (dir == 4) {
			rotateDir4(y,x);
		}
	}
	
	public void rotateDir1(int y, int x) {
		if (x == Tetris.H_MAX-2) {
			points.get(0).setY(y-1);
			int b = 1;
			for (int i = 0; i < BLOCK_LEN-1; i++) {
				points.get(b).setY(y);
				points.get(b).setX(x-1+i);
				b++;
			}
		} else {
			points.get(0).setY(y-1);
			points.get(0).setX(x+1);
			int b = 1;
			for (int i = 0; i < BLOCK_LEN-1; i++) {
				points.get(b).setY(y);
				points.get(b).setX(x+i);
				b++;
			}
		}
	}
	
	public void rotateDir2(int y, int x) {
		points.get(0).setY(y+1);
		points.get(0).setX(x+1);
		int b = 1;
		for (int i = 0; i < BLOCK_LEN-1; i++) {
			points.get(b).setY(y+i);
			points.get(b).setX(x);
			b++;
		}
	}
	
	public void rotateDir3(int y, int x) {
		if (x == 1) {
			points.get(0).setY(y+1);
			points.get(0).setX(x);
			int b = 1;
			for (int i = 0; i < BLOCK_LEN-1; i++) {
				points.get(b).setY(y);
				points.get(b).setX(i);
				b++;
			}
		} else {
			points.get(0).setY(y+1);
			points.get(0).setX(x-1);
			int b = 1;
			for (int i = 0; i < BLOCK_LEN-1; i++) {
				points.get(b).setY(y);
				points.get(b).setX(x-2+i);
				b++;
			}
		}
	}
	
	public void rotateDir4(int y, int x) {
		points.get(0).setY(y-1);
		points.get(0).setX(x-1);
		int b = 1;
		for (int i = 0; i < BLOCK_LEN-1; i++) {
			points.get(b).setY(y-2+i);
			points.get(b).setX(x);
			b++;
		}
	}
	

}
