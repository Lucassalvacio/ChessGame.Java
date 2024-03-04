package pieces;

import java.util.LinkedList;

import chessgame.ChessGame;

public class King extends Piece{
	
	public King(int xPos, int yPos, boolean isWhite, String type, LinkedList<Piece> pList) {
		super(xPos, yPos, isWhite, type, pList);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void preProcess() {
		// TODO Auto-generated method stub
	
		this.isCheck = false;
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				if(j == 0 && i == 0) continue;
				Piece target = ChessGame.getPiece(xPos + j, yPos + i);
				if(target == null || target.isWhite != isWhite) {
					possibleMove.add(new Move(xPos, yPos, xPos + j, yPos + i, target));
				}
				
			}
		}
		super.preProcess();
		
	}

}
