package pieces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import chessgame.ChessGame;

public class LongWalkers extends Piece{

	public static ArrayList<ArrayList<Integer>> numSqrToEdge;
	
	public LongWalkers(int xPos, int yPos, boolean isWhite, String type, LinkedList<Piece> pList) {
		super(xPos, yPos, isWhite, type, pList);
		// TODO Auto-generated constructor stub
	}
	
	public void preProcess() {
		// TODO Auto-generated method stub
		
	}
	
	protected void upProcess(Piece a) {
		// TODO Auto-generated method stub
		for(int i = a.yPos; i < ChessGame.get)
	}
	
	
	
	

}
