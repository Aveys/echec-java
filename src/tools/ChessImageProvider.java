package tools;

import model.Couleur;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author francoise.perrin
 * Inspiration Jacques SARAYDARYAN, Adrien GUENARD
 * 
 * Cette classe s'appuie sur ChessPieceImage
 * pour fournir les noms des images des pièces
 * qui sont utilisées dans l'IHM 
 *  
 */
public class ChessImageProvider {
	
	private static Map<String, String> mapImage;
	private static Map<String, String> mapUnicode;

	static {		
		mapImage = new HashMap<String, String>();
		for (int i = 0; i < ChessPieceImage.values().length; i++) {
			mapImage.put(ChessPieceImage.values()[i].nom, ChessPieceImage.values()[i].imageFile);
		}
		mapUnicode = new HashMap<String, String>();
		for (int i = 0; i < ChessPieceUnicode.values().length; i++) {
			mapUnicode.put(ChessPieceUnicode.values()[i].nom, ChessPieceUnicode.values()[i].UnicodeString);
		}
	}

	/**
	 * private pour ne pas instancier d'objets
	 */
	private ChessImageProvider() {

	}	
	
	/**
	 * @param pieceType
	 * @param pieceCouleur
	 * @return nom fichier contenant image de la pi�ce
	 */
	public static String getImageFile(String pieceType, Couleur pieceCouleur){
		String ret, key, value;
		ret = null;
		key = pieceType + pieceCouleur.name();
		value = mapImage.get(key);
		File g=new File("");
		try {
			ret = g.getCanonicalPath()+"/images/" + value;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static String getUnicodeString(String pieceType, Couleur pieceCouleur){
		String key;
		key = pieceType + pieceCouleur.name();
		return  mapUnicode.get(key);
	}
	/**
	 * Test unitaires
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(ChessImageProvider.getImageFile("Cavalier", Couleur.BLANC));
		System.out.println(ChessImageProvider.getUnicodeString("Cavalier",Couleur.NOIR));
	}

}
