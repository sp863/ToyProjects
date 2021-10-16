package chess;

public class Rook extends Unit {
	
	public Rook (String code, String color, UnitLocationPoint unitLocationPoint) {
		super.unitCode = code;
		super.unitColor = color;
		super.unitLocationPoint = unitLocationPoint;
	}

	@Override
	public boolean checkMove(int y, int x) {
		// TODO Auto-generated method stub
		return false;
	}

}
