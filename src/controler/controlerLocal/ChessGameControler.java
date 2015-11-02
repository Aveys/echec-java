package controler.controlerLocal;

import controler.ChessGameControlers;
import model.ChessGame;
import model.Coord;
import model.Couleur;

import javax.swing.*;

/**
 * Created by arthurveys on 02/11/15 for ProjetDP2.
 */


public class ChessGameControler implements ChessGameControlers{

	private ChessGame game ;

	public ChessGameControler(ChessGame game) {
		this.game = game;
	}

	@Override
	public boolean move(Coord initCoord, Coord finalCoord) {
		Boolean moved = game.move(initCoord.x,initCoord.y,finalCoord.x,finalCoord.y);
		System.out.println(game.getMessage());

		return moved;
	}

	@Override
	public String getMessage() {
		return game.getMessage();
	}

	@Override
	public boolean isEnd() {
		return game.isEnd();
	}

	@Override
	public Couleur getColorCurrentPlayer() {
		return game.getColorCurrentPlayer();
	}

	@Override
	public String toString() {
		return game.toString();
	}
}
