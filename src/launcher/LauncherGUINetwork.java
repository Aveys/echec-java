package launcher;

import controler.controlerNetwork.ChessGameControler;
import model.ChessGame;
import model.Couleur;
import vue.ChessGameGUI;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Launcher du mode On lan
 */
public class LauncherGUINetwork {

	public LauncherGUINetwork() {

		ServerSocket ss;
		Socket socket;
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}



		Object[] options = { "Serveur", "Client" };

		int info = JOptionPane.showOptionDialog(null, "Jouer en tant que serveur (Blanc) ou client(noir) ? \u2654", "Info",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
				null, options, options[0]);
		if(info == 0){

			try {
				ss = new ServerSocket(2009);
				System.out.println("Le serveur est à l'écoute du port "+ss.getLocalPort());

				socket = ss.accept();
				System.out.println("Un joueur s'est connecté");
				initGame(socket,Couleur.BLANC);


			} catch (IOException e) {
				System.err.println("Le port est déjà utilisé !");
			}

		}
		else {
			try {

				System.out.println("Demande de connexion");
				socket = new Socket("127.0.0.1",2009);
				System.out.println("Connexion établie avec le serveur, authentification :"); // Si le message s'affiche c'est que je suis connecté

				initGame(socket,Couleur.NOIR);

			} catch (UnknownHostException e) {
				System.err.println("Impossible de se connecter à l'adresse ");
			} catch (IOException e) {
				System.err.println("Aucun serveur à l'écoute du port ");
			}
		}

		//new ChessGameCmdLine(chessGameControler);
	}

	public static void initGame(Socket socket, Couleur couleur){
		try {
			ChessGame chessGame;
			ChessGameGUI chessGameGUI;
			ChessGameControler chessGameControler;
			chessGame = new ChessGame();
			BufferedReader in;
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Scanner sc = new Scanner(System.in);

			chessGameControler = new ChessGameControler(chessGame, socket, in,couleur);

			Thread t1 = new Thread(chessGameControler);
			t1.start();

			chessGameGUI = new ChessGameGUI(chessGameControler);
			chessGameGUI.setTitle(couleur.toString());
			chessGame.addObserver(chessGameGUI);
			chessGameGUI.display();
			chessGame.refresh();
		} catch (IOException e) {
		System.err.println("Le port est déjà utilisé !");
	}

	}
}
