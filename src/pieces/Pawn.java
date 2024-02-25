package pieces;

import java.util.LinkedList;

import chessgame.ChessGame;

public class Pawn extends Piece{

	public Pawn(int xPos, int yPos, boolean isWhite, String type, LinkedList<Piece> pList) {
		super(xPos, yPos, isWhite, type, pList);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move(int xPos, int yPos, LinkedList<Piece> pList) {
		// TODO Auto-generated method stub
//		System.out.println(this.xPos + " " + this.yPos + " " + yPos + " " + xPos);
		if(ChessGame.getPiece(xPos, yPos) != null) {
			if(this.isWhite && (yPos-1 == this.yPos && (xPos-1 == this.xPos || xPos+1 == this.xPos))) {
				super.move(xPos, yPos, pList);
			}else if(!(this.isWhite) && (yPos+1 == this.yPos && (xPos-1 == this.xPos || xPos+1 == this.xPos))){
				super.move(xPos, yPos, pList);
			}
		}else {
			if(((yPos == this.yPos-1 && !this.isWhite) || (yPos == this.yPos+1 && this.isWhite))  && xPos == this.xPos) {
				super.move(xPos, yPos, pList);
			}else if(this.isWhite && (yPos-1 == this.yPos && (xPos-1 == this.xPos || xPos+1 == this.xPos))){
				if(ChessGame.getPiece(xPos, yPos-1) != null) {
					super.move(xPos, yPos, pList);
					checkKill(xPos, yPos-1, pList);
				}
			}else if(!(this.isWhite) && (yPos+1 == this.yPos && (xPos-1 == this.xPos || xPos+1 == this.xPos))){
				if(ChessGame.getPiece(xPos, yPos+1) != null) {
					super.move(xPos, yPos, pList);
					checkKill(xPos, yPos+1, pList);
				}
			}else if(((this.yPos == 1 && this.isWhite && yPos == this.yPos+2) || (this.yPos == 6 && !this.isWhite && yPos == this.yPos-2)) && xPos == this.xPos) {
				super.move(xPos, yPos, pList);
			}
			
		}
		
		if((this.isWhite && this.yPos == 7) || (!(this.isWhite) && this.yPos == 0)){
			Piece newQueen = new Piece(this.xPos, this.yPos, this.isWhite, "Queen", pList);
			this.die(pList);	
		}
			
			
			
		
		
		
	}

}
