package block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import tetris.Point;
import tetris.Tetris;
import static utils.Constant.*;

public class BlockController {
	private Random random;
	
	public BlockController (Random random) {
		this.random = random;
	}
	
	public void addBlockSpace(ArrayList<Point> points) {
		for (Point p : points) {
			int y = p.getY();
			int x = p.getX();
			Tetris.map[y][x] = 1;
		}
	}
	
	public void removeBlockSpace(ArrayList<Point> points) {
		for (Point p : points) {
			int y = p.getY();
			int x = p.getX();
			Tetris.map[y][x] = 0;
		}
	}
	
	public HashMap<Integer,Block> createBlockList() {
		HashMap<Integer, Block> map = new HashMap<>();
		map.put(BLOCK_I, new BlockI());
		map.put(BLOCK_L, new BlockL());
		map.put(BLOCK_SQUARE, new BlockSquare());
		map.put(BLOCK_T, new BlockT());
		map.put(BLOCK_Z, new BlockZ());
		
		return map;
	}
	
	public Block generateBlock() {
		HashMap<Integer, Block> temp = createBlockList();
		int num = random.nextInt(5)+1001;
		Set<Integer> keys = temp.keySet();
		
		for (int key : keys) {
			if (key == num) {
				return temp.get(num);
			}
		}
		return null;
	}
	
	public Block generateBlock(int blockType) {
		HashMap<Integer, Block> temp = createBlockList();
		int num = blockType;
		Set<Integer> keys = temp.keySet();
		
		for (int key : keys) {
			if (key == num) {
				return temp.get(num);
			}
		}
		return null;
	}
}
