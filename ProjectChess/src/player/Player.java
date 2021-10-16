package player;

import java.util.HashMap;

import chess.Unit;

public abstract class Player {
	protected String playerColor;
	protected HashMap<String, Unit> aliveUnitList;
	protected HashMap<String, Unit> takenUnitList;
	
	public abstract void playerInit();
	
	public abstract void unitInit();
	
	public abstract void bishopInit();
	public abstract void knightInit();
	public abstract void rookInit();
	public abstract void pawnInit();

	public HashMap<String, Unit> getAliveUnitList() {
		return aliveUnitList;
	}
	
	public HashMap<String, Unit> getTakenUnitList() {
		return takenUnitList;
	}
}
