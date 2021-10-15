package block;

import java.util.ArrayList;
import java.util.Random;
import tetris.Point;
import tetris.Tetris;
import static utils.Constant.*;

public class BlockL extends Block {
	private int dir = 1;

	public BlockL () {
		blockNum = BLOCK_L;
		super.blockName = "L";
		setPoints();
	}
	
	@Override
	public int randomStart() {
		Random ran = new Random();
		int r = ran.nextInt(9);
		return r;
	}
	
	@Override
	public void setPoints() {
		super.points = new ArrayList<>();
		int y = 0;
		int start = randomStart();
		
		for (int i = 0; i < 4-1; i++) {
			Point p = new Point(y, start);
			super.points.add(p);
			y++;
		}
		Point p = new Point(y-1, start+1);
		super.points.add(p);
	}
	
	@Override
	public void rotateBlock() {
		this.dir++;
		if (this.dir > 4) {
			this.dir = 1;
		}
		int y = points.get(3).getY();
		int x = points.get(3).getX();
		
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
		int tempY = y+1;
		points.get(3).setY(tempY);
		for (int i = 2; i >= 0; i--) {
			points.get(i).setY(tempY);
			points.get(i).setX(x-1);
			tempY--;
		}
	}
	public void rotateDir2(int y, int x) {
		if (x > MAX_HORIZONTAL_LENGTH-BLOCK_LENGTH+1) { //bigger than 7
			int tempX = MAX_HORIZONTAL_LENGTH-BLOCK_LENGTH+1;
			points.get(3).setY(y);
			points.get(3).setX(tempX);
			for (int i = 2; i >= 0; i--) {
				points.get(i).setY(y-1);
				points.get(i).setX(tempX);
				tempX++;
			}
		} else {
			int b = 0;
			for (int i = 2; i >= 0; i--) {
				points.get(i).setY(y-1);
				points.get(i).setX(x+b);
				b++;
			}
		}
	}
	public void rotateDir3(int y, int x) {
		int tempY = y-1;
		points.get(3).setY(tempY);
		for (int i = 2; i >= 0; i--) {
			points.get(i).setY(tempY);
			points.get(i).setX(x+1);
			tempY++;
		}
	}
	public void rotateDir4(int y, int x) {
		if (x == MAX_HORIZONTAL_LENGTH-2) {
			int tempX = MAX_HORIZONTAL_LENGTH-1;
			points.get(3).setX(tempX);
			int b = 0;
			for (int i = 2; i >= 0; i--) {
				points.get(i).setY(y+1);
				points.get(i).setX(tempX-b);
				b++;
			}
		} else if (x < 2) {
			int tempX = 2;
			points.get(3).setX(tempX);
			int b = 0;
			for (int i = 2; i >= 0; i--) {
				points.get(i).setY(y+1);
				points.get(i).setX(tempX-b);
				b++;
			}
		} else {
			int b = 0;
			for (int i = 2; i >= 0; i--) {
				points.get(i).setY(y+1);
				points.get(i).setX(x-b);
				b++;
			}
		}
	}
}
