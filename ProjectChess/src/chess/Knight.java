package chess;

import player.Player;
import static utils.Constant.*;

public class Knight extends Unit {
	private int[]knightPossibleRange_dy = {-2,-2,-1,1,2,2,1,-1};
	private int[]knightPossibleRange_dx = {-1,1,2,2,1,-1,-2,-2};
	
	public Knight (Player opponentPlayer, String code, String color, UnitLocationPoint unitLocationPoint) {
		super.myOpponent = opponentPlayer;
		super.unitCode = code;
		super.unitColor = color;
		super.unitLocationPoint = unitLocationPoint;
	}


	@Override
	public boolean checkUnitSpecificMove(int y, int x, Player player) {
		if (checkUnitMoveRange(y, x) && checkTakePiece(y, x, player)) {
			return true;
		}
		return false;
	}
	
	public boolean checkUnitMoveRange(int y, int x) {
		int currentY = super.unitLocationPoint.getY();
		int currentX = super.unitLocationPoint.getX();
		
		for (int i = 0; i < KNIGHT_DIRECTION_MAX; i++) {
			int tempY = currentY + knightPossibleRange_dy[i];
			int tempX = currentX + knightPossibleRange_dx[i];
			if (checkBoardRange(tempY, tempX)) {
				if (tempY == y && tempX == x) {
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
		for (int i = 0; i < KNIGHT_DIRECTION_MAX; i++) {
			int tempY = y + knightPossibleRange_dy[i];
			int tempX = x + knightPossibleRange_dx[i];
			if (checkBoardRange(tempY, tempX)) {
				if (ChessGame.chessBoard[tempY][tempX] == myOpponent.getAliveUnit(KING_NAME)) {
					return true;
				}
			}
		}
		return false;
	}
}
