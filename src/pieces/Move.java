package pieces;

import chessgame.ChessGame;

public class Move {

	public int xStart;
	public int yStart;
	public int xEnd;
	public int yEnd;
	public Piece target;
	
	
	public Move(int xStart, int yStart, int xEnd, int yEnd, Piece target) {
		super();
		this.xStart = xStart;
		this.yStart = yStart;
		this.xEnd = xEnd;
		this.yEnd = yEnd;
		this.target = target;
		
		if(target != null && target.type.compareToIgnoreCase("king") == 0) {
			ChessGame.getPiece(xStart, yStart).isCheck = true;
		}
	}
	
	
	
	
	
	

}
