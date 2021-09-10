package tetris;

import java.util.ArrayList;
import java.util.Random;

public class BlockI extends Block {
	private int dir = 1;
	
	public BlockI () {
		super.blockNum = 1001;
		super.blockName = "I";
		setPoints();
	}
	
	public int randomStart() {
		Random ran = new Random();
		int r = ran.nextInt(10);
		return r;
	}
	
	@Override
	public void setPoints() {
		super.points = new ArrayList<>();
		int y = 0;
		int start = randomStart();
		
		for (int i = 0; i < 4; i++) {
			Point p = new Point(y, start);
			super.points.add(p);
			y++;
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
		if (this.dir > 2) {
			this.dir = 1;
		}
		int y = points.get(3).getY();
		int x = points.get(3).getX();
		
		if (dir == 1) {
			rotateDir1(y,x);
		} else if (dir == 2) {
			rotateDir2(y,x);
		}
	}
	
	public void rotateDir1(int y, int x) {
		int cnt = 0;
		for (int i = 1; i <= 3; i++) {
			int yy = y-i;
			int xx = x;
			
			if (checkRotate(yy, xx)) {
				cnt++;
			}
		}
		if (cnt == 3) {
			int b = 1;
			for (int i = 2; i >= 0; i--) {
				points.get(i).setY(y-b);
				points.get(i).setX(x);
				b++;
			}
		}
	}
	public void rotateDir2(int y, int x) {
		if (x > Tetris.H_MAX-BLOCK_LEN) { //bigger than 6
			int temp = Tetris.H_MAX-BLOCK_LEN;
			for (int i = BLOCK_LEN-1; i >= 0; i--) {
				points.get(i).setY(y);
				points.get(i).setX(temp);
				temp++;
			}
		} else {
			int b = 1;
			for (int i = 2; i >= 0; i--) {
				points.get(i).setY(y);
				points.get(i).setX(x+b);
				b++;
			}
		}
	}
	

}
