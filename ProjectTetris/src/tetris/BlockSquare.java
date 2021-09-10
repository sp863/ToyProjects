package tetris;

import java.util.ArrayList;
import java.util.Random;

public class BlockSquare extends Block {
	public BlockSquare () {
		super.blockNum = 1003;
		super.blockName = "Square";
		setPoints();
	}
	
	public int randomStart() {
		Random ran = new Random();
		int r = ran.nextInt(Tetris.H_MAX-1);
		return r;
	}
	
	@Override
	public void setPoints() {
		super.points = new ArrayList<>();
		int start = randomStart();
		
		for (int i = 0; i < BLOCK_LEN/2; i++) {
			for (int j = start; j < start+BLOCK_LEN/2; j++) {
				Point p = new Point(i, j);
				super.points.add(p);
			}
		}
	}
	
	@Override
	public void rotateBlock() {
		return;
	}
}
