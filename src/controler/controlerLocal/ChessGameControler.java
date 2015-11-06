package controler.controlerLocal;

import controler.ChessGameControlers;
import model.ChessGame;
import model.Coord;
import model.Couleur;

import javax.swing.*;
import java.util.List;

/**
 * Controller local de l'échiquier
 */


public class ChessGameControler implements ChessGameControlers{

	private ChessGame game ;

	public ChessGameControler(ChessGame game) {
		this.game = game;
	}

	@Override
	public boolean move(Coord initCoord, Coord finalCoord) {
		Boolean moved = game.move(initCoord.x,initCoord.y,finalCoord.x,finalCoord.y);

		if(moved){
			if(game.isPromotable(finalCoord)){
				int result = -1;
				String pieceArray[] = { "Reine", "Tour","Cavalier","Fou"};
				JFrame frame = new JFrame();
				do{
					result = JOptionPane.showOptionDialog(frame, "Quelle promotion voulez-vous ?", "Promotion",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, pieceArray, pieceArray[0]);
				}while (result < 0);
				game.promotePiece(finalCoord.x,finalCoord.y,pieceArray[result]);
				game.refresh();
			}
			game.switchJoueur();
		}

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
	public List<Coord> getListDep(Coord coord) {
		return game.getListDep(coord.x,coord.y);
	}

	@Override
	public String toString() {
		return game.toString();
	}
}
