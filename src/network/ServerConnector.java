package network;

import controler.ChessGameControlers;
import controler.controlerNetwork.ChessGameControler;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by arthurveys on 04/11/15 for ProjetDP2.
 */
public class ServerConnector {


	public ServerConnector(int port, ChessGameControler chessGameControler) {
		try {
			ServerSocket connection = new ServerSocket(port);
			Thread t1 = new Thread(new AcceptConnection(connection,chessGameControler));
			t1.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

