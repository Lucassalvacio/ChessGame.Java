package pieces;

import java.util.ArrayList;
import java.util.LinkedList;

import chessgame.ChessGame;

public class Piece {
	public int xPos;
	public int yPos;
	public int xRawPos;
	public int yRawPos;

	public boolean isWhite;
	public String type;

	public ArrayList<Move> possibleMove = new ArrayList<Move>();
	protected int moveCount = 0;
	protected int moveTime;
	
	public Piece(int xPos, int yPos, boolean isWhite, String type, LinkedList<Piece> pList) {
		super();
		this.xPos = xPos;
		this.yPos = yPos;
		xRawPos = xPos * 64;
		yRawPos = yPos * 64;
		
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
			this.xRawPos = xPos * 64;
			this.yRawPos = yPos * 64;
			moveCount++;
			ChessGame.setTotalMoves();
			moveTime = ChessGame.getTotalMoves();
			possibleMove.clear();

			ChessGame.reProcess();
		}else {
			this.xRawPos = this.xPos*64;
			this.yRawPos = this.yPos*64;
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

	public void die(LinkedList<Piece> pList) {
		
		pList.remove(this);
		
	}
	
	public void preProcess() {
		possibleMove.clear();
	}
	

}
