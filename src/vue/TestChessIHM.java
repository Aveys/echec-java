package vue;

import tools.ChessImageProvider;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Created by lbl on 02/11/2015.
 */
public class TestChessIHM extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    Point memoChessPiece;
    int xAdjustment;
    int yAdjustment;

    public TestChessIHM(){
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
        chessBoard.setLayout( new GridLayout(8, 8) );
        chessBoard.setPreferredSize( boardSize );
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel( new BorderLayout() );
            chessBoard.add( square );

            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground( i % 2 == 0 ? Color.black : Color.white );
            else
                square.setBackground( i % 2 == 0 ? Color.white : Color.black );
        }

        //Add a few pieces to the board

        JLabel piece = new JLabel( new ImageIcon("./images/pionNoirS.png"));
        JPanel panel = (JPanel)chessBoard.getComponent(1);
        panel.add(piece);
        piece = new JLabel(new ImageIcon("./images/pionNoirS.png"));
        panel = (JPanel)chessBoard.getComponent(15);
        panel.add(piece);
        piece = new JLabel(new ImageIcon("./images/roiBlancS.png"));
        panel = (JPanel)chessBoard.getComponent(16);
        panel.add(piece);
        piece = new JLabel(new ImageIcon("./images/roiNoirS.png"));
        panel = (JPanel)chessBoard.getComponent(20);
        panel.add(piece);

    }

    public void mousePressed(MouseEvent e){
        chessPiece = null;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());

        if (c instanceof JPanel)
            return;

        memoChessPiece = new Point(e.getX()/75,e.getY()/75);

        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel)c;
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
        if(chessPiece == null) return;

        chessPiece.setVisible(false);

        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
        System.out.println("OldX : " + memoChessPiece.x);
        System.out.println("OldY : " + memoChessPiece.y);
        System.out.println("NewX : " + e.getX() / 75);
        System.out.println("NewY : " + e.getY() / 75);

        if(c == null){
            chessPiece.setLocation(memoChessPiece.x * 75, memoChessPiece.y * 75);
        }

        if (c instanceof JLabel){
            Container parent = c.getParent();
            parent.remove(0);
            parent.add( chessPiece );
        }
        else {
            Container parent = (Container)c;
            parent.add( chessPiece );
        }

        chessPiece.setVisible(true);
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e){

    }

    public void mouseExited(MouseEvent e) {

    }

    public static void main(String[] args) {
        JFrame frame = new TestChessIHM();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
    }
}