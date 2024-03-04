package chessgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;



import pieces.*;

public class ChessGame{
	
	static Piece selectedP;
	public static LinkedList<Piece> pList = new LinkedList<>();

	public static ArrayList<ArrayList<ArrayList<Integer>>> numSqrToEdge = new ArrayList<ArrayList<ArrayList<Integer>>>();
	public static int totalMoves = 0;
	
	public static List<Piece> checkPieces = new ArrayList<Piece>();
	
	public static void reset() {
		pList.clear();
		Piece wKing = new King(3, 0, true, "king", pList);
		Piece wQueen = new Queen(4, 0, true,"queen", pList);
		Piece wRook1 = new Rook(0, 0, true,"rook", pList);
		Piece wRook2 = new Rook(7, 0, true,"rook", pList);
		Piece wKnight1 = new Knight(6, 0, true,"knight", pList);
		Piece wKnight2 = new Knight(1, 0, true,"knight", pList);
		Piece wBishop1 = new Bishop(5, 0, true,"Bishop", pList);
		Piece wBishop2 = new Bishop(2, 0, true,"Bishop", pList);
		
		Piece bKing = new King(4, 7, false, "king", pList);
		Piece bQueen = new Queen(3, 7, false,"queen", pList);
		Piece bRook1 = new Rook(0, 7, false,"rook", pList);
		Piece bRook2 = new Rook(7, 7, false,"rook", pList);
		Piece bKnight1 = new Knight(6, 7, false,"knight", pList);
		Piece bKnight2 = new Knight(1, 7, false,"knight", pList);
		Piece bBishop1 = new Bishop(5, 7, false,"Bishop", pList);
		Piece bBishop2 = new Bishop(2, 7, false,"Bishop", pList);
		
		for(int i = 0; i < 8; i++) {
			Piece bPawn = new Pawn(i, 1, true, "pawn", pList);
			Piece wPawn = new Pawn(i, 6, false, "pawn", pList);
		}
				
		reProcess();
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub	

		BufferedImage chessPieces = ImageIO.read(new File(System.getProperty("user.dir") + "/src/assets/chess.png"));
		Image piecesImage[] = new Image[12];
		
		int idx = 0;
		for(int y = 0; y < 400; y += 200) {
			for(int x = 0; x < 1200; x += 200) {
				piecesImage[idx]= chessPieces.getSubimage(x,  y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
				idx++;
			}
		}
		
		for(int y = 0; y < 8; y++) {
			ArrayList<ArrayList<Integer>> arrayHolder = new ArrayList<ArrayList<Integer>>();
			for(int x = 0; x < 8; x++) {
				int numUp = y;
				int numRight = 7 - x;
				int numDown = 7 - y;
				int numLeft = x;
				
				ArrayList<Integer> temp = new ArrayList<>(Arrays.asList(numUp, numRight, numDown, numLeft, Math.min(numUp, numRight), Math.min(numRight, numDown), Math.min(numDown, numLeft), Math.min(numLeft, numUp)));
				arrayHolder.add(temp);
			}
			numSqrToEdge.add(arrayHolder);
		}
		
		System.out.println("Test");

		reset();
		
		JFrame frame = new JFrame();
		frame.setBounds(10, 10, 512, 512);
		frame.setUndecorated(true);

		JPanel panel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				boolean black = true;
				for(int y = 0; y < 8; y++) {
					for(int x = 0; x < 8; x++) {
						if(black) {
							g.setColor(Color.decode("#769656"));
						}else {
							g.setColor(Color.decode("#eeeed2"));
						}
						g.fillRect(x*64, y*64, 64, 64);
						black = !black;
					}
					black = !black;
				}
				
				if(selectedP != null) {
					for(Move m : selectedP.possibleMove) {
						g.setColor(new Color(0, 0, 0, 36));
						int r = 30;
						int x = m.xEnd*64 + (32 - (r/2));
						int y = m.yEnd*64 + (32 - (r/2));
						g.fillOval(x, y, r, r);
					}
					Move m = selectedP.getMove((selectedP.xRawPos + 32)/64, (selectedP.yRawPos + 32)/64);
					if(m != null) {
						g.setColor(Color.decode("#baca44"));
						g.fillRect(m.xEnd * 64,m.yEnd * 64, 64, 64);
					}
					
				}
				
				for (Piece p : pList) {
					int idx = 0;
					if(p.type.equalsIgnoreCase("king")) {
						idx = 0;
					}else if(p.type.equalsIgnoreCase("queen")) {
						idx = 1;
					}else if(p.type.equalsIgnoreCase("bishop")) {
						idx = 2;
					}else if(p.type.equalsIgnoreCase("knight")) {
						idx = 3;
					}else if(p.type.equalsIgnoreCase("rook")) {
						idx = 4;
					}else if(p.type.equalsIgnoreCase("pawn")) {
						idx = 5;
					}
					if(!p.isWhite) {
						idx+=6;
					}
					g.drawImage(piecesImage[idx], p.xRawPos, p.yRawPos, this);
				}
				
			}
		};
		frame.add(panel);
		frame.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if(selectedP != null) {
					Piece target = getPiece(e.getX()/64, e.getY()/64);
					
					
					
					
					if(target == null) {
						selectedP.move(e.getX()/64, e.getY()/64, pList);
						frame.repaint();
						selectedP = null;
					}else if(target.isWhite  && !selectedP.isWhite || !target.isWhite  && selectedP.isWhite) {
						selectedP.move(e.getX()/64, e.getY()/64, pList);
						frame.repaint();
						selectedP = null;
					}else {
						selectedP.xRawPos = selectedP.xPos*64;
						selectedP.yRawPos = selectedP.yPos*64;
						frame.repaint();
						selectedP = null;
					}
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(selectedP == null) {
					selectedP = getPiece(e.getX()/64, e.getY()/64);
					if(selectedP != null && ((selectedP.isWhite && totalMoves % 2 ==  1)|| ((!selectedP.isWhite) && totalMoves % 2 == 0))) {
						selectedP = null;
					}
				}
				
				
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Hi");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Bye");
				
			}
		});
		frame.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
				if(selectedP != null) {
					selectedP.xRawPos = e.getX() - 32;
					selectedP.yRawPos = e.getY() - 32;
					frame.repaint();
				}
			}
		});
		
		Timer timer = new Timer(180000, new ActionListener(){
		    public void actionPerformed(ActionEvent evt) {
		        frame.dispose();
		    }

		});
		timer.setRepeats(false);
		timer.start();
		
		frame.setDefaultCloseOperation(3);
		frame.setVisible(true);
	}

	public static int getTotalMoves() {
		return totalMoves;
	}

	public static void setTotalMoves() {
		totalMoves++;
	}
	
	public static ArrayList<Integer> getNumSqrToEdge(int x, int y) {
		return numSqrToEdge.get(y).get(x);
	}
	
	public static Piece getPiece(int xPos, int yPos) {
		for (Piece p : pList) {
			if(p.xPos == xPos && p.yPos == yPos) {
				return p;
			}
		}
		return null;
	}
	
	public static void reProcess() {
		checkPieces.clear();
		for (Piece p : pList) {
			p.possibleMove.clear();
			p.preProcess();
		}
		
		if(!checkPieces.isEmpty()) {
			for (Piece p : checkPieces) {
				System.out.println((p.isWhite ? "White" : "Black") + " " +  p.type);
			}
		}
	}
	
	public static void changePiece(Piece a, String b) {
		// TODO Auto-generated method stub
		Piece aReal = getPiece(a.xPos, a.yPos);
		aReal = new Queen(a.xPos, a.yPos, a.isWhite, b, pList);
	}

}
