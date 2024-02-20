package chessgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChessGame {

	public static void reset(LinkedList<Piece> pList) {
		pList.clear();
		Piece wKing = new Piece(3, 0, true, "king", pList);
		Piece wQueen = new Piece(4, 0, true,"queen", pList);
		Piece wRook1 = new Piece(0, 0, true,"rook", pList);
		Piece wRook2 = new Piece(7, 0, true,"rook", pList);
		Piece wKnight1 = new Piece(6, 0, true,"knight", pList);
		Piece wKnight2 = new Piece(1, 0, true,"knight", pList);
		Piece wBishop1 = new Piece(5, 0, true,"Bishop", pList);
		Piece wBishop2 = new Piece(2, 0, true,"Bishop", pList);
		
		Piece bKing = new Piece(4, 7, false, "king", pList);
		Piece bQueen = new Piece(3, 7, false,"queen", pList);
		Piece bRook1 = new Piece(0, 7, false,"rook", pList);
		Piece bRook2 = new Piece(7, 7, false,"rook", pList);
		Piece bKnight1 = new Piece(6, 7, false,"knight", pList);
		Piece bKnight2 = new Piece(1, 7, false,"knight", pList);
		Piece bBishop1 = new Piece(5, 7, false,"Bishop", pList);
		Piece bBishop2 = new Piece(2, 7, false,"Bishop", pList);
		
		for(int i = 0; i < 8; i++) {
			Piece bPawn = new Piece(i, 1, true, "pawn", pList);
			Piece wPawn = new Piece(i, 6, false, "pawn", pList);
		}
	}
		
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		LinkedList<Piece> pList = new LinkedList<>();
		
		
		BufferedImage chessPieces = ImageIO.read(new File(System.getProperty("user.dir") + "/src/assets/chess.png"));
		Image piecesImage[] = new Image[12];
		
		int idx = 0;
		for(int y = 0; y < 400; y += 200) {
			for(int x = 0; x < 1200; x += 200) {
				piecesImage[idx]= chessPieces.getSubimage(x,  y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
				idx++;
				
			}
		}
		
		reset(pList);
		
		JFrame frame = new JFrame();
		frame.setBounds(10, 10, 512, 512);
//		frame.setUndecorated(true);
		
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
						g.fillRect(x*65, y*64, 64, 64);
						black = !black;
					}
					black = !black;
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
					g.drawImage(piecesImage[idx], p.xPos, p.yPos, this);
				}
			}
		};
		frame.add(panel);
		frame.setDefaultCloseOperation(3);
		frame.setVisible(true);
	}

}
