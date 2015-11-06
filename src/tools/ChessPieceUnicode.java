package tools;

/**
 * @author francoise.perrin
 *
 */
public enum ChessPieceUnicode {

	TOURBLANC("TourBLANC", "\u2656"),
	CAVALIERBLANC("CavalierBLANC", "\u2658"),
	FOUBLANC("FouBLANC",  "\u2657"),
	REINEBLANC("ReineBLANC", "\u2655"),
	ROIBLANC("RoiBLANC", "\u2654"),
	PIONBLANC("PionBLANC", "\u2659"),

	TOURNOIR("TourNOIR", "\u265C"),
	CAVALIERNOIR("CavalierNOIR", "\u265E"),
	FOUNOIR( "FouNOIR", "\u265D"),
	REINENOIR("ReineNOIR", "\u265B"),
	ROINOIR("RoiNOIR", "\u265A"),
	PIONNOIR("PionNOIR", "\u265F")
	;



	public String nom;
	public  String UnicodeString ;

	ChessPieceUnicode(String nom, String UnicodeString) {
		this.nom = nom;
		this.UnicodeString = UnicodeString;
	} 


	public static void main(String[] args) {
		for (int i = 0; i < ChessPieceUnicode.values().length; i++) {
			System.out.print(ChessPieceUnicode.values()[i].nom + " \t");
			System.out.print(ChessPieceUnicode.values()[i].UnicodeString + " \t");
			System.out.println();
		}
	}
}

