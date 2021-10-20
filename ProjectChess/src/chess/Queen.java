package chess;

import player.Player;
import static utils.Constant.*;

public class Queen extends Unit {
	private int[] queenPossibleRange_dy = {-1,0,1,0,-1,-1,1,1};
	private int[] queenPossibleRange_dx = {0,1,0,-1,-1,1,1,-1};
	private int direction = 0;
	private int distanceToTarget = 0;
	
	public Queen (Player opponentPlayer, String code, String color, UnitLocationPoint unitLocationPoint) {
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
		
		for (int i = 0; i < QUEEN_DIRECTION_MAX; i++) {
			for (int j = 1; j < BOARD_LENGTH; j++) {
				int tempY = currentY+(queenPossibleRange_dy[i]*j);
				int tempX = currentX+(queenPossibleRange_dx[i]*j);
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
			int tempY = currentY+(queenPossibleRange_dy[direction]*i);
			int tempX = currentX+(queenPossibleRange_dx[direction]*i);
			
			if (ChessGame.chessBoard[tempY][tempX] != null) {
				return false;
			}
		}
		return true;
	}
	
	public boolean checkTakePiece(int y, int x, Player player) {
		Unit enemyUnit = ChessGame.chessBoard[y][x];
		if (enemyUnit != null) {
			enemyUnit.setIsDead(true);
			String enemyUnitCode = enemyUnit.getUnitCode();
			player.getTakenUnitList().put(enemyUnitCode, enemyUnit);
			myOpponent.getAliveUnitList().remove(enemyUnitCode);
		}
		return true;
	}

	@Override
	public boolean unitCheckKing(int y, int x) {
		if (unitCheckKingRange(y, x) && unitCheckKingObstacle(y, x)) {
			return true;
		}
		return false;
	}
	
	public boolean unitCheckKingRange(int y, int x) {
		for (int i = 0; i < QUEEN_DIRECTION_MAX; i++) {
			for (int j = 1; j < BOARD_LENGTH; j++) {
				int tempY = y+(queenPossibleRange_dy[i]*j);
				int tempX = x+(queenPossibleRange_dx[i]*j);
				if (checkBoardRange(tempY, tempX)) {
					if (ChessGame.chessBoard[tempY][tempX] == myOpponent.getAliveUnit(KING_NAME)) {
						direction = i;
						distanceToTarget = j;
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean unitCheckKingObstacle(int y, int x) {
		for (int i = 1; i < distanceToTarget; i++) {
			int tempY = y + (queenPossibleRange_dy[direction]*i);
			int tempX = x + (queenPossibleRange_dx[direction]*i);
			if (ChessGame.chessBoard[tempY][tempX] != null) {
				return false;
			}
		}
		return true;
	}
}
