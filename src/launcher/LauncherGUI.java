package launcher;

import controler.controlerLocal.ChessGameControler;
import model.ChessGame;
import vue.ChessGameCmdLine;
import vue.ChessGameGUI;
import javax.swing.*;


/**
 * Created by lbl on 02/11/2015.
 */
public class LauncherGUI {

    public static void main(String[] args) {

        ChessGame chessGame;
        ChessGameGUI chessGameGUI;
        ChessGameControler chessGameControler;

        chessGame = new ChessGame();
        chessGameControler = new ChessGameControler(chessGame);
        chessGameGUI = new ChessGameGUI();
        chessGame.addObserver(chessGameGUI);

        chessGameGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chessGameGUI.pack();
        chessGameGUI.setResizable(true);
        chessGameGUI.setLocationRelativeTo( null );
        chessGameGUI.setVisible(true);


        new ChessGameCmdLine(chessGameControler);
    }

}
