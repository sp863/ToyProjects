package chess;

import player.Player;
import utils.OutputView;

import static utils.Constant.*;

public abstract class Unit {
	protected Player myOpponent;
	protected String unitColor;
	protected String unitCode;
	protected UnitLocationPoint unitLocationPoint;
	protected boolean isDead = false;

	public abstract boolean checkUnitSpecificMove(int y, int x, Player player);
	public abstract boolean unitCheckKing(int y, int x);
	
	public boolean checkBoardRange(int y, int x) {
		if (y < 0 || y >= BOARD_LENGTH || x < 0 || x >= BOARD_LENGTH) {
			return false;
		}
		return true;
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
	
	public boolean getIsDead() {
		return isDead;
	}
	
	public void setIsDead(boolean isDead) {
		this.isDead = isDead;
	}

}
