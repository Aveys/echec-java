package network;

import controler.controlerNetwork.ChessGameControler;
import model.ChessGame;
import vue.ChessGameGUI;

import java.net.Socket;

public class Connexion{

	private Socket socket = null;
	public static Thread t2;

	public Connexion(Socket s){

		socket = s;

		ChessGame chessGame;
		ChessGameGUI chessGameGUI;
		ChessGameControler chessGameControler;
		chessGame = new ChessGame();
		chessGameControler = new ChessGameControler(chessGame,socket);
		chessGameGUI = new ChessGameGUI(chessGameControler);
		chessGame.addObserver(chessGameGUI);
		chessGameGUI.display();
		chessGame.init();
	}


}