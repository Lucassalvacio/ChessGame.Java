package pieces;

import java.util.LinkedList;

public class Rook extends LongWalkers{

	public Rook(int xPos, int yPos, boolean isWhite, String type, LinkedList<Piece> pList) {
		super(xPos, yPos, isWhite, type, pList);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void preProcess() {
		this.isCheck = false;
		// TODO Auto-generated method stub
		straightProcess();
		super.preProcess();
	}

	

}
