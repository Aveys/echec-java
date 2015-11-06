package launcher;

import controler.controlerLocal.ChessGameControler;
import model.ChessGame;
import vue.ChessGameGUI;


/**
 * Created by lbl on 02/11/2015.
 */
public class LauncherGUI {

    public LauncherGUI() {


        ChessGame chessGame;
        ChessGameGUI chessGameGUI;
        ChessGameControler chessGameControler;

        chessGame = new ChessGame();
        chessGameControler = new ChessGameControler(chessGame);
        chessGameGUI = new ChessGameGUI(chessGameControler);
        chessGame.addObserver(chessGameGUI);

        chessGameGUI.display();
        chessGame.init();

        //new ChessGameCmdLine(chessGameControler);
    }

}
