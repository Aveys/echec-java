package controler.controlerNetwork;

import controler.ChessGameControlers;
import model.ChessGame;
import model.Coord;
import model.Couleur;
import network.AcceptConnection;
import network.Reception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by arthurveys on 04/11/15 for ProjetDP2.
 */
public class ChessGameControler implements ChessGameControlers{

	private ChessGame game ;
	private Socket socket;
	public ChessGameControler(ChessGame game, Socket socket) {

		this.game = game;
		this.socket = socket;

		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			Scanner sc = new Scanner(System.in);

			Thread t3 = new Thread(new Reception(in,this));
			t3.start();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean move(Coord initCoord, Coord finalCoord) {

		Boolean moved = game.move(initCoord.x,initCoord.y,finalCoord.x,finalCoord.y);
		System.out.println(game.getMessage());
		System.out.println("Send message to client ...");
		PrintWriter out;


		try {
			out = new PrintWriter(socket.getOutputStream());
			out.println("MOV:"+convertCoord(initCoord, finalCoord));
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return moved;
	}

	public boolean moveNetwork(Coord initCoord, Coord finalCoord) {

		Boolean moved = game.move(initCoord.x,initCoord.y,finalCoord.x,finalCoord.y);
		System.out.println(game.getMessage());

		return moved;
	}
	@Override
	public String getMessage() {
		return null;
	}

	@Override
	public boolean isEnd() {
		return false;
	}

	@Override
	public Couleur getColorCurrentPlayer() {
		return null;
	}


	private String convertCoord(Coord initCoord, Coord finalCoord){

		return initCoord.x +":"+initCoord.y+":"+finalCoord.x+":"+finalCoord.y;
	}
}
