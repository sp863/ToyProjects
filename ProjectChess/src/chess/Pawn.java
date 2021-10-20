package chess;

import player.Player;
import static utils.Constant.*;

public class Pawn extends Unit {
	private boolean isMoved = false;

	public Pawn (Player opponentPlayer, String code, String color, UnitLocationPoint unitLocationPoint) {
		super.myOpponent = opponentPlayer;
		super.unitCode = code;
		super.unitColor = color;
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
					isMoved = true;
					return true;
				}
			} else if (conditionB) {
				checkTakePiece(y, x, player);
				isMoved = true;
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
					isMoved = true;
					return true;
				}
			} else if (conditionB) {
				checkTakePiece(y, x, player);
				isMoved = true;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkWhitePawnMoveRange(int y, int x) {
		int currentY = super.unitLocationPoint.getY();
		int currentX = super.unitLocationPoint.getX();
		if (currentY > 0) {
			if (isMoved == false) {
				for (int i = currentY-1; i >= currentY-2; i--) {
					if (i == y && currentX == x) {
						return true;
					}
				}
			} else {
				if (currentY-1 == y && currentX == x) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkWhitePawnDiagonalMove(int y, int x) {
		int currentY = super.unitLocationPoint.getY();
		int currentX = super.unitLocationPoint.getX();
		int[] dy = {-1, -1};
		int[] dx = {-1, 1};
		for (int i = 0; i < 2; i++) {
			int tempY = dy[i]+currentY;
			int tempX = dx[i]+currentX;
			if (checkBoardRange(tempY, tempX)) {
				if (ChessGame.chessBoard[tempY][tempX] != null && tempY == y && tempX == x) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkBlackPawnMoveRange(int y, int x) {
		int currentY = super.unitLocationPoint.getY();
		int currentX = super.unitLocationPoint.getX();
		if (currentY < BOARD_LENGTH-1) {
			if (isMoved == false) {
				for (int i = currentY+1; i <= currentY+2; i++) {
					if (i == y && currentX == x) {
						return true;
					}
				}
			} else {
				if (currentY+1 == y && currentX == x) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean checkBlackPawnDiagonalMove(int y, int x) {
		int currentY = super.unitLocationPoint.getY();
		int currentX = super.unitLocationPoint.getX();
		int[] dy = {1, 1};
		int[] dx = {-1, 1};
		for (int i = 0; i < 2; i++) {
			int tempY = dy[i]+currentY;
			int tempX = dx[i]+currentX;
			if (checkBoardRange(tempY, tempX)) {
				if (ChessGame.chessBoard[tempY][tempX] != null && tempY == y && tempX == x) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkWhitePawnObstacle(int y, int x) {
		int currentY = super.unitLocationPoint.getY();
		int currentX = super.unitLocationPoint.getX();
		
		for (int i = currentY-1; i > y; i--) {
			if (ChessGame.chessBoard[i][currentX] != null) {
				return false;
			}
		}
		return true;
	}
	
	public boolean checkBlackPawnObstacle(int y, int x) {
		int currentY = super.unitLocationPoint.getY();
		int currentX = super.unitLocationPoint.getX();
		
		for (int i = currentY+1; i < y; i++) {
			if (ChessGame.chessBoard[i][currentX] != null) {
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
		int[] pawnRange_dy = {-2,-1,-1,-1};
		int[] pawnRange_dx = {0,-1,0,-1};
		int start = 0;
		if (isMoved == false) {
			start = 0;
		} else if (isMoved == true) {
			start = 1;
		}
		for (int i = start; i < 4; i++) {
			int tempY = y + pawnRange_dy[i];
			int tempX = x + pawnRange_dx[i];
			if (checkBoardRange(tempY,tempX)) {
				if (ChessGame.chessBoard[tempY][tempX] == myOpponent.getAliveUnit(KING_NAME)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean blackPawnCheckKing(int y, int x) {
		int[] pawnRange_dy = {2,1,1,1};
		int[] pawnRange_dx = {0,-1,0,-1};
		int start = 0;
		if (isMoved == false) {
			start = 0;
		} else if (isMoved == true) {
			start = 1;
		}
		for (int i = start; i < 4; i++) {
			int tempY = y + pawnRange_dy[i];
			int tempX = x + pawnRange_dx[i];
			if (checkBoardRange(tempY,tempX)) {
				if (ChessGame.chessBoard[tempY][tempX] == myOpponent.getAliveUnit(KING_NAME)) {
					return true;
				}
			}
		}
		return false;
	}
}
