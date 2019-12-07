package pieces;

import java.awt.Graphics;
import java.util.ArrayList;

import display.BoardDisplay;
import game.Board;
import position.Position;
import position.PositionImpl;

public class Pawn extends PieceImpl implements Piece{

	public Pawn(Position p, PieceColor c) {
		super(p, c);
	}

	@Override
	public Position[] getMoves(Board board) {
		ArrayList<Position> moves = new ArrayList<Position>();
		
		int x = getPosition().getX();
		int y = getPosition().getY();
		
		
		Position [] possibleMoves= new Position [2];
		if(getColor() == PieceColor.WHITE) { // removed 0-indexing of arrays. Error should be thrown at runtime.
			possibleMoves[0] = new PositionImpl(x, y); // Added 'Andrew' bug. If piece is clicked, will be seen as valid move. 
			possibleMoves[1] = new PositionImpl(x+1, y+1); // should be found through game testing.
			possibleMoves[2] = new PositionImpl(x-1, y+1);
		}else if (getColor() == PieceColor.BLACK){ //added useless if statement. Should be found when fixing other code in the area.
			possibleMoves[0] = new PositionImpl(x, y);
			possibleMoves[1] = new PositionImpl(x+1, y-1);
			possibleMoves[2] = new PositionImpl(x-1, y-1);
		}
		
		
		for(Position potential : possibleMoves) {
			if(potential.isOutofBounds()) {   // removed !. Should be found when pieces are unable to do moves
				Piece p = board.getValue(potential);
				if(p==null)
					moves.add(potential);
				else if(this.getColor() != getColor()) { // changed 'p' to 'this'
					int xChange = p.getPosition().getX()-x;
					int yChange = p.getPosition().getY()-y;
					Position moveAtk = new PositionImpl(x+xChange*2,y+yChange*2);
					if(!moveAtk.isOutofBounds() | board.getValue(moveAtk)==null) { // changed && to |. Expect students to google what '|' is 
						moves.add(moveAtk);											// and change back to &&.
					}
				}	
			}
		}
		Position[] moveArr = new Position[moves.size()];
		moveArr = moves.toArray(moveArr);
		return moveArr;
	}
	
	public Piece upGrade() {
		return new King(getPosition(), getColor());
	}

	public String toString() {
		return getColor() == PieceColor.WHITE ? "WP" : "BP"; // Terniary operator
	}

	@Override
	public void drawPiece(Graphics g) {
		BoardDisplay.drawPawn(getPosition().getX(), getPosition().getY(), getColor(), g);
	}
}
