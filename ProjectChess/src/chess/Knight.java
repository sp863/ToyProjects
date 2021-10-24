package chess;

import player.Player;
import static utils.Constant.*;

public class Knight extends Unit {
	
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
	
	@Override
	public boolean unitCheckTile(int y, int x) {
		if (checkUnitMoveRange(y, x)) {
			return true;
		}
		return false;
	}
	
	public boolean checkUnitMoveRange(int y, int x) {
		int currentKnightY = super.unitLocationPoint.getY();
		int currentKnightX = super.unitLocationPoint.getX();
		for (int i = 0; i < KNIGHT_DIRECTION_MAX; i++) {
			int tempY = currentKnightY + KNIGHT_MOVE_RANGE_DY[i];
			int tempX = currentKnightX + KNIGHT_MOVE_RANGE_DX[i];
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
			int tempY = y + KNIGHT_MOVE_RANGE_DY[i];
			int tempX = x + KNIGHT_MOVE_RANGE_DX[i];
			if (checkBoardRange(tempY, tempX)) {
				if (ChessGame.chessBoard[tempY][tempX] == myOpponent.getAliveUnit(KING_NAME)) {
					return true;
				}
			}
		}
		return false;
	}
}
