package tetris;

import java.util.ArrayList;
import java.util.Random;

public class BlockZ extends Block {
	private int dir = 1;
	
	public BlockZ () {
		super.blockNum = 1005;
		super.blockName = "Z";
		setPoints();
	}
	
	public int randomStart() {
		Random ran = new Random();
		int r = ran.nextInt(7)+1;
		return r;
	}
	
	@Override
	public void setPoints() {
		super.points = new ArrayList<>();
		int y = 1;
		int start = randomStart();
		
		Point p1 = new Point(y, start);
		Point p2 = new Point(y, start+1);
		Point p3 = new Point(y+1, start+1);
		Point p4 = new Point(y+1, start+2);
		super.points.add(p1);
		super.points.add(p2);
		super.points.add(p3);
		super.points.add(p4);
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
		int y = points.get(0).getY();
		int x = points.get(0).getX();
		
		if (dir == 1) {
			rotateDir1(y,x);
		} else if (dir == 2) {
			rotateDir2(y,x);
		}
	}
	
	public void rotateDir1(int y, int x) {
		if (x < 2) {
			y = 1;
			x = 0;
			points.get(0).setY(y);
			points.get(0).setX(x);
			
			points.get(1).setY(y);
			points.get(1).setX(x+1);
			
			points.get(2).setY(y+1);
			points.get(2).setX(x+1);
			
			points.get(3).setY(y+1);
			points.get(3).setX(x+2);
		} else {
			points.get(0).setY(y+1);
			points.get(0).setX(x-2);
			
			points.get(1).setY(y+1);
			points.get(1).setX(x-1);
			
			points.get(2).setY(y+2);
			points.get(2).setX(x-1);
			
			points.get(3).setY(y+2);
			points.get(3).setX(x);
		}
	}
	
	public void rotateDir2(int y, int x) {
		points.get(0).setY(y-1);
		points.get(0).setX(x+2);
		
		points.get(1).setY(y);
		points.get(1).setX(x+2);
		
		points.get(2).setY(y);
		points.get(2).setX(x+1);
		
		points.get(3).setY(y+1);
		points.get(3).setX(x+1);
	}
	

}
