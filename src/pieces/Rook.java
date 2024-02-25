package pieces;

import java.util.LinkedList;

public class Rook extends Piece{

	public Rook(int xPos, int yPos, boolean isWhite, String type, LinkedList<Piece> pList) {
		super(xPos, yPos, isWhite, type, pList);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void move(int xPos, int yPos, LinkedList<Piece> pList) {
		// TODO Auto-generated method stub
		if(xPos == this.xPos || yPos == this.yPos) {
//			if(xPos == this.xPos) {
//				//Check Colission from this.yPos till yPos
//			}
			super.move(xPos, yPos, pList);
		}
		
	}

	

}
