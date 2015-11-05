package controler;

import model.Coord;
import model.Couleur;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arthurveys on 02/11/15 for ProjetDP2.
 */
public interface ChessGameControlers {

	/**
	 * Move a piece on the board
	 * @param initCoord initial coord
	 * @param finalCoord final coord
	 * @return True if movement correct and executed, false if failed
	 */
	public boolean move(Coord initCoord, Coord finalCoord);

	/**
	 * Return message from the move
	 * @return
	 */
	public String getMessage();

	/**
	 * check if game is finished
	 * @return true if game is finished, false otherwise
	 */
	public boolean isEnd();

	/**
	 * return the color of the current player
	 * @return The color of the current player
	 */
	public Couleur getColorCurrentPlayer();

	public List<Coord> getListDep(Coord coord);
}
