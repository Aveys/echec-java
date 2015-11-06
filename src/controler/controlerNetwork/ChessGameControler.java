package controler.controlerNetwork;

import controler.ChessGameControlers;
import model.ChessGame;
import model.Coord;
import model.Couleur;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

/**
 * Controller on lan de l'échiquier
 */
public class ChessGameControler implements ChessGameControlers,Runnable{

	private ChessGame game ;
	private Socket socket;
    private BufferedReader in;
    private Couleur couleur;

	public ChessGameControler(ChessGame game, Socket socket,BufferedReader in,Couleur couleur) {

		this.game = game;
		this.socket = socket;
        this.in = in;
        this.couleur = couleur;

	}

	@Override
	public boolean move(Coord initCoord, Coord finalCoord) {

        if (game.getColorCurrentPlayer() != couleur){
            System.out.println("Ce n'est pas ton jeu!");
            game.refresh();
            return false;
        }

		Boolean moved = game.move(initCoord.x,initCoord.y,finalCoord.x,finalCoord.y);
		if(moved){
			game.switchJoueur();
			System.out.println(game.getMessage());
			System.out.println("Send message to client ...");
			PrintWriter out;


			try {
				out = new PrintWriter(socket.getOutputStream());
				out.println("MOV:"+convertCoord(initCoord, finalCoord));
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if(game.isPromotable(finalCoord)){
				int result = -1;
				String pieceArray[] = { "Reine", "Tour","Cavalier","Fou"};
				JFrame frame = new JFrame();
				do{
					result = JOptionPane.showOptionDialog(frame, "Quelle promotion voulez-vous ?", "Promotion",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, pieceArray, pieceArray[0]);
				}while (result < 0);
				game.promotePiece(finalCoord.x,finalCoord.y, pieceArray[result]);

				game.refresh();

				try {
					out = new PrintWriter(socket.getOutputStream());
					out.println("PROM:"+finalCoord.x + ":" + finalCoord.y + ":" + pieceArray[result]);
					out.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}


		return moved;
	}

	public boolean moveNetwork(Coord initCoord, Coord finalCoord) {

		Boolean moved = game.move(initCoord.x,initCoord.y,finalCoord.x,finalCoord.y);
		game.switchJoueur();
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


	private String convertCoord(Coord initCoord, Coord finalCoord){

		return initCoord.x +":"+initCoord.y+":"+finalCoord.x+":"+finalCoord.y;
	}

    @Override
    public List<Coord> getListDep(Coord coord) {
        return game.getListDep(coord.x,coord.y);
    }

    @Override
    public void run() {

	    //noinspection InfiniteLoopStatement
	    while(true){
            try {

                String message = in.readLine();
                System.out.println("Message reçu : " + message);
                String[] splitString = message.split(":");

				switch (splitString[0]){
					case "MOV":
						Coord initCoord=new Coord(Integer.parseInt(splitString[1]),Integer.parseInt(splitString[2]));
						Coord finalCoord=new Coord(Integer.parseInt(splitString[3]),Integer.parseInt(splitString[4]));
						this.moveNetwork(initCoord, finalCoord);
						break;
					case "PROM":
						System.out.println("Promotion!");
						game.promotePiece(Integer.parseInt(splitString[1]),Integer.parseInt(splitString[2]),splitString[3]);
						game.refresh();
						break;
					default:
						System.out.println("Commande non reconnue");
				}

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }
}
