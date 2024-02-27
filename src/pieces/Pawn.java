package pieces;

import java.util.LinkedList;

import chessgame.ChessGame;

public class Pawn extends Piece{

	private Pawn(int xPos, int yPos, boolean isWhite, String type, LinkedList<Piece> pList) {
		super(xPos, yPos, isWhite, type, pList);
	}
	
	
	
	public static Pawn create(int xPos, int yPos, boolean isWhite, String type, LinkedList<Piece> pList) {
		Pawn a = new Pawn(xPos, yPos, isWhite, type, pList);
		a.preProcess();
		a.possibleMove.add(new Move(xPos, yPos, xPos, yPos + (isWhite ? 2 : -2), null));
		return a;
	}

	@Override
	public void move(int xPos, int yPos, LinkedList<Piece> pList) {
		
		super.move(xPos, yPos, pList);
		if((this.yPos == 0 && !this.isWhite) || (this.yPos == 7 && this.isWhite)) {
			Piece temp = this;
			ChessGame.changePiece(temp, "queen");
			this.die(pList);
			
		}
		if(possibleMove.isEmpty()) preProcess();
		
	}
	
	@Override
	public void preProcess() {
		
		
		
		for(int i = -1; i < 2; i++) { // i is either -1 or 1 
			for(int j = -1; j < 2; j++) {
				if(i == 0) continue;
				
				if(i == -1 && !this.isWhite ) { // black pawn
					Piece target = ChessGame.getPiece(xPos + j, yPos + i);
					if((j == 0 && target == null)  || (j != 0 && target != null) ) {
//						System.out.println("Hello " + this.xPos + ", " + (this.yPos + i));
						possibleMove.add(new Move(xPos, yPos, xPos + j, yPos + i, target));
					}else if(j != 0 && target == null) {
						target = ChessGame.getPiece(xPos + j, yPos);
						if(target != null && target.yPos == 3 && target.moveCount == 1 && ChessGame.getTotalMoves() == target.moveTime) {
							System.out.println(target.yPos + " " + target.moveCount);
							possibleMove.add(new Move(xPos, yPos, xPos + j, yPos + i, target));
						}
					}
				}else if(i == 1) { // White Pawn
					Piece target = ChessGame.getPiece(xPos + j, yPos + i);
					if((j == 0 && target == null)  || (j != 0 && target != null)) {
//						System.out.println("Hello " + this.xPos + ", " + (this.yPos + i));
						possibleMove.add(new Move(xPos, yPos, xPos + j, yPos + i, target));
					}else if(j != 0 && target == null) {
						target = ChessGame.getPiece(xPos + j, yPos);
						if(target != null && target.yPos == 4 && target.moveCount == 1 && ChessGame.getTotalMoves() == target.moveTime) {
							possibleMove.add(new Move(xPos, yPos, xPos + j, yPos + i, target));
						}
					}
				}
				
			}
		}
	}

}
