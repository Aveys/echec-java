package controler.controlerNetwork;

import controler.ChessGameControlers;
import model.ChessGame;
import model.Coord;
import model.Couleur;
import network.AcceptConnection;

/**
 * Created by arthurveys on 04/11/15 for ProjetDP2.
 */
public class ChessGameControler implements ChessGameControlers,Runnable{

	private ChessGame game ;
	private AcceptConnection server;
	public ChessGameControler(ChessGame game) {
		this.game = game;
	}

	@Override
	public boolean move(Coord initCoord, Coord finalCoord) {

		Boolean moved = game.move(initCoord.x,initCoord.y,finalCoord.x,finalCoord.y);
		System.out.println(game.getMessage());
		System.out.println("Send message to client ...");


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

	@Override
	public void run() {

	}
}
