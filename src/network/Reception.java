package network;

import controler.controlerNetwork.ChessGameControler;
import model.Coord;

import java.io.BufferedReader;
import java.io.IOException;


public class Reception implements Runnable {

	private BufferedReader in;
	private ChessGameControler controler;

	public Reception(BufferedReader in, ChessGameControler controler){

		this.in = in;
		this.controler = controler;
	}

	public void run() {

		while(true){
			try {

				String message = in.readLine();
				System.out.println("Message reçu : " + message);
				String[] splitString = message.split(":");

				Coord initCoord=new Coord(Integer.parseInt(splitString[1]),Integer.parseInt(splitString[2]));
				Coord finalCoord=new Coord(Integer.parseInt(splitString[2]),Integer.parseInt(splitString[4]));
				controler.moveNetwork(initCoord, finalCoord);

			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

}