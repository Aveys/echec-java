package controler;

import model.Coord;
import model.Couleur;

import java.util.List;

/**
 * Controller de l'échiquier
 */
public interface ChessGameControlers {

	/**
	 * Move a piece on the board
	 * @param initCoord initial coord
	 * @param finalCoord final coord
	 * @return True if movement correct and executed, false if failed
	 */
	boolean move(Coord initCoord, Coord finalCoord);

	/**
	 * Return message from the move
	 * @return Message de l'échiquier
	 */
	String getMessage();

	/**
	 * check if game is finished
	 * @return true if game is finished, false otherwise
	 */
	boolean isEnd();

	/**
	 * return the color of the current player
	 * @return The color of the current player
	 */
	Couleur getColorCurrentPlayer();

	List<Coord> getListDep(Coord coord);
}
