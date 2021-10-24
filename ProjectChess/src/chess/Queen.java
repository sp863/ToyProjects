package chess;

import player.Player;
import static utils.Constant.*;

public class Queen extends Unit {
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
	

	@Override
	public boolean unitCheckTile(int y, int x) {
		if (checkUnitMoveRange(y, x) && checkUnitObstacle()) {
			return true;
		}
		return false;
	}
	
	public boolean checkUnitMoveRange(int y, int x) {
		int currentQueenY = super.unitLocationPoint.getY();
		int currentQueenX = super.unitLocationPoint.getX();
		
		for (int i = 0; i < QUEEN_DIRECTION_MAX; i++) {
			for (int j = 1; j < BOARD_LENGTH; j++) {
				int tempY = currentQueenY+(QUEEN_MOVE_RANGE_DY[i]*j);
				int tempX = currentQueenX+(QUEEN_MOVE_RANGE_DX[i]*j);
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
		int currentQueenY = super.unitLocationPoint.getY();
		int currentQueenX = super.unitLocationPoint.getX();
		for (int i = 1; i < distanceToTarget; i++) {
			int tempY = currentQueenY+(QUEEN_MOVE_RANGE_DY[direction]*i);
			int tempX = currentQueenX+(QUEEN_MOVE_RANGE_DX[direction]*i);
			
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
				int tempY = y+(QUEEN_MOVE_RANGE_DY[i]*j);
				int tempX = x+(QUEEN_MOVE_RANGE_DX[i]*j);
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
			int tempY = y + (QUEEN_MOVE_RANGE_DY[direction]*i);
			int tempX = x + (QUEEN_MOVE_RANGE_DX[direction]*i);
			if (ChessGame.chessBoard[tempY][tempX] != null) {
				return false;
			}
		}
		return true;
	}
}
