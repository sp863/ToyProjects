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
	
	
	public void isOpponentKingOnCheck() {
		Set<String> aliveUnitKeyList = this.aliveUnitList.keySet();
		int checkCount = 0;
		for (String key : aliveUnitKeyList) {
			Unit unit = this.aliveUnitList.get(key);
			int currentUnitPositionY = unit.getUnitLocationPoint().getY();
			int currentUnitPositionX = unit.getUnitLocationPoint().getX();
			if (unit.unitCheckKing(currentUnitPositionY, currentUnitPositionX)) {
				checkCount++;
			}
		}
		if (checkCount > 0) {
			opponentPlayer.setKingOnCheck(true);
			return;
		}
		opponentPlayer.setKingOnCheck(false);
	}
	
	public boolean isPlayerTileOnCheck(int y, int x) {
		HashMap<String, Unit> opponentPlayerUnitList = opponentPlayer.getAliveUnitList();
		Set<String> opponentKeyList = opponentPlayer.getAliveUnitList().keySet();
		int checkCount = 0;
		for (String key : opponentKeyList) {
			if (opponentPlayerUnitList.get(key).unitCheckTile(y, x)) {
				checkCount++;
			}
		}
		if (checkCount > 0) {
			return true;
		}
		return false;
	}
	
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
