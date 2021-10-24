package chess;

import player.Player;
import static utils.Constant.*;

public class Pawn extends Unit {

	public Pawn (Player opponentPlayer, String code, String color, UnitLocationPoint unitLocationPoint) {
		super.myOpponent = opponentPlayer;
		super.unitCode = code;
		super.unitColor = color;
		super.isMoved = false;
		super.unitLocationPoint = unitLocationPoint;
	}

	@Override
	public boolean checkUnitSpecificMove(int y, int x, Player player) {
		if (super.unitColor.equals(PLAYER_COLOR_WHITE)) {
			return checkWhitePawnMove(y, x, player);
		} else if (super.unitColor.equals(PLAYER_COLOR_BLACK)) {
			return checkBlackPawnMove(y, x, player);
		}
		return false;
	}
	
	public boolean checkWhitePawnMove(int y, int x, Player player) {
		boolean conditionA = checkWhitePawnMoveRange(y,x);
		boolean conditionB = checkWhitePawnDiagonalMove(y,x);
		if (conditionA || conditionB) {
			if (conditionA) {
				if (checkWhitePawnObstacle(y, x) && checkTakePiece(y, x, player)) {
					super.isMoved = true;
					return true;
				}
			} else if (conditionB) {
				checkTakePiece(y, x, player);
				super.isMoved = true;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkBlackPawnMove(int y, int x, Player player) {
		boolean conditionA = checkBlackPawnMoveRange(y,x);
		boolean conditionB = checkBlackPawnDiagonalMove(y,x);
		if (conditionA || conditionB) {
			if (conditionA) {
				if (checkBlackPawnObstacle(y, x) && checkTakePiece(y, x, player)) {
					super.isMoved = true;
					return true;
				}
			} else if (conditionB) {
				checkTakePiece(y, x, player);
				super.isMoved = true;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkWhitePawnMoveRange(int y, int x) {
		int currentPawnY = super.unitLocationPoint.getY();
		int currentPawnX = super.unitLocationPoint.getX();
		if (currentPawnY > 0) {
			if (super.isMoved == false) {
				for (int i = currentPawnY-1; i >= currentPawnY-2; i--) {
					if (i == y && currentPawnX == x) {
						return true;
					}
				}
			} else {
				if (currentPawnY-1 == y && currentPawnX == x) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkBlackPawnMoveRange(int y, int x) {
		int currentPawnY = super.unitLocationPoint.getY();
		int currentPawnX = super.unitLocationPoint.getX();
		if (currentPawnY < BOARD_LENGTH-1) {
			if (super.isMoved == false) {
				for (int i = currentPawnY+1; i <= currentPawnY+2; i++) {
					if (i == y && currentPawnX == x) {
						return true;
					}
				}
			} else {
				if (currentPawnY+1 == y && currentPawnX == x) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkWhitePawnDiagonalMove(int y, int x) {
		int currentPawnY = super.unitLocationPoint.getY();
		int currentPawnX = super.unitLocationPoint.getX();
		int[] dy = {-1, -1};
		int[] dx = {-1, 1};
		for (int i = 0; i < 2; i++) {
			int tempY = dy[i]+currentPawnY;
			int tempX = dx[i]+currentPawnX;
			if (checkBoardRange(tempY, tempX)) {
				if (ChessGame.chessBoard[tempY][tempX] != null && tempY == y && tempX == x) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean checkBlackPawnDiagonalMove(int y, int x) {
		int currentPawnY = super.unitLocationPoint.getY();
		int currentPawnX = super.unitLocationPoint.getX();
		int[] dy = {1, 1};
		int[] dx = {-1, 1};
		for (int i = 0; i < 2; i++) {
			int tempY = dy[i]+currentPawnY;
			int tempX = dx[i]+currentPawnX;
			if (checkBoardRange(tempY, tempX)) {
				if (ChessGame.chessBoard[tempY][tempX] != null && tempY == y && tempX == x) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkWhitePawnObstacle(int y, int x) {
		int currentPawnY = super.unitLocationPoint.getY();
		int currentPawnX = super.unitLocationPoint.getX();
		for (int i = currentPawnY-1; i > y; i--) {
			if (ChessGame.chessBoard[i][currentPawnX] != null) {
				return false;
			}
		}
		return true;
	}
	
	public boolean checkBlackPawnObstacle(int y, int x) {
		int currentPawnY = super.unitLocationPoint.getY();
		int currentPawnX = super.unitLocationPoint.getX();
		for (int i = currentPawnY+1; i < y; i++) {
			if (ChessGame.chessBoard[i][currentPawnX] != null) {
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
		if (super.unitColor.equals(PLAYER_COLOR_BLACK)) {
			blackPawnCheckKing(y, x);
		} else if (super.unitColor.equals(PLAYER_COLOR_WHITE)) {
			whitePawnCheckKing(y, x);
		}
		return false;
	}
	
	public boolean whitePawnCheckKing(int y, int x) {
		int start = 0;
		if (super.isMoved == false) {
			start = 0;
		} else if (super.isMoved == true) {
			start = 1;
		}
		for (int i = start; i < 4; i++) {
			int tempY = y + WHITE_PAWN_RANGE_DY[i];
			int tempX = x + WHITE_PAWN_RANGE_DX[i];
			if (checkBoardRange(tempY,tempX)) {
				if (ChessGame.chessBoard[tempY][tempX] == myOpponent.getAliveUnit(KING_NAME)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean blackPawnCheckKing(int y, int x) {
		int start = 0;
		if (super.isMoved == false) {
			start = 0;
		} else if (super.isMoved == true) {
			start = 1;
		}
		for (int i = start; i < 4; i++) {
			int tempY = y + BLACK_PAWN_RANGE_DY[i];
			int tempX = x + BLACK_PAWN_RANGE_DX[i];
			if (checkBoardRange(tempY,tempX)) {
				if (ChessGame.chessBoard[tempY][tempX] == myOpponent.getAliveUnit(KING_NAME)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean unitCheckTile(int y, int x) {
		if (super.unitColor.equals(PLAYER_COLOR_BLACK)) {
			blackPawnCheckTile(y, x);
		} else if (super.unitColor.equals(PLAYER_COLOR_WHITE)) {
			whitePawnCheckTile(y, x);
		}
		return false;
	}
	
	public boolean whitePawnCheckTile(int y, int x) {
		int currentPawnY = super.unitLocationPoint.getY();
		int currentPawnX = super.unitLocationPoint.getX();
		int start = 0;
		if (super.isMoved == false) {
			start = 0;
		} else if (super.isMoved == true) {
			start = 1;
		}
		for (int i = start; i < PAWN_DIRECTION_MAX; i++) {
			int tempY = currentPawnY + WHITE_PAWN_RANGE_DY[i];
			int tempX = currentPawnX + WHITE_PAWN_RANGE_DX[i];
			if (checkBoardRange(tempY,tempX)) {
				if (tempY == y && tempX == x) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean blackPawnCheckTile(int y, int x) {
		int currentPawnY = super.unitLocationPoint.getY();
		int currentPawnX = super.unitLocationPoint.getX();
		int start = 0;
		if (super.isMoved == false) {
			start = 0;
		} else if (super.isMoved == true) {
			start = 1;
		}
		for (int i = start; i < PAWN_DIRECTION_MAX; i++) {
			int tempY = currentPawnY + BLACK_PAWN_RANGE_DY[i];
			int tempX = currentPawnX + BLACK_PAWN_RANGE_DX[i];
			if (checkBoardRange(tempY,tempX)) {
				if (tempY == y && tempX == x) {
					return true;
				}
			}
		}
		return false;
	}
}
