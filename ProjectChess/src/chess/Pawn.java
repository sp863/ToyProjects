package chess;

public class Pawn extends Unit {

	public Pawn (String code, String color, UnitLocationPoint unitLocationPoint) {
		super.unitCode = code;
		super.unitColor = color;
		super.unitLocationPoint = unitLocationPoint;
	}

	@Override
	public boolean checkMove(int y, int x) {
		
		return false;
	}
}
