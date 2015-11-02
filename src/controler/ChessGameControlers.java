package controler;

import model.Coord;
import model.Couleur;

/**
 * Created by arthurveys on 02/11/15 for ProjetDP2.
 */
public interface ChessGameControlers {

	public boolean move(Coord initCoord, Coord finalCoord);
	public String getMessage();
	public boolean isEnd();
	public Couleur getColorCurrentPlayer();
}
