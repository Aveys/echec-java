package launcher;

import controler.controlerNetwork.ChessGameControler;
import model.ChessGame;
import network.ServerConnector;
import vue.ChessGameGUI;

import javax.swing.*;

/**
 * Created by arthurveys on 04/11/15 for ProjetDP2.
 */
public class LauncherGUINetwork {

	public static void main(String[] args) {

		ChessGame chessGame;
		ChessGameGUI chessGameGUI;
		ChessGameControler chessGameControler;

		chessGame = new ChessGame();
		chessGameControler = new ChessGameControler(chessGame);
		chessGameGUI = new ChessGameGUI(chessGameControler);
		chessGame.addObserver(chessGameGUI);

		Object[] options = { "Serveur", "Client" };
		int info = JOptionPane.showOptionDialog(null, "Jouer en tant que serveur (Blanc) ou client(noir) ?", "Info",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
				null, options, options[0]);
		if(info == 0){
			ServerConnector serv = new ServerConnector(8181,chessGameControler);

		}
		else {

		}
		System.out.println("INFO:"+info);

		chessGameGUI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		chessGameGUI.pack();
		chessGameGUI.setResizable(true);
		chessGameGUI.setLocationRelativeTo( null );
		chessGameGUI.setVisible(true);
		chessGame.init();

		//new ChessGameCmdLine(chessGameControler);
	}
}
