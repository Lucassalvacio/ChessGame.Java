package pieces;

import java.util.LinkedList;

public class Bishop extends LongWalkers{

	public Bishop(int xPos, int yPos, boolean isWhite, String type, LinkedList<Piece> pList) {
		super(xPos, yPos, isWhite, type, pList);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void preProcess() {
		// TODO Auto-generated method stub
		super.preProcess();
		diagProcess();
	}

}
