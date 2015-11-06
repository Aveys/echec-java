package model;

/**
 * @author francoise.perrin
 * Inspiration Jacques SARAYDARYAN, Adrien GUENARD *
 */
public interface Pieces {

	/**
	 * @return indice de la colonne o� est positionn�e la piece
	 */
	int getX();
	
	/**
	 * @return indice de la ligne o� est positionn�e la piece
	 */
	int getY();
	
	/**
	 * @return couleur de la piece
	 */
	Couleur getCouleur();
	
	/**
	 * @return lenom de la piece
	 */
	String getName() ;
	
	/**
	 * @param xFinal
	 * @param yFinal
	 * @return true si d�placement l�gal
	 */
	boolean isMoveOk(int xFinal, int yFinal) ;
	
	/**
	 * @param xFinal
	 * @param yFinal
	 * @return true si d�placement effectu�
	 */
	boolean move(int xFinal, int yFinal);
	
	/** 
	 * @return true si piece effectivement captur�e
	 * Positionne x et y à -1
	 */
	boolean capture();
}

