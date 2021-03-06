package vue;

import controler.ChessGameControlers;
import model.Coord;
import model.Couleur;
import model.PieceIHM;
import tools.ChessImageProvider;
import tools.ObserverObservable.ObserverCustom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Vue de l'échiquier
 */
public class ChessGameGUI extends JFrame implements MouseListener, MouseMotionListener, ObserverCustom {

	ChessGameControlers controler;
	JLayeredPane layeredPane;
	JPanel chessBoard;
	JLabel chessPiece;
	int xAdjustment;
	int yAdjustment;
	Coord memoChessPiece;
	Font fontUnicode;

	public ChessGameGUI(ChessGameControlers controler) {

		File f = new File("font/DejaVuSans.ttf");
		try {
			fontUnicode = Font.createFont(Font.TRUETYPE_FONT, f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(fontUnicode);
			this.fontUnicode =this.fontUnicode.deriveFont(Font.TRUETYPE_FONT,60);
		} catch (FontFormatException | IOException e) {
			System.out.println("WARN - Impossible de charger la font : "+f.getAbsolutePath());
			this.fontUnicode = new Font("Arial Unicode MS", Font.PLAIN, 60);
			e.printStackTrace();
		}

		this.controler = controler;

		Dimension boardSize = new Dimension(600, 600);

		//  Use a Layered Pane for this this application
		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane);
		layeredPane.setPreferredSize(boardSize);
		layeredPane.addMouseListener(this);
		layeredPane.addMouseMotionListener(this);

		//Add a chess board to the Layered Pane

		chessBoard = new JPanel();
		layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
		chessBoard.setLayout(new GridLayout(8, 8));
		chessBoard.setPreferredSize(boardSize);
		chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

		for (int i = 0; i < 64; i++) {
			JPanel square = new JPanel(new BorderLayout());
			chessBoard.add(square);

			int row = (i / 8) % 2;
			if (row == 0)
				square.setBackground(i % 2 == 0 ? Color.lightGray : Color.white);
			else
				square.setBackground(i % 2 == 0 ? Color.white : Color.lightGray);
		}

	}

	public void mousePressed(MouseEvent e) {
		chessPiece = null;
		Component c = chessBoard.findComponentAt(e.getX(), e.getY());

		if (c instanceof JPanel)
			return;

		memoChessPiece = new Coord(e.getX() / 75, e.getY() / 75);

		List<Coord> list = controler.getListDep(memoChessPiece);

		Point parentLocation = c.getParent().getLocation();

		for (Coord coord : list) {
			JPanel panel = (JPanel) chessBoard.getComponentAt(coord.x * 75, coord.y * 75);
			panel.setBorder(BorderFactory.createMatteBorder(
					2, 2, 2, 2, Color.red));
		}


		xAdjustment = parentLocation.x - e.getX();
		yAdjustment = parentLocation.y - e.getY();
		chessPiece = (JLabel) c;
		chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
		chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
		layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
	}

	//Move the chess piece around

	public void mouseDragged(MouseEvent me) {
		if (chessPiece == null) return;
		chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
	}

	//Drop the chess piece back onto the chess board

	public void mouseReleased(MouseEvent e) {
		if (chessPiece == null) return;

		chessPiece.setVisible(false);
		layeredPane.remove(chessPiece);

		controler.move(memoChessPiece, new Coord(e.getX() / 75, e.getY() / 75));

	}


	public void mouseClicked(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void update(List<PieceIHM> listPieces) {

		//Remove all the pieces of the board
		for (int i = 0; i < 64; i++) {
			JPanel panel = (JPanel) chessBoard.getComponent(i);
			panel.setBorder(null);
			panel.removeAll();
		}
		for (PieceIHM piece : listPieces) {
			String name = piece.getTypePiece();
			Couleur couleur = piece.getCouleur();
			for (Coord coordo : piece.getList()) {
				JLabel pieceIHM = new JLabel(ChessImageProvider.getUnicodeString(name, couleur), SwingConstants.CENTER);
				pieceIHM.setFont(this.fontUnicode);
				JPanel panel = (JPanel) chessBoard.findComponentAt((coordo.x * 75), (coordo.y * 75));
				panel.add(pieceIHM, BorderLayout.CENTER);
			}
		}
		chessBoard.updateUI();
		//chessBoard.repaint();
	}


	public void display() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.pack();
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
