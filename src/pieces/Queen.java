package pieces;

import java.util.LinkedList;

public class Queen extends LongWalkers{

	public Queen(int xPos, int yPos, boolean isWhite, String type, LinkedList<Piece> pList) {
		super(xPos, yPos, isWhite, type, pList);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void preProcess() {
		this.isCheck = false;
		// TODO Auto-generated method stub
		
		
		diagProcess();
		straightProcess();
		super.preProcess();
	}

}
