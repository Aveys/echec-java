package network;

import controler.controlerNetwork.ChessGameControler;
import model.Coord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class AcceptConnection implements Runnable{

	ServerSocket ss;
	ChessGameControler controler;

	public AcceptConnection(ServerSocket pss, ChessGameControler chessGameControler) {
		ss = pss;

	}

	@Override
	public void run() {
		try {
			while(true){
				Socket socket = ss.accept();
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String msg = in.readLine();
				String[] splitString = msg.split(":");
				Coord initCoord=new Coord(Integer.parseInt(splitString[1]),Integer.parseInt(splitString[2]));
				Coord finalCoord=new Coord(Integer.parseInt(splitString[2]),Integer.parseInt(splitString[4]));
				controler.moveNetwork(initCoord,finalCoord);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
