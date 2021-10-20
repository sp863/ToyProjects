package chess;

import player.Player;
import static utils.Constant.*;

public class King extends Unit {
	private int[] kingPossibleRange_dy = {-1,0,1,0,-1,-1,1,1};
	private int[] kingPossibleRange_dx = {0,1,0,-1,-1,1,1,-1};
	private int direction = 0;
	
	public King (Player opponentPlayer, String code, String color, UnitLocationPoint unitLocationPoint) {
		super.myOpponent = opponentPlayer;
		super.unitCode = code;
		super.unitColor = color;
		super.unitLocationPoint = unitLocationPoint;
	}

	@Override
	public boolean checkUnitSpecificMove(int y, int x, Player player) {
		if (checkUnitMoveRange(y,x) && checkTakePiece(y, x, player)) {
			return true;
		}
		return false;
	}
	
	public boolean checkUnitMoveRange(int y, int x) {
		int currentY = super.unitLocationPoint.getY();
		int currentX = super.unitLocationPoint.getX();
		
		for (int i = 0; i < KING_DIRECTION_MAX; i++) {
			int tempY = currentY+kingPossibleRange_dy[i];
			int tempX = currentX+kingPossibleRange_dx[i];
			if (checkBoardRange(tempY, tempX)) {
				if (tempY == y && tempX == x) {
					direction = i;
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkTakePiece(int y, int x, Player player) {
		Unit enemyUnit = ChessGame.chessBoard[y][x];
		if (enemyUnit != null) {
			String enemyUnitCode = enemyUnit.getUnitCode();
			player.getTakenUnitList().put(enemyUnitCode, enemyUnit);
			myOpponent.getAliveUnitList().remove(enemyUnitCode);
		}
		return true;
	}

	@Override
	public boolean unitCheckKing(int y, int x) {
		for (int i = 0; i < KING_DIRECTION_MAX; i++) {
			int tempY = y + kingPossibleRange_dy[i];
			int tempX = x + kingPossibleRange_dx[i];
			if (checkBoardRange(tempY, tempX)) {
				if (ChessGame.chessBoard[tempY][tempX] == myOpponent.getAliveUnit(KING_NAME)) {
					return true;
				}
			}
		}
		return false;
	}

}
