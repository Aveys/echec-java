package launcher;

import network.AcceptConnection;
import network.Connexion;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by arthurveys on 04/11/15 for ProjetDP2.
 */
public class LauncherGUINetwork {

	public static void main(String[] args) {

		ServerSocket ss;
		Socket socket;




		Object[] options = { "Serveur", "Client" };

		int info = JOptionPane.showOptionDialog(null, "Jouer en tant que serveur (Blanc) ou client(noir) ?", "Info",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
				null, options, options[0]);
		if(info == 0){
			try {
				ss = new ServerSocket(2009);
				System.out.println("Le serveur est à l'écoute du port "+ss.getLocalPort());

				new AcceptConnection(ss);

			} catch (IOException e) {
				System.err.println("Le port est déjà utilisé !");
			}

		}
		else {
			try {

				System.out.println("Demande de connexion");
				socket = new Socket("127.0.0.1",2009);
				System.out.println("Connexion établie avec le serveur, authentification :"); // Si le message s'affiche c'est que je suis connecté

				new Connexion(socket);

			} catch (UnknownHostException e) {
				System.err.println("Impossible de se connecter à l'adresse ");
			} catch (IOException e) {
				System.err.println("Aucun serveur à l'écoute du port ");
			}
		}

		//new ChessGameCmdLine(chessGameControler);
	}
}
