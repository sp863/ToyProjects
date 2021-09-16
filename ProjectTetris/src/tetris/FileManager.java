package tetris;

import java.io.BufferedReader;
import java.io.File;
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
		FileWriter fw = null;
		try {
			fw = new FileWriter(mapFileName);
			fw.write(data);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
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
		FileWriter fw = null;
		try {
			fw = new FileWriter(blockFileName);
			fw.write(data);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}
	
	public Block loadGameBlock() {
		File file = new File(blockFileName);
		FileReader fr = null;
		BufferedReader br = null;
		Block b = null;
		ArrayList<Point> p = null;
		
		if (file.exists()) {
			try {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				//getBlockType -------------------------------------------------------
				String tempBlock = br.readLine();
				int blockType = Integer.parseInt(tempBlock);
				b = Tetris.getInstance().generateBlock(blockType);
				p = b.getPoints();
				//getPoints -------------------------------------------------------
				while (true) {
					String line = br.readLine();
					if (line == null) {
						break;
					}
					String[] temp = line.split("\n");
					for (int i = 0; i < temp.length; i++) {
						String[] temp2 = line.split(",");
						int y = Integer.parseInt(temp2[0]);
						int x = Integer.parseInt(temp2[1]);
						p.get(i).setY(y);
						p.get(i).setX(x);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fr != null) {
					try {
						fr.close();
					} catch (Exception e2) {
					}
				}
				if (br != null) {
					try {
						br.close();
					} catch (Exception e2) {
					}
				}
			}
		}
		return b;
	}
	
	public boolean loadGameMap() {
		boolean existFile = false;
		
		File file = new File(mapFileName);
		FileReader fr = null;
		BufferedReader br = null;
		
		if (file.exists()) {
			existFile = true;
			
			try {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				//getMap -------------------------------------------------------
				while (true) {
					String line = br.readLine();
					if (line == null) {
						break;
					}
					String[] temp = line.split("\n");
					for (int i = 0; i < temp.length; i++) {
						String[] temp2 = temp[i].split(",");
						for (int j = 0; j < temp2.length; j++) {
							int tempNum = Integer.parseInt(temp2[j]);
							Tetris.map[i][j] = tempNum;
						}
					}
				}
				System.out.println("[Message] Saved game successfully loaded.");
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fr != null) {
					try {
						fr.close();
					} catch (IOException e2) {
					}
				}
				if (br != null) {
					try {
						br.close();
					} catch (IOException e2) {
					}
				}
			}
		}
		return existFile;
	}
	

}
