package chess;

import player.Player;
import static utils.Constant.*;

public class King extends Unit {
	
	public King (Player opponentPlayer, String code, String color, UnitLocationPoint unitLocationPoint) {
		super.myOpponent = opponentPlayer;
		super.unitCode = code;
		super.unitColor = color;
		super.unitLocationPoint = unitLocationPoint;
		super.isMoved = false;
	}

	@Override
	public boolean checkUnitSpecificMove(int y, int x, Player player) {
		if (checkUnitMoveRange(y,x) && checkTakePiece(y, x, player)) {
			super.isMoved = true;
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
		int currentKingY = super.unitLocationPoint.getY();
		int currentKingX = super.unitLocationPoint.getX();
		for (int i = 0; i < KING_DIRECTION_MAX; i++) {
			int tempY = currentKingY+KING_MOVE_RANGE_DY[i];
			int tempX = currentKingX+KING_MOVE_RANGE_DX[i];
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
		for (int i = 0; i < KING_DIRECTION_MAX; i++) {
			int tempY = y + KING_MOVE_RANGE_DY[i];
			int tempX = x + KING_MOVE_RANGE_DX[i];
			if (checkBoardRange(tempY, tempX)) {
				if (ChessGame.chessBoard[tempY][tempX] == myOpponent.getAliveUnit(KING_NAME)) {
					return true;
				}
			}
		}
		return false;
	}
}
