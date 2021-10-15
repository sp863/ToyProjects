package block;

import java.util.ArrayList;
import java.util.Random;
import tetris.Point;
import tetris.Tetris;
import static utils.Constant.*;

public class BlockI extends Block {
	private int dir = 1;
	
	public BlockI () {
		blockNum = BLOCK_I;
		super.blockName = "I";
		setPoints();
	}
	
	@Override
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
		if (x > MAX_HORIZONTAL_LENGTH-BLOCK_LENGTH) { //bigger than 6
			int temp = MAX_HORIZONTAL_LENGTH-BLOCK_LENGTH;
			for (int i = BLOCK_LENGTH-1; i >= 0; i--) {
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
