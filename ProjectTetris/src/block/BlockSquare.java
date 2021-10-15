package block;

import java.util.ArrayList;
import java.util.Random;
import tetris.Point;
import tetris.Tetris;
import static utils.Constant.*;

public class BlockSquare extends Block {
	
	public BlockSquare () {
		blockNum = BLOCK_SQUARE;
		super.blockName = "Square";
		setPoints();
	}
	
	@Override
	public int randomStart() {
		Random ran = new Random();
		int r = ran.nextInt(MAX_HORIZONTAL_LENGTH-1);
		return r;
	}
	
	@Override
	public void setPoints() {
		super.points = new ArrayList<>();
		int start = randomStart();
		
		for (int i = 0; i < BLOCK_LENGTH/2; i++) {
			for (int j = start; j < start+BLOCK_LENGTH/2; j++) {
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
