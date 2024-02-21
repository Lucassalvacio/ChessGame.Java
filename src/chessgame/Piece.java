package chessgame;

import java.util.LinkedList;

public class Piece {
	int xPos;
	int yPos;
	
	int newX;
	int newY;
	boolean isWhite;
	String type;
	LinkedList<Piece> pList;


	public Piece(int xPos, int yPos, boolean isWhite, String type, LinkedList<Piece> pList) {
		super();
		this.xPos = xPos*64;
		this.yPos = yPos*64;
		newX = xPos*64;
		newY = yPos*64;
		this.isWhite = isWhite;
		this.type = type;
		pList.add(this);
		
	}
	
	public void move(int xPos, int yPos) {
//		for (Piece p: pList) {
//			if(p.xPos == xPos && p.yPos == yPos) {
//				p.kill();
//			}
//		}
		pList.stream().filter((p) -> (p.xPos == xPos && p.yPos == yPos)).forEachOrdered((p) -> {
			p.kill();
		});
		
		//For loop and this is the same
		
		
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public void kill() {
		pList.remove(this);
	}
	
	

}
