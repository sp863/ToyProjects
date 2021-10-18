package player;

import java.util.HashMap;

import chess.Unit;

public abstract class Player {

	protected Player opponentPlayer;
	protected String playerColor;
	protected HashMap<String, Unit> aliveUnitList;
	protected HashMap<String, Unit> takenUnitList;
	
	public abstract void playerInit(Player opponentPlayer);
	
	public abstract void unitInit();
	
	public abstract void bishopInit();
	public abstract void knightInit();
	public abstract void rookInit();
	public abstract void pawnInit();

	public String getPlayerColor() {
		return playerColor;
	}

	public HashMap<String, Unit> getAliveUnitList() {
		return aliveUnitList;
	}
	
	public HashMap<String, Unit> getTakenUnitList() {
		return takenUnitList;
	}
	
	public Unit getAliveUnit(String unitCode) {
		return aliveUnitList.get(unitCode);
	}
}
