package chess;

import player.Player;
import static utils.Constant.*;

public class Bishop extends Unit {
	private int[] bishopPossibleRange_dy = {-1,-1,1,1};
	private int[] bishopPossibleRange_dx = {-1,1,1,-1};
	private int direction = 0;
	private int distanceToTarget = 0;
	
	public Bishop (Player opponentPlayer, String code, String color, UnitLocationPoint unitLocationPoint) {
		super.myOpponent = opponentPlayer;
		super.unitCode = code;
		super.unitColor = color;
		super.unitLocationPoint = unitLocationPoint;
	}

	@Override
	public boolean checkUnitSpecificMove(int y, int x, Player player) {
		if (checkUnitMoveRange(y,x) && checkUnitObstacle() && checkTakePiece(y, x, player)) {
			return true;
		}
		return false;
	}
	
	public boolean checkUnitMoveRange(int y, int x) {
		int currentY = super.unitLocationPoint.getY();
		int currentX = super.unitLocationPoint.getX();
		
		for (int i = 0; i < BISHOP_DIRECTION_MAX; i++) {
			for (int j = 1; j < BOARD_LENGTH; j++) {
				int tempY = currentY+(bishopPossibleRange_dy[i]*j);
				int tempX = currentX+(bishopPossibleRange_dx[i]*j);
				if (checkBoardRange(tempY, tempX)) {
					if (tempY == y && tempX == x) {
						direction = i;
						distanceToTarget = j;
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean checkUnitObstacle() {
		int currentY = super.unitLocationPoint.getY();
		int currentX = super.unitLocationPoint.getX();
		
		for (int i = 1; i < distanceToTarget; i++) {
			int tempY = currentY+(bishopPossibleRange_dy[direction]*i);
			int tempX = currentX+(bishopPossibleRange_dx[direction]*i);
			
			if (ChessGame.chessBoard[tempY][tempX] != null) {
				return false;
			}
		}
		return true;
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
}
