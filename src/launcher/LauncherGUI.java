package launcher;

import controler.controlerLocal.ChessGameControler;
import model.ChessGame;
import vue.ChessGameGUI;

import javax.swing.*;


/**
 * Launcher du mode local
 */
public class LauncherGUI {

    public LauncherGUI() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        ChessGame chessGame;
        ChessGameGUI chessGameGUI;
        ChessGameControler chessGameControler;

        chessGame = new ChessGame();
        chessGameControler = new ChessGameControler(chessGame);
        chessGameGUI = new ChessGameGUI(chessGameControler);
        chessGame.addObserver(chessGameGUI);

        chessGameGUI.display();
        chessGame.refresh();

        //new ChessGameCmdLine(chessGameControler);
    }

}
