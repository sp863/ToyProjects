package tetris;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import block.Block;
import block.BlockController;
import static utils.Constant.*;

public class FileManager {
	static FileManager instance = new FileManager();
	private String mapFileName = MAP_FILENAME;
	private String blockFileName = BLOCK_FILENAME;
	
	public String orgMapData() {
		String data = "";
		for (int i = 0; i < MAX_VERTICAL_LENGTH; i++) {
			for (int j = 0; j < MAX_HORIZONTAL_LENGTH; j++) {
				data += Tetris.map[i][j];
				if (j < MAX_HORIZONTAL_LENGTH-1) {
					data += ",";
				}
			}
			if (i < MAX_VERTICAL_LENGTH-1) {
				data += "\n";
			}
		}
		return data;
	}
	
	public void saveGameMap() {
		String data = orgMapData();
		try (FileWriter fw = new FileWriter(mapFileName)){
			fw.write(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String orgBlockData(Block b, ArrayList<Point> points) {
		String data = "";
		ArrayList<Point> p = points;
		data += b.getBlockNum();
		data += "\n";
		for (int i = 0; i < p.size(); i++) {
			data += p.get(i).getY();
			data += ",";
			data += p.get(i).getX();
			if (i < p.size()-1) {
				data += "\n";
			}
		}
		return data;
	}
	
	public void saveGameBlock(Block b, ArrayList<Point> points) {
		String data = orgBlockData(b, points);
		try (FileWriter fw = new FileWriter(blockFileName)){
			fw.write(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Block loadGameBlock(BlockController blockController) {
		File file = new File(blockFileName);
		Block b = null;
		
		try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr);) {
			//getBlockType -------------------------------------------------------
			String tempBlock = br.readLine();
			int blockType = Integer.parseInt(tempBlock);
			b = blockController .generateBlock(blockType);
			
			//getPoints -------------------------------------------------------
			int k = 0;
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				String[] temp = line.split(",");
				int y = Integer.parseInt(temp[0]);
				int x = Integer.parseInt(temp[1]);
				b.getPoints().get(k).setY(y);
				b.getPoints().get(k).setX(x);
				k++;
			}
		} catch (FileNotFoundException e) {
		} catch (IOException e1) {
		}
		return b;
	}
	
	public boolean loadGameMap() {
		boolean existFile = false;
		
		File file = new File(mapFileName);
		
		try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr);){
			//getMap -------------------------------------------------------
			Tetris.map = new int[MAX_VERTICAL_LENGTH][MAX_HORIZONTAL_LENGTH];
			int k = 0;
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				String[] temp = line.split(",");
				for (int i = 0; i < temp.length; i++) {
					int tempNum = Integer.parseInt(temp[i]);
					Tetris.map[k][i] = tempNum;
				}
				k++;
			}
			System.out.println(GAME_LOAD_MESSAGE);
			existFile = true;
		} catch (FileNotFoundException e) {
			System.out.println(GAME_FILE_NOTFOUND_MESSAGE);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return existFile;
	}
}
