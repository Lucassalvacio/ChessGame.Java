package pieces;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import chessgame.ChessGame;

public class Piece {
	public int xPos;
	public int yPos;
	public boolean isWhite;
	public String type;
	
	public int xRawPos; // For following the mouse while user dragging piece
	public int yRawPos; 

	public ArrayList<Move> possibleMove = new ArrayList<Move>();
	protected int moveCount = 0;
	protected int moveTime;
	
	public boolean isCheck;

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
		Piece target = temp.target;
		int redoTargetX = 10;
		if(!ChessGame.checkPieces.isEmpty()) {
			int redoX = this.xPos, redoY = this.yPos;
			if(temp.target != null) {
				redoTargetX = target.xPos;
				target.xPos = 10;
			}
			ChessGame.checkPieces.clear();
			this.xPos = xPos;
			this.yPos = yPos;
			this.xRawPos = xPos * 64;
			this.yRawPos = yPos * 64;
			ChessGame.reProcess();
			if(!ChessGame.checkPieces.isEmpty()) {
				System.out.println(ChessGame.checkPieces.get(0));
				this.xPos = redoX;
				this.yPos = redoY;
				this.xRawPos = redoX * 64;
				this.yRawPos = redoY * 64;
				if(redoTargetX != 10) temp.target.xPos = redoTargetX; 
				ChessGame.reProcess();
			}else {
				if(target != null) target.die(pList);
				moveCount++;
				ChessGame.setTotalMoves();
				moveTime = ChessGame.getTotalMoves();
				possibleMove.clear();

				ChessGame.reProcess();
			}
		}else if( temp != null) {
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
			System.out.println(this.type + " " + this.xPos + ", " + this.yPos + " to " + xPos + ", " + yPos + "Is Not Valid");
		}	
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
		if(isCheck == true) {
			ChessGame.checkPieces.add(this);
		}
	}
	

}
