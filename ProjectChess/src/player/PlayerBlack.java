package player;

import java.util.HashMap;
import chess.Bishop;
import chess.King;
import chess.Knight;
import chess.Pawn;
import chess.Queen;
import chess.Rook;
import chess.UnitLocationPoint;
import static utils.Constant.*;

public class PlayerBlack extends Player{
	
	public PlayerBlack() {
		super();
		playerColor = PLAYER_COLOR_BLACK;
	}

	@Override
	public void playerInit(Player opponentPlayer) {
		super.opponentPlayer = opponentPlayer;
		aliveUnitList = new HashMap<>();
		takenUnitList = new HashMap<>();
		unitInit();
	}

	@Override
	public void unitInit() {
		aliveUnitList.put(KING_NAME, new King(opponentPlayer, KING_NAME, PLAYER_COLOR_BLACK, new UnitLocationPoint(0,4)));
		aliveUnitList.put(QUEEN_NAME, new Queen(opponentPlayer, QUEEN_NAME, PLAYER_COLOR_BLACK, new UnitLocationPoint(0,3)));
		bishopInit();
		knightInit();
		rookInit();
		pawnInit();
	}
	
	@Override
	public void bishopInit() {
		aliveUnitList.put(BISHOP_1_NAME, new Bishop(opponentPlayer, BISHOP_1_NAME, PLAYER_COLOR_BLACK, new UnitLocationPoint(0,5)));
		aliveUnitList.put(BISHOP_2_NAME, new Bishop(opponentPlayer, BISHOP_2_NAME, PLAYER_COLOR_BLACK, new UnitLocationPoint(0,2)));
	}

	@Override
	public void knightInit() {
		aliveUnitList.put(KNIGHT_1_NAME, new Knight(opponentPlayer, KNIGHT_1_NAME, PLAYER_COLOR_BLACK, new UnitLocationPoint(0,6)));
		aliveUnitList.put(KNIGHT_2_NAME, new Knight(opponentPlayer, KNIGHT_2_NAME, PLAYER_COLOR_BLACK, new UnitLocationPoint(0,1)));
	}

	@Override
	public void rookInit() {
		aliveUnitList.put(ROOK_1_NAME, new Rook(opponentPlayer, ROOK_1_NAME, PLAYER_COLOR_BLACK, new UnitLocationPoint(0,7)));
		aliveUnitList.put(ROOK_2_NAME, new Rook(opponentPlayer, ROOK_2_NAME, PLAYER_COLOR_BLACK, new UnitLocationPoint(0,0)));
	}

	@Override
	public void pawnInit() {
		aliveUnitList.put(PAWN_1_NAME, new Pawn(opponentPlayer, PAWN_1_NAME, PLAYER_COLOR_BLACK, new UnitLocationPoint(1,7)));
		aliveUnitList.put(PAWN_2_NAME, new Pawn(opponentPlayer, PAWN_2_NAME, PLAYER_COLOR_BLACK, new UnitLocationPoint(1,6)));
		aliveUnitList.put(PAWN_3_NAME, new Pawn(opponentPlayer, PAWN_3_NAME, PLAYER_COLOR_BLACK, new UnitLocationPoint(1,5)));
		aliveUnitList.put(PAWN_4_NAME, new Pawn(opponentPlayer, PAWN_4_NAME, PLAYER_COLOR_BLACK, new UnitLocationPoint(1,4)));
		aliveUnitList.put(PAWN_5_NAME, new Pawn(opponentPlayer, PAWN_5_NAME, PLAYER_COLOR_BLACK, new UnitLocationPoint(1,3)));
		aliveUnitList.put(PAWN_6_NAME, new Pawn(opponentPlayer, PAWN_6_NAME, PLAYER_COLOR_BLACK, new UnitLocationPoint(1,2)));
		aliveUnitList.put(PAWN_7_NAME, new Pawn(opponentPlayer, PAWN_7_NAME, PLAYER_COLOR_BLACK, new UnitLocationPoint(1,1)));
		aliveUnitList.put(PAWN_8_NAME, new Pawn(opponentPlayer, PAWN_8_NAME, PLAYER_COLOR_BLACK, new UnitLocationPoint(1,0)));
	}
}

