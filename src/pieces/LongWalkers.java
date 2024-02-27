package pieces;


import java.util.LinkedList;

import chessgame.ChessGame;

public class LongWalkers extends Piece{
	
	public LongWalkers(int xPos, int yPos, boolean isWhite, String type, LinkedList<Piece> pList) {
		super(xPos, yPos, isWhite, type, pList);
		// TODO Auto-generated constructor stub
	}
	
	public void preProcess() {
		super.preProcess();
	}
	
	protected void straightProcess() {
		for(int j = -1 ; j < 2; j++) {
			if(j == 0) continue;
			for(int i = 1; i <= ChessGame.getNumSqrToEdge(xPos, yPos).get(j == 1 ? 2 : 0); i++) {
				Piece target = ChessGame.getPiece(xPos, yPos + (i * j));
				if(target == null) { 
					possibleMove.add(new Move(xPos, yPos, xPos, yPos + (i * j), target));
				}else {
					if(target.isWhite != this.isWhite) {
						possibleMove.add(new Move(xPos, yPos, xPos, yPos + (i * j), target));
					}
					break;
				}
			}
			for(int i = 1; i <= ChessGame.getNumSqrToEdge(xPos, yPos).get(j == 1 ? 1 : 3); i++) {
				Piece target = ChessGame.getPiece(xPos + (i * j), yPos);
				if(target == null) { 
					possibleMove.add(new Move(xPos, yPos, xPos + (i * j), yPos, target));
				}else {
					if(target.isWhite != this.isWhite) {
						possibleMove.add(new Move(xPos, yPos, xPos + (i * j), yPos, target));
					}
					break;
				}
			}
		}
	}
	
	protected void diagProcess() {
		for(int j = -1 ; j < 2; j++) {
			if(j == 0) continue;
			for(int i = 1; i <= ChessGame.getNumSqrToEdge(xPos, yPos).get(j == 1 ? 6 : 4); i++) {
				Piece target = ChessGame.getPiece(xPos - (i * j), yPos + (i * j));
				if(target == null) { 
					possibleMove.add(new Move(xPos, yPos, xPos - (i * j), yPos + (i * j), target));
				}else {
					if(target.isWhite != this.isWhite) {
						possibleMove.add(new Move(xPos, yPos, xPos - (i * j), yPos + (i * j), target));
					}
					break;
				}
			}
			for(int i = 1; i <= ChessGame.getNumSqrToEdge(xPos, yPos).get(j == 1 ? 5 : 7); i++) {
				Piece target = ChessGame.getPiece(xPos + (i * j), yPos + (i * j));
				if(target == null) { 
					possibleMove.add(new Move(xPos, yPos, xPos + (i * j), yPos + (i * j), target));
				}else {
					if(target.isWhite != this.isWhite) {
						possibleMove.add(new Move(xPos, yPos, xPos + (i * j), yPos + (i * j), target));
					}
					break;
				}
			}
		}
	}
}
