package pieces;

import java.util.ArrayList;
import java.util.LinkedList;

import chessgame.ChessGame;

public class Piece {
	public int xPos;
	public int yPos;
	

	public boolean isWhite;
	public String type;

	public ArrayList<Move> possibleMove = new ArrayList<Move>();
	protected int moveCount = 0;
	protected int moveTime;
	
	public Piece(int xPos, int yPos, boolean isWhite, String type, LinkedList<Piece> pList) {
		super();
		this.xPos = xPos;
		this.yPos = yPos;
		this.isWhite = isWhite;
		this.type = type;
		pList.add(this);
	}
	
	
	public void move(int xPos, int yPos, LinkedList<Piece> pList) {
		Move temp = getMove(xPos, yPos);
		if( temp != null) {
			if(temp.target != null) {
				temp.target.die(pList);
			}
			this.xPos = xPos;
			this.yPos = yPos;
			moveCount++;
			ChessGame.setTotalMoves();
			moveTime = ChessGame.getTotalMoves();
			possibleMove.clear();
			
			
			
		}else {
			System.out.println(this.xPos + ", " + this.yPos + " to " + xPos + ", " + yPos + "Is Not Valid");
		}
		
//		checkKill(xPos, yPos, pList);
		
		
	}
	
	
	public Move getMove(int xEnd, int yEnd) {
		for (Move move: possibleMove) {
			if(move.xEnd == xEnd && move.yEnd == yEnd) {
				return move;
			}
		}
		
		return null;
	}
	
//	public void checkKill(int xPos, int yPos, LinkedList<Piece> pList) {
//		for (Piece p: pList) {
////			System.out.println(p.xPos + " " + p.yPos + " " + xPos + " " + yPos);
//			if(p.xPos == xPos && p.yPos == yPos) {
//				p.die(pList);
//				break;
//				
//			}
//		}
//	}

	public void die(LinkedList<Piece> pList) {
		
		pList.remove(this);
		
	}
	
	public void preProcess() {
//		System.out.println("No Process Yet");
		
	}
	

}
