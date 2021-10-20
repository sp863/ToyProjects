package chess;

import player.Player;
import static utils.Constant.*;

public class Rook extends Unit {
	private int[] rookPossibleRange_dy = {-1,0,1,0};
	private int[] rookPossibleRange_dx = {0,1,0,-1};
	private int direction = 0;
	private int distanceToTarget = 0;
	
	public Rook (Player opponentPlayer, String code, String color, UnitLocationPoint unitLocationPoint) {
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
		
		for (int i = 0; i < ROOK_DIRECTION_MAX; i++) {
			for (int j = 1; j < BOARD_LENGTH; j++) {
				int tempY = currentY+(rookPossibleRange_dy[i]*j);
				int tempX = currentX+(rookPossibleRange_dx[i]*j);
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
			int tempY = currentY+(rookPossibleRange_dy[direction]*i);
			int tempX = currentX+(rookPossibleRange_dx[direction]*i);
			
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

	@Override
	public boolean unitCheckKing(int y, int x) {
		if (unitCheckKingRange(y, x) && unitCheckKingObstacle(y, x)) {
			return true;
		}
		return false;
	}
	
	public boolean unitCheckKingRange(int y, int x) {
		for (int i = 0; i < ROOK_DIRECTION_MAX; i++) {
			for (int j = 1; j < BOARD_LENGTH; j++) {
				int tempY = y+(rookPossibleRange_dy[i]*j);
				int tempX = x+(rookPossibleRange_dx[i]*j);
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
			int tempY = y + (rookPossibleRange_dy[direction]*i);
			int tempX = x + (rookPossibleRange_dx[direction]*i);
			if (ChessGame.chessBoard[tempY][tempX] != null) {
				return false;
			}
		}
		return true;
	}
}
