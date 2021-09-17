package tetris;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {
	static FileManager instance = new FileManager();
	private String mapFileName = "SavedGameMap.txt";
	private String blockFileName = "SavedGameBlock.txt";
	
	public String orgMapData() {
		String data = "";
		for (int i = 0; i < Tetris.V_MAX; i++) {
			for (int j = 0; j < Tetris.H_MAX; j++) {
				data += Tetris.map[i][j];
				if (j < Tetris.H_MAX-1) {
					data += ",";
				}
			}
			if (i < Tetris.V_MAX-1) {
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
		data += b.blockNum;
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
	
	public Block loadGameBlock() {
		File file = new File(blockFileName);
		Block b = null;
		
		try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr);) {
			//getBlockType -------------------------------------------------------
			String tempBlock = br.readLine();
			int blockType = Integer.parseInt(tempBlock);
			b = Tetris.getInstance().generateBlock(blockType);
			
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
			System.out.println("[Message] Saved game successfully loaded.");
			existFile = true;
		} catch (FileNotFoundException e) {
			System.out.println("[Message] Saved file not found.");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return existFile;
	}
	

}
