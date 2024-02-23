package pieces;

import java.util.LinkedList;

public class Pawn extends Piece{

	public Pawn(int xPos, int yPos, boolean isWhite, String type, LinkedList<Piece> pList) {
		super(xPos, yPos, isWhite, type, pList);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move(int xPos, int yPos, LinkedList<Piece> pList) {
		// TODO Auto-generated method stub
		System.out.println(this.xPos + " " + this.yPos + " " + yPos + " " + xPos);
		if((yPos == this.yPos-1 && !this.isWhite) || (yPos == this.yPos+1 && this.isWhite)) {
			super.move(xPos, yPos, pList);
		}
		
		
		
	}

}
