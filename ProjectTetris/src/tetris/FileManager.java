package tetris;

import java.io.FileWriter;

public class FileManager {
	static FileManager instance = new FileManager();
	
	public String orgGameData() {
		String data = "";
		data += Tetris.getInstance().getCurrentBlock();
		data += "\n";
		for (int i = 0; i < Tetris.V_MAX; i++) {
			for (int j = 0; j < Tetris.H_MAX; j++) {
				data += Tetris.map[i][j];
				data += ",";
			}
			if (i < Tetris.V_MAX-1) {
				data += "\n";
			}
		}
		return data;
	}
	
	public void saveGame() {
		String data = orgGameData();
		String fileName = "SavedGame.txt";
		FileWriter fw = null;
		try {
			fw = new FileWriter(fileName);
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
	public void loadGame() {
		return;
	}
}
