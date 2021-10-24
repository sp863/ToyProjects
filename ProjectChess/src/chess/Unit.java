package chess;

import player.Player;
import utils.OutputView;

import static utils.Constant.*;

public abstract class Unit {
	protected Player myOpponent;
	protected String unitColor;
	protected String unitCode;
	protected boolean isMoved;
	protected boolean isDead;
	protected UnitLocationPoint unitLocationPoint;

	public abstract boolean checkUnitSpecificMove(int y, int x, Player player);
	public abstract boolean unitCheckKing(int y, int x);
	public abstract boolean unitCheckTile(int y, int x);
	
	public boolean checkBoardRange(int y, int x) {
		if (y < 0 || y >= BOARD_LENGTH || x < 0 || x >= BOARD_LENGTH) {
			return false;
		}
		return true;
	}
	
	public boolean isMoved() {
		return isMoved;
	}
	
	public void setIsMoved(boolean isMoved) {
		this.isMoved = isMoved;
	}
	
	public boolean isDead() {
		return isDead;
	}
	public void setIsDead(boolean isDead) {
		this.isDead = isDead;
	}
	
	public Player getMyOpponent() {
		return myOpponent;
	}
	
	public String getUnitColor() {
		return unitColor;
	}
	
	public String getUnitCode() {
		return unitCode;
	}
	
	public UnitLocationPoint getUnitLocationPoint() {
		return unitLocationPoint;
	}
}
