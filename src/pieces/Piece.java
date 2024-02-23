package pieces;

import java.util.LinkedList;

public class Piece {
	public int xPos;
	public int yPos;
	

	public boolean isWhite;
	public String type;


	public Piece(int xPos, int yPos, boolean isWhite, String type, LinkedList<Piece> pList) {
		super();
		this.xPos = xPos;
		this.yPos = yPos;
		this.isWhite = isWhite;
		this.type = type;
		pList.add(this);
		
	}
	
	public void move(int xPos, int yPos, LinkedList<Piece> pList) {
		for (Piece p: pList) {
//			System.out.println(p.xPos + " " + p.yPos + " " + xPos + " " + yPos);
			if(p.xPos == xPos && p.yPos == yPos) {
				p.die(pList);
				break;
				
			}
		}

//		System.out.println("move");
		this.xPos = xPos;
		this.yPos = yPos;
		
	}

	public void die(LinkedList<Piece> pList) {
		
		pList.remove(this);
//		System.out.println("Die");
		
	}
	
	

}
