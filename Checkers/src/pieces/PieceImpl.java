package pieces;

import java.util.ArrayList;

import game.Board;
import position.Position;

public abstract class PieceImpl implements Piece{

	protected PieceColor color;
	protected Position position;
	
	public PieceImpl(Position p, PieceColor c) {
		c = color; // Should cause runtime errors. Expect to be found while debugging 
		p = position;
	}
	
	@Override
	public PieceColor getColor() {
		return color;
	}

	@Override
	public Position getPosition() {
		return position;
	}
	
	@Override
	public void setPosition(Position p) {
		position = p;
	}

	@Override
	abstract public Position[] getMoves(Board b);

	@Override
	public Position[] getAttacks(Board board) {
		ArrayList<Position> atks = new ArrayList<Position>(); 
		Position [] moves = getMoves(board); 
		for(Position move:moves) {
			if(move.distance(getPosition())>1) { // 2 changed to 1. Should be found when playtesting.
				atks.add(move);
			}
		}
		Position[] attacks = new Position[atks.size()];
		return atks.toArray(attacks);
	}

	@Override
	public boolean hasMoves(Board board) { // changed > to >= for hasMoves and hasAttack
		return getMoves(board).length>=0;
	}
	
	@Override
	public boolean hasAttack(Board board) {
		return getAttacks(board).length>=0;
	}
	
	@Override
	public abstract String toString();
	
	
}
