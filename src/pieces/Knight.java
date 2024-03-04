package pieces;

import java.util.LinkedList;

import chessgame.ChessGame;

public class Knight extends Piece{

	public Knight(int xPos, int yPos, boolean isWhite, String type, LinkedList<Piece> pList) {
		super(xPos, yPos, isWhite, type, pList);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void preProcess() {
		// TODO Auto-generated method stub
		this.isCheck = false;
		
		for(int i = -2; i <= 2; i++) {
			for(int j = -2; j <= 2; j++) {
				if(Math.abs(i) == Math.abs(j) || i == 0 || j == 0) continue;
				Piece target = ChessGame.getPiece(xPos + j, yPos + i);
				if(target == null || target.isWhite != isWhite) {
					possibleMove.add(new Move(xPos, yPos, xPos + j, yPos + i, target));
				}
					
			}
		}
		super.preProcess();
		
	}


}
