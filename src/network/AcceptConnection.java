package network;

import controler.controlerNetwork.ChessGameControler;
import model.ChessGame;
import model.Coord;
import vue.ChessGameGUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class AcceptConnection{

	private ServerSocket socketserver = null;
	private Socket socket = null;

	public Thread t1;
	public AcceptConnection(ServerSocket ss){

		socketserver = ss;

		try {
				socket = socketserver.accept();
				System.out.println("Un joueur s'est connecté");


				ChessGame chessGame;
				ChessGameGUI chessGameGUI;
				ChessGameControler chessGameControler;
				chessGame = new ChessGame();
				chessGameControler = new ChessGameControler(chessGame,socket);
				chessGameGUI = new ChessGameGUI(chessGameControler);
				chessGame.addObserver(chessGameGUI);
				chessGameGUI.display();
				chessGame.init();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
