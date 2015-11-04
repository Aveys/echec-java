package controler.controlerNetwork;

import controler.ChessGameControlers;
import model.ChessGame;
import model.Coord;
import model.Couleur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by arthurveys on 04/11/15 for ProjetDP2.
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
            game.init();
            return false;
        }

		Boolean moved = game.move(initCoord.x,initCoord.y,finalCoord.x,finalCoord.y);
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

		return moved;
	}

	public boolean moveNetwork(Coord initCoord, Coord finalCoord) {

		Boolean moved = game.move(initCoord.x,initCoord.y,finalCoord.x,finalCoord.y);
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
    public void run() {

        while(true){
            try {

                String message = in.readLine();
                System.out.println("Message reçu : " + message);
                String[] splitString = message.split(":");

                Coord initCoord=new Coord(Integer.parseInt(splitString[1]),Integer.parseInt(splitString[2]));
                Coord finalCoord=new Coord(Integer.parseInt(splitString[3]),Integer.parseInt(splitString[4]));
                this.moveNetwork(initCoord, finalCoord);

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }
}
