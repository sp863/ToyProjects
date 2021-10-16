package chess;

public abstract class Unit {
	protected String unitColor;
	protected String unitCode;
	protected UnitLocationPoint unitLocationPoint;
	
	public abstract boolean checkMove(int y, int x);
}
