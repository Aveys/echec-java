package controler.controlerLocal;

import controler.ChessGameControlers;
import model.ChessGame;
import model.Coord;
import model.Couleur;

/**
 * Created by arthurveys on 02/11/15 for ProjetDP2.
 */


public class ChessGameControler implements ChessGameControlers{

	public ChessGame game = new ChessGame();

	@Override
	public boolean move(Coord initCoord, Coord finalCoord) {
		return game.move(initCoord.x,initCoord.y,finalCoord.x,finalCoord.y);
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
}
