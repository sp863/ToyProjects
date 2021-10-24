package player;

import static utils.Constant.KING_NAME;

import java.util.HashMap;
import java.util.Set;

import chess.Unit;
import utils.OutputView;

public abstract class Player {

	protected Player opponentPlayer;
	protected String playerColor;
	protected boolean isKingOnCheck;
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
	
	public Player getOpponentPlayer() {
		return opponentPlayer;
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
	
	public Unit getTakenUnit(String unitCode) {
		return takenUnitList.get(unitCode);
	}
	
	public boolean isKingOnCheck() {
		return isKingOnCheck;
	}

	public void setKingOnCheck(boolean isKingOnCheck) {
		this.isKingOnCheck = isKingOnCheck;
	}
}
