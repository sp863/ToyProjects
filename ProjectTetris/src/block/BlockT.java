package block;

import java.util.ArrayList;
import java.util.Random;
import tetris.Point;
import tetris.Tetris;
import static utils.Constant.*;

public class BlockT extends Block {
	private int dir = 1;
	
	public BlockT () {
		blockNum = BLOCK_T;
		super.blockName = "T";
		setPoints();
	}
	
	@Override
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
		for (int i = start-1; i < start-1+(BLOCK_LENGTH-1); i++) {
			Point a = new Point(y+1, i);
			super.points.add(a);
		}
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
		if (x == MAX_HORIZONTAL_LENGTH-2) {
			points.get(0).setY(y-1);
			int b = 1;
			for (int i = 0; i < BLOCK_LENGTH-1; i++) {
				points.get(b).setY(y);
				points.get(b).setX(x-1+i);
				b++;
			}
		} else {
			points.get(0).setY(y-1);
			points.get(0).setX(x+1);
			int b = 1;
			for (int i = 0; i < BLOCK_LENGTH-1; i++) {
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
		for (int i = 0; i < BLOCK_LENGTH-1; i++) {
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
			for (int i = 0; i < BLOCK_LENGTH-1; i++) {
				points.get(b).setY(y);
				points.get(b).setX(i);
				b++;
			}
		} else {
			points.get(0).setY(y+1);
			points.get(0).setX(x-1);
			int b = 1;
			for (int i = 0; i < BLOCK_LENGTH-1; i++) {
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
		for (int i = 0; i < BLOCK_LENGTH-1; i++) {
			points.get(b).setY(y-2+i);
			points.get(b).setX(x);
			b++;
		}
	}
	

}
